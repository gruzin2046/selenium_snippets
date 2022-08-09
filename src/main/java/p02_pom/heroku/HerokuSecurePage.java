package p02_pom.heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static p02_pom.heroku.HerokuLoginPage.SELECTOR_FLASH;

public class HerokuSecurePage {
    private static final String LOGIN_PAGE_URL = "http://the-internet.herokuapp.com/secure";
    private WebDriver driver;

    public HerokuSecurePage(WebDriver driver, boolean reload){
        this.driver = driver;
        if (reload) {
            driver.get(LOGIN_PAGE_URL);
        }
        else if (!driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
            throw new RuntimeException("Cannot initialize HerokuSecurePage," +
                    " as the driver points to a different page");
        }
    }

    public boolean isSuccessfullLoginMsgDisplayed() {
        WebElement flash = driver.findElement(SELECTOR_FLASH);
        String flashText = flash.getText();
        return flash.isDisplayed() &&
                flashText.toLowerCase()
                        .contains("You logged into a secure area!".toLowerCase());
    }
}
