package Simple_Page_Objects.useful;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleTranslate {
    private static final String LOGIN_PAGE_URL = "https://translate.google.com";
    private static final By selectorPageHeader = By.xpath("//header[@id='gb']/div[2]//a[@aria-label='Google Translate']");
    private static final By cookiesBtn = By.xpath("//button[@aria-label='Accept all']//span[contains(text(),'Accept all')]");
    private static final By sourceTextArea = By.xpath("//textarea[@aria-label='Source text']");
    private static final By destTextArea = By.xpath("//c-wiz[@data-node-index='3;0']//c-wiz[@data-node-index='4;0']" +
            "//span[contains(@jsaction, 'mouseup')]/span/span");
    private static final By langChangeBtn = By.xpath("//button[@aria-label='More target languages']/div[3]");
    private static final By searchLanguagesInput = By.xpath("(//input[@aria-label='Search languages'])[2]");

    private final WebDriver driver;

    public GoogleTranslate(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return driver.getCurrentUrl().contains("translate.google")
                && driver.findElement(selectorPageHeader).getAttribute("title").equals("Google Translate");
    }

    public void acceptCookies() {
        try {
            driver.findElement(cookiesBtn).click();
        } catch (Exception ignored) {}
    }

    public void translateText(String s) {
        driver.findElement(sourceTextArea).sendKeys(s);
    }


    public String checkTranslation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(destTextArea));
        return driver.findElement(destTextArea).getText();
    }

    public void changeTranslationLanguage(String lang) {
        driver.findElement(langChangeBtn).click();
        driver.findElement(searchLanguagesInput).sendKeys(lang);
        driver.findElement(searchLanguagesInput).click();
        driver.findElement(searchLanguagesInput).sendKeys(Keys.ENTER);
    }
}
