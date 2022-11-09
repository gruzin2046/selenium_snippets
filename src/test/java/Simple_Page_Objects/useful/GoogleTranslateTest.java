package Simple_Page_Objects.useful;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTranslateTest {

    private static WebDriver driver;
    private GoogleTranslate googleTranslate;

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
        googleTranslate = new GoogleTranslate(driver);
        googleTranslate.acceptCookies();
    }

    @Test
    public void pageShouldLoad() {
        assertTrue(googleTranslate.pageShouldLoad());
    }

    @Test
    public void translateText() {
        googleTranslate.translateText("chcę to przetłumaczyć");
        String translatedText = googleTranslate.checkTranslation();
        assertEquals("I want to translate it", translatedText);
    }


    @Test
    public void translateToItalian() {
        googleTranslate.changeTranslationLanguage("Italian");
        googleTranslate.translateText("chcę to przetłumaczyć");
        String translatedText = googleTranslate.checkTranslation();
        assertEquals("Voglio tradurlo", translatedText);
    }
}
