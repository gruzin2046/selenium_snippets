package Simple_Page_Objects.heroku;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

class dragAndDropTest {

    private static WebDriver driver;
    private dragAndDrop dragAndDrop;

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
        dragAndDrop = new dragAndDrop(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(dragAndDrop.pageShouldLoad());
    }

    @Test
    void dragAndDropSquareStepByStep() {
        // switch to internal iframe
        dragAndDrop.switchToFrame();
        // assert text before drag and drop
        String destSquareTextBefore = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Drop here",destSquareTextBefore);
        // execute drag and drop
        dragAndDrop.dragAndDropSquareStepByStep();
        // assert text after drag and drop
        String destSquareTextAfter = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Dropped!",destSquareTextAfter);
    }

    @Test
    void dragAndDropSquareUsingDND() {
        dragAndDrop.switchToFrame();
        String destSquareTextBefore = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Drop here",destSquareTextBefore);
        dragAndDrop.dragAndDropSquareOnElement();
        String destSquareTextAfter = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Dropped!",destSquareTextAfter);
    }

    @Test
    void dNDSquareUsingDNDByOffset() {
        dragAndDrop.switchToFrame();
        String destSquareTextBefore = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Drop here",destSquareTextBefore);
        dragAndDrop.dragAndDropByOffset();
        String destSquareTextAfter = dragAndDrop.getDestSquareText();
        Assertions.assertEquals("Dropped!",destSquareTextAfter);
    }

}