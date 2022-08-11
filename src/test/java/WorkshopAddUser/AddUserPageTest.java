package WorkshopAddUser;

import WorkShopAddUser.AddCustomer;
import dev.failsafe.internal.util.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddUserPageTest {
    private static WebDriver driver = new ChromeDriver();
    private AddCustomer page;

    @BeforeAll
    public static void warmup() {
        driver = new ChromeDriver();
    }
    @BeforeEach
    void prepareTest() {
        page = new AddCustomer(driver);
    }

    @Test
    public void addUser() {
        int howManyUsersOnStartPage = page.howManyUsersOnPage();
        page.addUser("Janusz");
        int howManyUsersOnPageAfterAdding = page.howManyUsersOnPage();
        page.setCustomers();
        Assertions.assertEquals(1, howManyUsersOnPageAfterAdding - howManyUsersOnStartPage);
        Assertions.assertTrue(page.isUserOnThePage("Janusz"));

    }




}
