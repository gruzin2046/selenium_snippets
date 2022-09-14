package Simple_Page_Objects.heroku;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Simple_Page_Objects.heroku.HerokuLoginPage.SELECTOR_FLASH;

// klasa modelująca stronę http://the-internet.herokuapp.com/secure
public class HerokuSecurePage {
    private static final String LOGIN_PAGE_URL = "http://the-internet.herokuapp.com/secure";
    private WebDriver driver;

    public HerokuSecurePage(WebDriver driver, boolean reload) {
        this.driver = driver;
//        if (reload) {
//            driver.get(LOGIN_PAGE_URL);
//        }
//        else if (!driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
//            throw new RuntimeException("Cannot initialize HerokuSecurePage," +
//                    " as the driver points to a different page");
//        }
    }

    public boolean isSuccessfullLoginMsgDisplayed() {
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        String flashText = flash.getText();

        // jeśli element nie jest widoczny - zwróć false
        if(!flash.isDisplayed()){
            return false;
        }

        String flashTextInLowerCase = flashText.toLowerCase();
        String expectedTextInElementToLowerCase = "You logged into a secure area!".toLowerCase();

        return flashTextInLowerCase.contains(expectedTextInElementToLowerCase);


        // isDisplayed() - sprawdzenie czy element nie jest tylko i wyłącznie obecny w kodzie strony, ale również widoczny ("nieukryty")

//        return flash.isDisplayed() && flashText.toLowerCase().contains("You logged into a secure area!".toLowerCase());
    }
}
