package Single_Page_Objects.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage(WebDriver driver, String URL) {
        this.driver = driver;
        openURL(URL);
    }

    protected boolean pageShouldLoad(String urlContains, By headerSelector, String headerContains) {
        return driver.getCurrentUrl().contains(urlContains)
                && findElementQuietly(headerSelector).getText().contains(headerContains);
    }

    // open page based on given URL
    protected void openURL(String URL) {
        driver.get(URL);
    }

    // find element
    protected WebElement findElementQuietly(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            System.out.printf("element: %s is not visible", locator.toString());
            return null;
        }
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    // click on element
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        findElementQuietly(locator).click();
    }

    // type
    protected void type(By locator, String text) {
        waitForVisibilityOf(locator, 5);
        findElementQuietly(locator).sendKeys(text);
    }

    // wait for
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
        wait.until(condition);
    }

    // wait for visibility
    protected void waitForVisibilityOf(By locator, Integer timeInSec) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeInSec);
        } catch (Exception e) {
            System.out.printf("element: %s is not visible", locator.toString());
            e.printStackTrace();
        }
    }

    protected void switchToNewWindowWithTitle(String windowTitle) {
        String firstWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Set<String> filtered = windows.stream()
                .filter(w -> !w.equals(firstWindow))
                .collect(Collectors.toSet());

        for (String window : filtered) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) {
                break;
            }
        }
    }

}
