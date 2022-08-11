package p02_pom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/***
 * - Po wyświetleniu strony powinno być 3 userów
 * - Można ich usunąć!
 * - Czy jest pole do wprowadzenia user
 * - Dodać dwóch jednakowych
 * - Dodanie usera
 * - Usunięcie usera
 * - Usunąć wszystkich (sypie się)
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
// @BeforeAll @AfterAll - metody publicczne STATYCZNE

    @BeforeEach
    public void setupBeforeEveryTest() {
        customersPage = new CustomersPage();
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
}


/***
 * //*[@class="Customers-list"]/div/div/span[@class="customer-name"][contains(text(),'Tomasz')]
 *
 * //span[@class="customer-name"][contains(text(),'" + name + "')]
 *
 * //span[@class="customer-name"]
 * span.customer-name
 */
