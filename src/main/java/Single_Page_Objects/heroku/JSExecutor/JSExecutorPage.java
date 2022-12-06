package Single_Page_Objects.heroku.JSExecutor;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSExecutorPage extends BasePage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h1");
    JavascriptExecutor jsExec = (JavascriptExecutor) driver;

    public JSExecutorPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("herokuapp", selectorPageHeader, "Welcome to the-internet");
    }

    public void scrollToPageBottom() {
        jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public By getPageSelector() {
        return selectorPageHeader;
    }

    public void scrollToWebElement(By selector) {
        jsExec.executeScript("arguments[0].scrollIntoView(true);", findElementQuietly(selector));
    }

    // sleep used only to see what selenium is doing :)
    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
    }

    // window.scrollTo(x, y)
    // - x, y  -> coordinates in pixels (f.e. 0,0 are coordinates for top of the page)

    // window.scrollBy(x, y)
    // - x, y  -> dimension in pixels (f.e. to move to the right: 50, to the left: -50)

    // scrollIntoView()
    // 1. Prepare JS script
    // 2. Pass WebElement as argument after the script
    // jsExec.executeScript("arguments[0].scrollIntoView(true);",findElementQuietly(selectorPageHeader));
}
