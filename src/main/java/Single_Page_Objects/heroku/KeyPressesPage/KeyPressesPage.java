package Single_Page_Objects.heroku.KeyPressesPage;
import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class KeyPressesPage extends BasePage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/key_presses";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorResult = By.id("result");
    private static final By selectorBody = By.xpath("//body");

    public KeyPressesPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("key_presses", selectorPageHeader, "Key Presses");
    }

    // pressKey on By locator
    public void pressKey(Keys key) {
        findElementQuietly(selectorBody).sendKeys(key);
    }

    // pressKey with Action class
    public void pressKeyWithAction(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    public String getResultText() {
        return findElementQuietly(selectorResult).getText();
    }


}
