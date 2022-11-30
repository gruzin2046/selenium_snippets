package CheatSheat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    1. Logowanie bez żadnych danych - "Your username is invalid!"
        - https://the-internet.herokuapp.com/login
    2. Logowanie z błędnymi danymi - "Your username is invalid!"
        - https://the-internet.herokuapp.com/login
    3. Logowanie z błędnym hasłem - "Your password is invalid!"
        - https://the-internet.herokuapp.com/login
    4. Logowanie poprawnymi danymi
        - https://the-internet.herokuapp.com/secure
        - Message: You logged into a secure area!
        - Content Title: Secure Area
 */
class TheInternetHerokuLoginPageTest {
    private static final String LOGIN_PAGE_URL = "http://the-internet.herokuapp.com/login";
    public static final String SECURE_PAGE_URL = "http://the-internet.herokuapp.com/secure";

    public static final By SELECTOR_LOGIN_BUTTON = By.cssSelector("#login > button");
    public static final By SELECTOR_USERNAME_INPUT = By.id("username");
    public static final By SELECTOR_PASSWORD_INPUT = By.id("password");
    public static final By SELECTOR_FLASH = By.id("flash");
    private static WebDriver driver;

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

    @Test
    void loginPageShouldLoad() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Test
    void loginPageShouldLoadAndHaveCorrectTitle() {
        openLoginPage();
        String title = driver.getTitle();
        assertEquals(title, "The Internet");
    }

    @Test
    void submitting_wrongLoginDataForm_rendersUsernameInvalidMsg() {
        openLoginPage();
        fillAndSendForm("asia@sasasa","     ł");
        checkFlashMessage("Your username is invalid!",LOGIN_PAGE_URL);
    }

    // todo - wypadałoby poniżej zrobić podobny refactor jak w metodzie
    // submitting_wrongLoginDataForm_rendersUsernameInvalidMsg
    @Test
    void submitting_noData_rendersUsernameInvalidMsg() {
        openLoginPage();
        submitForm();
        checkFlashMessage("Your username is invalid!", LOGIN_PAGE_URL);
    }

    @Test
    void submitting_wrongPasw_rendersPasswordInvalidMsg() {
        openLoginPage();
        fillAndSendForm("tomsmith", "     ł");
        checkFlashMessage("Your password is invalid!", LOGIN_PAGE_URL);
    }

    @Test
    void submitting_correctCredentials_opensSecurePage() {
        openLoginPage();
        fillAndSendForm("tomsmith","SuperSecretPassword!" );
        checkFlashMessage("You logged into a secure area!", SECURE_PAGE_URL);
    }

    private void checkFlashMessage(String expectedMsg, String pageAddress) {
        WebElement flash = getFlashElement();
        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals(pageAddress, getCurrUrl());
    }

    private void fillAndSendForm(String user, String password) {
        fillUsername(user);
        fillPassword(password);
        submitForm();
    }

    private void openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
    }

    private String getCurrUrl() {
        return driver.getCurrentUrl();
    }

    private void fillUsername(String username){
        getUsernameInputElement().sendKeys(username);
    }

    private void fillPassword(String password){
        getPasswordInputElement().sendKeys(password);
    }

    private void submitForm(){
        getSubmitButtonElement().submit();
    }

    private String getFlashMessage(){
        return getFlashElement().getText();
    }

    private WebElement getSubmitButtonElement() {
        return driver.findElement(SELECTOR_LOGIN_BUTTON);
    }

    private WebElement getFlashElement() {
        return driver.findElement(SELECTOR_FLASH);
    }

    private WebElement getPasswordInputElement() {
        return driver.findElement(SELECTOR_PASSWORD_INPUT);
    }

    private WebElement getUsernameInputElement() {
        return driver.findElement(SELECTOR_USERNAME_INPUT);
    }

}