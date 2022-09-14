package Simple_Page_Objects.gateOpener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class GateOpener {

    private static final String PAGE_URL = "http://68.183.78.90:81/gate-opener";
    private static final By SELECTOR_TITLE_HEADER = By.cssSelector("h1");
    private static final By SELECTOR_GATE_OPENER_BUTTONS = By.cssSelector(".Gate-opener button");
    private static final By SELECTOR_VALVES = By.cssSelector(".Valves [class^=Valve]");

    private final WebDriver driver;

    public GateOpener() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    public void quitDriver() {
        driver.quit();
    }

    public boolean isDisplayed() {
        WebElement title = driver.findElement(SELECTOR_TITLE_HEADER);
        String pageUrl = driver.getCurrentUrl();

        return pageUrl.contains("/gate-opener") && title.getText().contains("Gate opener");
    }

    public List<WebElement> findButtons() {
        return driver.findElements(SELECTOR_GATE_OPENER_BUTTONS);
    }

    public List<WebElement> findValves() {
        return driver.findElements(SELECTOR_VALVES);
    }

    public int countButtons() {
        return findButtons().size();
    }

    public int countValves() {
        return findValves().size();
    }

    public WebElement findButton(int indexNumber) {
        return findButtons().get(indexNumber);
    }

    public void clickButton(int indexNumber) {
        findButton(indexNumber).click();
    }

    public WebElement findValve(int indexNumber) {
        return findValves().get(indexNumber);
    }

    public boolean isButtonEnabled(int indexNumber) {
        return findButton(indexNumber).isEnabled();
    }

    public boolean areButtonsEnabled(int[] indexesToCheck) {
        return Arrays.stream(indexesToCheck)
                .mapToObj(this::isButtonEnabled)
                .allMatch(x -> x);
    }

    public boolean isValveOpen(int indexNumber) {
        return findValve(indexNumber).getAttribute("class").equals("Valve opened");
    }

    public String getValveColor(int indexNumber) {
        return findValve(indexNumber).getCssValue("background-color");
    }

    public boolean areValvesRed(int[] valvesIndexesToCheck) {
        return Arrays.stream(valvesIndexesToCheck)
                .mapToObj(this::getValveColor)
                .allMatch(e -> e.equals("rgba(139, 0, 0, 1)"));
    }

    public boolean areValvesOpen(int[] valvesIndexesToCheck) {
        return Arrays.stream(valvesIndexesToCheck)
                .mapToObj(this::isValveOpen)
                .allMatch(e -> e);
    }

    public WebDriverWait createWebDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }

    public void waitUntilButtonsWillBeEnabled(int[] buttonsIndexesToCheck) {
        WebDriverWait webDriverWait = createWebDriverWait(4);

        Arrays.stream(buttonsIndexesToCheck)
                .mapToObj(this::findButton)
                .forEach(btn -> webDriverWait.until(ExpectedConditions.elementToBeClickable(btn)));
    }

    public void waitUntilButtonWillNotBeEnabled(int buttonIndexToCheck) {
        WebDriverWait webDriverWait = createWebDriverWait(4);
        webDriverWait.until(driver -> isButtonEnabled(buttonIndexToCheck));
    }

    public void waitUntilValvesWillBeClosed(int[] valvesIndexesToCheck) {
        WebDriverWait webDriverWait = createWebDriverWait(4);
        webDriverWait.until(driver -> areValvesRed(valvesIndexesToCheck) &&
                !areValvesOpen(valvesIndexesToCheck));
    }

    public void waitUntilValveWillBeOpen(int valveIndexToCheck) {
        WebDriverWait webDriverWait = createWebDriverWait(4);
        webDriverWait.until(driver -> isValveOpen(valveIndexToCheck) &&
                getValveColor(valveIndexToCheck).equals("rgba(34, 139, 34, 1)"));
    }
}
