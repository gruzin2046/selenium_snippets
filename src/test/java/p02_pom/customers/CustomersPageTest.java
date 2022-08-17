package p02_pom.customers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p02_pom.customers.CustomersPage;

public class CustomersPageTest {
    private CustomersPage customersPage;

// @BeforeEach @AfterEach - metody publiczne NIESTATYCZNE
// @BeforeAll @AfterAll - metody publicczne STATYCZNE

    @BeforeEach
    public void setupBeforeEveryTest() {
        customersPage = new CustomersPage();
    }

    @AfterEach
    public void cleanup() {
        customersPage.quitDriver();
    }

    // Po wyświetleniu strony powinno być 3 userów
    @Test
    public void afterPageInitialLoadShouldThereBe3DefaultUsers() {
        int customersNumber = customersPage.countCustomers();

        assertEquals(3, customersNumber, "Customers number on page should be equal to 3");
    }

    //  * - Po wyświetleniu strony powinno być 3 userów - sprawdzenie konkretnych imion
    @Test
    public void afterPageInitialLoadShouldBeExact3UsersWithSpecificData() {
        // given
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz");

        // when
        List<String> customersNames = customersPage.findCustomersNames();

        // then
        assertEquals(expectedNames, customersNames);
    }

    // Można ich usunąć!
    @Test
    public void initialUsersShouldBeDeletable() {
        List<String> expectedNames = List.of("Anna", "Mateusz");

        customersPage.deleteFirstCustomer();
        List<String> customersNames = customersPage.findCustomersNames();

        assertEquals(expectedNames, customersNames);
    }

    // Czy jest pole do wprowadzenia user
    @Test
    public void shouldExistUserInput() {
        boolean inputExists = customersPage.usernameInputExists();

        Assertions.assertTrue(inputExists);
    }


    // Dodanie usera
    @Test
    public void shouldAddUser() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", "Marysia");

        customersPage.addUser("Marysia");

        List<String> customersNames = customersPage.findCustomersNames();

        Assertions.assertEquals(expectedNames, customersNames);
    }

    // Dodać dwóch jednakowych userów
    @Test
    public void shouldAddingTwoIdenticalUsersBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", "Kuba", "Kuba");

        customersPage.addUser("Kuba");
        customersPage.addUser("Kuba");

        List<String> customersNames = customersPage.findCustomersNames();

        Assertions.assertEquals(expectedNames, customersNames);
    }

    // Usunięcie usera
    @Test
    public void shouldDeleteAddedUser() {
        customersPage.addUser("Marysia");

        int customersNumber = customersPage.countCustomers();
        Long userId = customersPage.findAllCustomers().stream()
                .filter(customer -> customer.getName().equals("Marysia"))
                .mapToLong(Customer::getId)
                .max()
                .orElseThrow();

        customersPage.deleteUser(customersNumber);

        List<Customer> allCustomersAfterDelete = customersPage.findAllCustomers();
        assertEquals(customersNumber - 1, allCustomersAfterDelete.size());

        boolean isUserDeleted = allCustomersAfterDelete.stream()
                .noneMatch(customer -> customer.getId() == userId && customer.getName().equals("Marysia"));
        Assertions.assertTrue(isUserDeleted);
    }

    // Usunąć wszystkich - powinno usunąć wszystkich customerów
    @Test
    public void deleteAllCustomersShouldWork(){
        customersPage.addUser("Kuba");

        int customersNumberBefore = customersPage.countCustomers();

        Assumptions.assumeTrue(customersNumberBefore > 0);
        // opcjonalne to co wyżej /\

        customersPage.deleteAllUsers();

        assertEquals(0, customersPage.countCustomers());
    }

    // Usunąć wszystkich i dodanie nowego - (SYPIE SIĘ)
    @Test
    public void deleteAllCustomersAndAddNewOneShouldWork(){
        customersPage.deleteAllUsers();

        customersPage.addUser("Piotr");

        List<Customer> allCustomers = customersPage.findAllCustomers();

        Assertions.assertEquals(1, allCustomers.size());

        Customer addedCustomer = allCustomers.get(0);
        Assertions.assertEquals(1, addedCustomer.getId());
        Assertions.assertEquals("Piotr", addedCustomer.getName());
    }

    // Spróbować dodać pustego usera - niemożliwe, przycisk dodawania zablokowany
    @Test
    public void addButtonShouldBeDisabledWhileInputEmpty(){
        customersPage.enterUserData("Kuba");
        customersPage.enterUserData("");

        assertFalse(customersPage.isAddCustomerButtonEnabled());
    }

    // Po dodaniu użytkownika pole username jest czyszczone
    @Test
    public void customerInputShouldBeClearedAfterAddNewUserAction(){
        customersPage.addUser("John");

        assertTrue(customersPage.getCustomerInputValue().isEmpty());
    }

    @Test
    public void addingSpaceShouldNotBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz");
        customersPage.addUser("$#@?~>");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void addingSpecialCharacterShouldNotBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz");
        customersPage.addUser(" ");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void addingPolishShouldBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", "Świętosława");
        customersPage.addUser("Świętosława");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void addingLongStringShouldBePossible() {
        String longString = customersPage.createLongString();
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", longString);
        customersPage.addUser(longString);
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void deletingMiddleUserShouldNotChangeNumbersOrder() {
        int allCustomersNumberBeforeAdd = customersPage.findAllCustomers().size();
        customersPage.deleteUser(allCustomersNumberBeforeAdd - 1);
        List<Customer> allCustomers = customersPage.findAllCustomers();
        Customer lastCustomer = allCustomers.get(allCustomers.size()-1);;
        Assertions.assertEquals(allCustomersNumberBeforeAdd, lastCustomer.getId());
    }


    @Test
    public void incrementationAddingOneUser() {
        int allCustomersNumberBeforeAdd = customersPage.findAllCustomers().size();
        customersPage.addUser("Nico");
        Customer addedCustomer = customersPage.findAllCustomers().get(allCustomersNumberBeforeAdd);
        Assertions.assertEquals("Nico", addedCustomer.getName());
        Assertions.assertEquals(allCustomersNumberBeforeAdd + 1, addedCustomer.getId());
    }

    @Test
    public void incrementationDeletingFirstUserAddingOneUser() {
        int allCustomersNumberBeforeAdd = customersPage.findAllCustomers().size();
        customersPage.deleteFirstCustomer();
        customersPage.addUser("Nico");
        Customer addedCustomer = customersPage.findAllCustomers().get(allCustomersNumberBeforeAdd - 1);
        Assertions.assertEquals("Nico", addedCustomer.getName());
        Assertions.assertEquals(allCustomersNumberBeforeAdd + 1, addedCustomer.getId());
    }

    @Test
    public void incrementationDeletingLastUserAddingOneUser() {
        int allCustomersNumberBeforeAdd = customersPage.findAllCustomers().size();
        customersPage.deleteUser(allCustomersNumberBeforeAdd);
        customersPage.addUser("Nico");
        Customer addedCustomer = customersPage.findAllCustomers().get(allCustomersNumberBeforeAdd - 1);
        Assertions.assertEquals("Nico", addedCustomer.getName());
        Assertions.assertEquals(allCustomersNumberBeforeAdd, addedCustomer.getId());
    }

    /***
     * - Sprawdzić polskie znaki /done
     * - Spróbować dodać spacje /done
     * - Długi string (długość 1200) /done
     * - Zakazane znaki (?) /done
     * - Czy działa inkrementacja /done
     * - Dodanie dwóch identycznych (powinno działać) / done
     * - Usunięcie środkowego nie zmienia numeracji /done
     * - Usunięcie ostatniego usera (ID: 4) -> Dodanie nowego - powinien mieć id 4 (o jeden większy niż największy) /done
     */
}


/***
 * //*[@class="Customers-list"]/div/div/span[@class="customer-name"][contains(text(),'Tomasz')]
 *
 * //span[@class="customer-name"][contains(text(),'" + name + "')]
 *
 * //span[@class="customer-name"]
 * span.customer-name
 */
