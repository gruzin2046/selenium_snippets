package Single_Page_Objects.heroku.AlertsPage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage extends BasePage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorResult = By.id("result");


    public AlertsPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("javascript_alerts", selectorPageHeader, "JavaScript Alerts");
    }

    private By btnLocatorBuilder(String alertName) {
        return By.xpath(String.format("//button[contains(text(), '%s')]", alertName));
    }

    public void openAlert(String alertName) {
        findElementQuietly(btnLocatorBuilder(alertName)).click();
    }

    private void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    private Alert switchToAlert() {
        waitForAlert();
        return driver.switchTo().alert();
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    public void typeInAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    public void acceptAlert() {
        switchToAlert().accept();
    }

    public void dismissAlert() {
        switchToAlert().dismiss();
    }

    public String getResultMessage() {
        return findElementQuietly(selectorResult).getText();
    }
}
