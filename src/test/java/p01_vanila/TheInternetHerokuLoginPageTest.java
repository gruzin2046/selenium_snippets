package p01_vanila;

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
        driver.get(LOGIN_PAGE_URL);
        String title = driver.getTitle();
        assertEquals(title, "The Internet");
    }

    @Test
    void submitting_wrongLoginDataForm_rendersUsernameInvalidMsg() {
        driver.get(LOGIN_PAGE_URL);
        fillUsername("asia@sasasa");
        fillPassword("     ł");
        submitForm();
        String flashMessage = getFlashMessage();

        assertTrue(flashMessage.contains("Your username is invalid!"));
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    // todo - wypadałoby poniżej zrobić podobny refactor jak w metodzie
    // submitting_wrongLoginDataForm_rendersUsernameInvalidMsg
    @Test
    void submitting_noData_rendersUsernameInvalidMsg() {
        driver.get(LOGIN_PAGE_URL);
        WebElement loginButton1 = getSubmitButtonElement();
        /*
        WebElement loginButton2 = driver.findElement(By.xpath("//form[@id='login']/button"));
        WebElement loginButton3 = driver.findElement(By.xpath("//*[@id='login']/button") );
        WebElement form = driver.findElement(By.xpath("//form[@id='login']"));
        WebElement loginButton4 = driver.findElement(By.cssSelector("#login button"));
        WebElement loginButton5 = driver.findElement(By.xpath("//*[@id='login']//button"));
        */
        loginButton1.submit();
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        String expectedMsg = "Your username is invalid!";

        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    void submitting_wrongPasw_rendersPasswordInvalidMsg() {
        driver.get(LOGIN_PAGE_URL);
        WebElement userName = getUsernameInputElement();
        WebElement userPasw = getPasswordInputElement();

        String expectedMsg = "Your password is invalid!";

        WebElement loginButton2 = getSubmitButtonElement();
        userName.sendKeys("tomsmith");
        userPasw.sendKeys("     ł");
        loginButton2.submit();
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    void submitting_correctCredentials_opensSecurePage() {
        driver.get(LOGIN_PAGE_URL);
        WebElement userName = getUsernameInputElement();
        WebElement userPasw = getPasswordInputElement();

        String expectedMsg = "You logged into a secure area!";

        WebElement loginButton = getSubmitButtonElement();
        userName.sendKeys("tomsmith");
        userPasw.sendKeys("SuperSecretPassword!");
        loginButton.submit();
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals(SECURE_PAGE_URL, driver.getCurrentUrl());
    }


    private void fillUsername(String username){
        driver.findElement(SELECTOR_USERNAME_INPUT).sendKeys(username);
    }
    private void fillPassword(String password){
        getPasswordInputElement().sendKeys(password);
    }
    private void submitForm(){
        getSubmitButtonElement().submit();
    }

    private String getFlashMessage(){
        return driver.findElement(SELECTOR_FLASH).getText();
    }

    private WebElement getSubmitButtonElement() {
        return driver.findElement(SELECTOR_LOGIN_BUTTON);
    }

    private WebElement getPasswordInputElement() {
        return driver.findElement(SELECTOR_PASSWORD_INPUT);
    }

    private WebElement getUsernameInputElement() {
        return driver.findElement(SELECTOR_USERNAME_INPUT);
    }


}