package Simple_Page_Objects.useful;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckboxTest {

    private static WebDriver driver;
    private Checkbox checkbox;

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
    void prepareTest() {
        checkbox = new Checkbox(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(checkbox.pageShouldLoad());
    }

    @Test
    void checkIfHaveText() {
        // weird but its working
        String text = checkbox.getCheckbox(0).getText();
        assertEquals("", text);
    }

    @Test
    void checkIfDisplayed() {
        boolean isDisplayed = checkbox.getCheckbox(0).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    void checkIfSelected() {
        boolean isSelected = checkbox.getCheckbox(1).isSelected();
        assertTrue(isSelected);
    }

    @Test
    void checkIfEnabled() {
        boolean isEnabled = checkbox.getCheckbox(0).isEnabled();
        assertTrue(isEnabled);
    }

    @Test
    void clickCheckbox() {
        checkbox.getCheckbox(0).click();
        boolean isSelected = checkbox.getCheckbox(0).isSelected();
        assertTrue(isSelected);
    }

    @Test
    void getCheckedAttribute() {
        String checked = checkbox.getCheckbox(1).getAttribute("checked");
        assertEquals("true", checked);
    }

}