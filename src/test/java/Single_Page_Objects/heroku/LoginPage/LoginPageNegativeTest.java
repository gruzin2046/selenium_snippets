package Single_Page_Objects.heroku.LoginPage;
import Single_Page_Objects.Base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static Single_Page_Objects.heroku.LoginPage.HerokuLoginPage.CORRECT_USERNAME;

public class LoginPageNegativeTest extends BaseTest {

    private HerokuLoginPage herokuLoginPage;

    @BeforeEach
    void prepareTest() {
        herokuLoginPage = new HerokuLoginPage(driver);
    }

    @Test
    void submit_incorrectCredentials_displaysIncorrectUsernameMsg() {
        herokuLoginPage.loginWithIncorrectCredentials();
        assertTrue(herokuLoginPage.isUsernameIncorrectMsgDisplayed());
    }

    // 1. sequential calls
    @Test
    void submit_incorrectPassword_displaysIncorrectPasswordMessage() {
        // If methods used below would be returning void,
        // we have to call them sequentially:
        herokuLoginPage.fillUsername(CORRECT_USERNAME);
        herokuLoginPage.fillPassword("xyz");
        herokuLoginPage.submitForm();

        assertTrue(herokuLoginPage.isPasswordIncorrectMsgDisplayed());
    }

    // 2. chain calls -> monada
    @Test
    void submit_incorrectPassword_displaysIncorrectPasswordMessageMONADA() {
        // If methods used below would be returning HerokuLoginPage instance,
        // we can call them in one chain (monada):
        herokuLoginPage
                .fillUsername(CORRECT_USERNAME)
                .fillPassword("xyz")
                .submitForm();

        assertTrue(herokuLoginPage.isPasswordIncorrectMsgDisplayed());
    }
}
