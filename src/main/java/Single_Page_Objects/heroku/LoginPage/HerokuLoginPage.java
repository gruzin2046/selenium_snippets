package Single_Page_Objects.heroku.LoginPage;

import Single_Page_Objects.heroku.HerokuSecurePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

  // Klasa modelująca stronę http://the-internet.herokuapp.com/login

public class HerokuLoginPage {
    private static final String LOGIN_PAGE_URL = "http://the-internet.herokuapp.com/login";

    static final String CORRECT_USERNAME = "tomsmith";
    static final String CORRECT_PASSWORD = "SuperSecretPassword!";

    private static final By SELECTOR_LOGIN_BUTTON = By.cssSelector("#login > button");
    private static final By SELECTOR_USERNAME_INPUT = By.id("username");
    private static final By SELECTOR_PASSWORD_INPUT = By.id("password");
    public static final By SELECTOR_FLASH = By.id("flash");

    private WebDriver driver;

    public HerokuLoginPage(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
    }

    public HerokuSecurePage loginWithCorrectCredentials(){
        driver.findElement(SELECTOR_USERNAME_INPUT).sendKeys(CORRECT_USERNAME);
        driver.findElement(SELECTOR_PASSWORD_INPUT).sendKeys(CORRECT_PASSWORD);
        submitForm();

        return new HerokuSecurePage(driver, false);
    }

    public void loginWithIncorrectCredentials() {
        driver.findElement(SELECTOR_USERNAME_INPUT).sendKeys("babla");
        driver.findElement(SELECTOR_PASSWORD_INPUT).sendKeys("blabla");
        submitForm();
    }

    public HerokuLoginPage fillUsername(String username) {
        driver.findElement(SELECTOR_USERNAME_INPUT).sendKeys(username);
        return this;
    }

    public HerokuLoginPage fillPassword(String password) {
        driver.findElement(SELECTOR_PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public void submitForm() {
        driver.findElement(SELECTOR_LOGIN_BUTTON).submit();
    }

    public boolean isPasswordIncorrectMsgDisplayed() {
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        String flashText = flash.getText();
        return flash.isDisplayed() &&
                flashText.toLowerCase()
                .contains("Your password is invalid!".toLowerCase());
    }

    public boolean isUsernameIncorrectMsgDisplayed() {
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        String flashText = flash.getText();
        return flash.isDisplayed() &&
                flashText.toLowerCase()
                        .contains("Your username is invalid!".toLowerCase());
    }

}
