package Single_Page_Objects.heroku.SecurePage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Single_Page_Objects.heroku.LoginPage.HerokuLoginPage.SELECTOR_FLASH;

public class HerokuSecurePage extends BasePage {
    private static final String SECURE_PAGE_URL = "http://the-internet.herokuapp.com/secure";

    public HerokuSecurePage(WebDriver driver, boolean reload) {
        super(driver);
        if (reload) {
            driver.get(SECURE_PAGE_URL);
        }
        else if (!driver.getCurrentUrl().equals(SECURE_PAGE_URL)) {
            throw new RuntimeException("Cannot initialize HerokuSecurePage," +
                    " as the driver points to a different page");
        }
    }

    public boolean isSuccessfulLoginMsgDisplayed() {
        WebElement flash = findElementQuietly(SELECTOR_FLASH);
        String flashText = flash.getText();

        // isDisplayed() - check if element is not only present but also visible
        return flash.isDisplayed() &&
                flashText.toLowerCase().contains("You logged into a secure area!".toLowerCase());
    }
}
