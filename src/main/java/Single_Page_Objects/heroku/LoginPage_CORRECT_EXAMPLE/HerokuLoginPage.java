package Single_Page_Objects.heroku.LoginPage_CORRECT_EXAMPLE;

import Single_Page_Objects.Base.BasePage;
import Single_Page_Objects.heroku.SecurePage.HerokuSecurePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HerokuLoginPage extends BasePage {
    private static final String LOGIN_PAGE_URL = "http://the-internet.herokuapp.com/login";

    static final String CORRECT_USERNAME = "tomsmith";
    static final String CORRECT_PASSWORD = "SuperSecretPassword!";

    private static final By SELECTOR_LOGIN_BUTTON = By.cssSelector("#login > button");
    private static final By SELECTOR_USERNAME_INPUT = By.id("username");
    private static final By SELECTOR_PASSWORD_INPUT = By.id("password");
    public static final By SELECTOR_FLASH = By.id("flash");

    // I. Refactor of code using Base test methods
    public HerokuLoginPage(WebDriver driver) {
        // 1. Pass driver to BasePage constructor
        // this.driver = driver;

        // 2. openURL() method inside BasePage instead of driver.get()
        // driver.get(LOGIN_PAGE_URL);

        super(driver, LOGIN_PAGE_URL);
    }

    public HerokuSecurePage loginWithCorrectCredentials() {
        fillUsername(CORRECT_USERNAME);
        fillPassword(CORRECT_PASSWORD);
        submitForm();

        return new HerokuSecurePage(driver, false);
    }

    public void loginWithIncorrectCredentials() {
        fillUsername("xyz");
        fillPassword("abc");
        submitForm();
    }

    public HerokuLoginPage fillUsername(String username) {
        // 3. type() instead of sendKeys()
        // driver.findElement(SELECTOR_USERNAME_INPUT).sendKeys(username);
        type(SELECTOR_USERNAME_INPUT, username);
        return this;
    }

    public HerokuLoginPage fillPassword(String password) {
        type(SELECTOR_PASSWORD_INPUT, password);
        return this;
    }

    public void submitForm() {
        findElementQuietly(SELECTOR_LOGIN_BUTTON).submit();
    }

    public boolean isPasswordIncorrectMsgDisplayed() {
        // 4. findElementQuietly() instead of find()
        WebElement flash = findElementQuietly(SELECTOR_FLASH);
        // WebElement flash = driver.findElement(SELECTOR_FLASH);
        String flashText = flash.getText();
        return flash.isDisplayed() &&
                flashText.toLowerCase()
                .contains("Your password is invalid!".toLowerCase());
    }

    public boolean isUsernameIncorrectMsgDisplayed() {
        WebElement flash = findElementQuietly(SELECTOR_FLASH);
        String flashText = flash.getText();
        return flash.isDisplayed() &&
                flashText.toLowerCase().contains("Your username is invalid!".toLowerCase());
    }

}
