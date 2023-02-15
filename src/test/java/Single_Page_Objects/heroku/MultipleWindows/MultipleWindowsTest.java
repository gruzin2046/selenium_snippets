package Single_Page_Objects.heroku.MultipleWindows;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.MultipleWindowsPage.MultipleWindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleWindowsTest extends BaseTest {

    private MultipleWindowsPage multipleWindowsPage;

    @BeforeEach
    void prepareTest() {
        multipleWindowsPage = new MultipleWindowsPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(multipleWindowsPage.pageShouldLoad());
    }

    @Test
    void switchToNewWindow() {
        multipleWindowsPage.openNewWindow();
        multipleWindowsPage.switchToNewPageWindow();
        String currentURL = multipleWindowsPage.getCurrentUrl();
        String expectedURL = "https://the-internet.herokuapp.com/windows/new";
        System.out.println(currentURL);
        assertEquals(expectedURL, currentURL);
    }
}
