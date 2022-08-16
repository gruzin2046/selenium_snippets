package p02_pom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/***
 * - Zamknięcie strony po każdym teście // done
 * - Po wyświetleniu strony powinno być 3 userów //done
 * - Można ich usunąć! //done
 * - Czy jest pole do wprowadzenia user //done
 * - Dodać dwóch jednakowych //done
 * - Dodanie usera //done
 * - Usunięcie usera //done
 * - Usunąć wszystkich (sypie się) // usun wszystkich dziala // usun wszystkich i dodaj - nie // done
 * - Sprawdzić polskie znaki
 * - Spróbować dodać pustego usera - niemożliwe, przycisk dodawania zablokowany
 * - Spróbować dodać spacje
 * - Długi string (długość 1200)
 * - Zakazane znaki (?)
 * - Czy działa inkrementacja
 * - Dodanie dwóch identycznych (powinno działać)
 * - Usunięcie środkowego nie zmienia numeracji
 * - Usunięcie ostatniego usera (ID: 4) -> Dodanie nowego - powinien mieć id 4 (o jeden większy niż największy)
 */
public class CustomersPageTest {
    private CustomersPage customersPage;

// @BeforeEach @AfterEach - metody publiczne NIESTATYCZNE
// @BeforeAll @AfterAll - metody publiczne STATYCZNE

    @BeforeEach
    public void setupBeforeEveryTest() {
        customersPage = new CustomersPage();
    }

    @AfterEach
    public void cleanup() {
        customersPage.closeDriver();
    }

    @Test
    public void afterPageInitialLoadShouldThereBe3DefaultUsers() {
        int customersNumber = customersPage.countCustomers();
        assertEquals(3, customersNumber, "Customers number on page should be equal to 3");
    }

    @Test
    public void afterPageInitialLoadShouldBeExact3UsersWithSpecificData() {
        // given
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz");
        // when
        List<String> customersNames = customersPage.findCustomersNames();
        // then
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void initialUsersShouldBeDeletable() {
        List<String> expectedNames = List.of("Anna", "Mateusz");
        customersPage.deleteFirstCustomer();
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void inputFieldShouldExist() {
        customersPage.findInputField();
    }

    @Test
    public void addingUserShouldBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", "Kamil");
        customersPage.addUser("Kamil");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void addingTwoUsersWithTheSameNameShouldBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna", "Mateusz", "Kamil", "Kamil");
        customersPage.addUser("Kamil");
        customersPage.addUser("Kamil");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void deletingExistingUserShouldBePossible() {
        List<String> expectedNames = List.of("Tomasz", "Anna");
        customersPage.deleteUser("Mateusz");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }

    @Test
    public void addingNewUserAfterDeletingAllUsersShouldBePossible() {
        List<String> expectedNames = List.of("Albert");
        customersPage.deleteUser("Mateusz");
        customersPage.deleteUser("Anna");
        customersPage.deleteUser("Tomasz");
        customersPage.addUser("Albert");
        List<String> customersNames = customersPage.findCustomersNames();
        assertEquals(expectedNames, customersNames);
    }
}


/***
 * //*[@class="Customers-list"]/div/div/span[@class="customer-name"][contains(text(),'Tomasz')]
 *
 * //span[@class="customer-name"][contains(text(),'" + name + "')]
 *
 * //span[@class="customer-name"]
 * span.customer-name
 */
