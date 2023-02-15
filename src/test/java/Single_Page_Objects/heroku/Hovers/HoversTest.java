package Single_Page_Objects.heroku.Hovers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoversTest {

    private static WebDriver driver;
    private Hovers hovers;

    @BeforeAll
    public static void warmup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void cleanup() {
        driver.quit();
    }

    @BeforeEach
    public void prepareTest() {
        hovers = new Hovers(driver);
    }

    @Test
    public void pageShouldLoad() {
        assertTrue(hovers.pageShouldLoad());
    }

    @Test
    public void whenHoverOverFirstUserPhotoUserNameShouldBeDisplayed() {
        hovers.hoverOverUserPhoto(1);
        boolean isNameDisplayed = hovers.checkIfUserNameIsDisplayed();
        assertTrue(isNameDisplayed);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void whenViewProfileIsClickedUserPageShouldBeDisplayed(int orderNum) {
        hovers.hoverOverUserPhoto(orderNum);
        hovers.clickOnViewProfile(orderNum);
        boolean userPageIsOpened = hovers.checkIfUserPageIsOpened(orderNum);
        assertTrue(userPageIsOpened);
    }
}
