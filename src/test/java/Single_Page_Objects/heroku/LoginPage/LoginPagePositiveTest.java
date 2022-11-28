package Single_Page_Objects.heroku.LoginPage;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.HerokuSecurePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPagePositiveTest extends BaseTest {

    private HerokuLoginPage herokuLoginPage;


    @BeforeEach
    void prepareTest() {
        herokuLoginPage = new HerokuLoginPage(driver);
    }

    @Test
    void submit_correctCredentials_opensSecurePage(){
        HerokuSecurePage herokuSecurePage = herokuLoginPage.loginWithCorrectCredentials();
        assertTrue(herokuSecurePage.isSuccessfulLoginMsgDisplayed());
    }

}