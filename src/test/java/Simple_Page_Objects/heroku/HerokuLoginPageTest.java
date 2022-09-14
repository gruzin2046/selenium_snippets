package Simple_Page_Objects.heroku;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static Simple_Page_Objects.heroku.HerokuLoginPage.CORRECT_USERNAME;

class HerokuLoginPageTest {

    private static WebDriver driver;
    private HerokuLoginPage herokuLoginPage;

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
        herokuLoginPage = new HerokuLoginPage(driver);
    }

    @Test
    void submit_incorrectCredentials_displaysIncorrectUsernameMsg() {
        herokuLoginPage.loginWithIncorrectCredentials();
        assertTrue(herokuLoginPage.isUsernameIncorrectMsgDisplayed());
    }

    @Test
    void submit_incorrectPassword_displaysIncorrectPasswordMessage() {
        herokuLoginPage.fillUsername(CORRECT_USERNAME)
                .fillPassword("sadfasdf")
                .submitForm();

        assertTrue(herokuLoginPage.isPasswordIncorrectMsgDisplayed());
    }

    @Test
    void submit_correctCredentials_opensSecurePage(){
        HerokuSecurePage herokuSecurePage = herokuLoginPage.loginWithCorrectCredentials();
        assertTrue(herokuSecurePage.isSuccessfullLoginMsgDisplayed());
    }

}