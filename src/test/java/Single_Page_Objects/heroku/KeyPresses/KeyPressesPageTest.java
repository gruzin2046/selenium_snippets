package Single_Page_Objects.heroku.KeyPresses;

import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.KeyPressesPage.KeyPressesPage;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class KeyPressesPageTest extends BaseTest {
    private KeyPressesPage keyPressesPage;

    @BeforeEach
    void prepareTest() {
        keyPressesPage = new KeyPressesPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(keyPressesPage.pageShouldLoad());
    }

    @Test
    void pressEnter() {
        keyPressesPage.pressKey(Keys.ENTER);
        String resultText = keyPressesPage.getResultText();
        String expectedText = "You entered: ENTER";
        assertEquals(resultText, expectedText);
    }

    @Test
    void pressSpace() {
        keyPressesPage.pressKeyWithAction(Keys.SPACE);
        String resultText = keyPressesPage.getResultText();
        String expectedText = "You entered: SPACE";
        assertEquals(resultText, expectedText);
    }
}
