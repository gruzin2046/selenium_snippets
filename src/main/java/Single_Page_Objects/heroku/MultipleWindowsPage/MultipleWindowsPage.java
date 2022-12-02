package Single_Page_Objects.heroku.MultipleWindowsPage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindowsPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/windows";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorLink = By.xpath("//div[@id='content']//a");

    public MultipleWindowsPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("windows", selectorPageHeader, "Opening a new window");
    }

    public void openNewWindow() {
        click(selectorLink);
    }

    public NewWindowPage switchToNewPageWindow() {
        switchToNewWindowWithTitle("New Window");
        return new NewWindowPage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

