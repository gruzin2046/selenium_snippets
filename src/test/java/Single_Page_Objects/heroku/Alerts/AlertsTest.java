package Single_Page_Objects.heroku.Alerts;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.AlertsPage.AlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertsTest extends BaseTest {
    private AlertsPage alertsPage;

    @BeforeEach
    void prepareTest() {
        alertsPage = new AlertsPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(alertsPage.pageShouldLoad());
    }

    @Test
    void checkAlertText() {
        alertsPage.openAlert("Alert");
        String alertText = alertsPage.getAlertText();
        String expectedAlertText = "I am a JS Alert";
        alertsPage.acceptAlert();
        assertEquals(expectedAlertText, alertText);
    }

    @Test
    void jsAlertIsAccepted_MessageShouldBeDisplayed() {
        alertsPage.openAlert("Alert");
        alertsPage.acceptAlert();
        String resultMessage = alertsPage.getResultMessage();
        String expectedMessage = "You successfully clicked an alert";
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void jsConfirmIsAccepted_MessageShouldBeDisplayed() {
        alertsPage.openAlert("Confirm");
        alertsPage.acceptAlert();
        String resultMessage = alertsPage.getResultMessage();
        String expectedMessage = "You clicked: Ok";
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void jsConfirmIsDismiss_MessageShouldBeDisplayed() {
        alertsPage.openAlert("Confirm");
        alertsPage.dismissAlert();
        String resultMessage = alertsPage.getResultMessage();
        String expectedMessage = "You clicked: Cancel";
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void jsPromptIsAccepted_MessageShouldBeDisplayed() {
        alertsPage.openAlert("Prompt");
        alertsPage.typeInAlert("Hello");
        alertsPage.acceptAlert();
        String resultMessage = alertsPage.getResultMessage();
        String expectedMessage = "You entered: Hello";
        assertEquals(expectedMessage, resultMessage);
    }
}
