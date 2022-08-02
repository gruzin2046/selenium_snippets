package p01_vanila;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheatSheat {
    public void examples() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.home")+"/tools/webdrivers/chromedriver");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        WebDriver driver = new ChromeDriver();

        // otworzenie konkretnej strony
        driver.get("https://www.programuj.pl");

        // zamknięcie przeglądarki
        driver.quit(); // wszystkich okien i zakładek
        driver.close(); // obecnie aktywne okno/zakladka

        // === PODSTAWOWE INFORMACJE O STRONIE ===
        // pobranie tytułu strony (tytuł karty w przeglądarce)
        String pageTitle = driver.getTitle();
        // sprawdzenie otworzonego adresu
        String pageCurrentUrl = driver.getCurrentUrl();
        // pobranie kodu HTML strony
        String pageSourceInHtml = driver.getPageSource();

        // === SELEKTORY ===
        // selektory - sposoby na wyszukiwanie elementu/elementów na stronie
        // selektory predefiniowane (proste)
        By byTagNameLocator = By.tagName("nazwa-tagu"); // <nazwa-tagu></nazwa-tagu>
        By byIdLocator = By.id("button-search"); // <div id="identyfikator"></div>
        By byNameLocator = By.name("form"); // <div name="form">
        By registerButtonSelector = By.className("button-search"); // <div class="nazwa-klasy"></div>
        By byLinkTextLocator = By.linkText("Zobacz opinie"); // <a>Tekst</a>

        // selektory zaawansowane
        By byCssLocator = By.cssSelector("div.button-search");
        By byXPathLocator = By.xpath("//*[contains(text(), 'Login')]");

        // === POBIERANIE ELEMENTU/ELEMENTÓW ZE STRONY ===


        // Uwaga! Sam fakt, że element istnieje na stronie (znajduje się w kodzie HTML)
        // nie oznacza, że jest on widoczny/aktywny!

        // Selenium WebElement
        WebElement buttonAsWebElement = driver.findElement(byIdLocator);
        buttonAsWebElement.click();

        // === METODY/FUNKCJE WEBELEMENTÓW ===
        // WebElement method

        WebElement pageElement = driver.findElement(By.tagName("h1"));

        Dimension size = pageElement.getSize();
        String color = pageElement.getCssValue("background-color");
        pageElement.clear();
        pageElement.click();
        pageElement.sendKeys("Hello");
        String elementText = pageElement.getText();
        String elementLink = pageElement.getAttribute("href");

        boolean displayed = pageElement.isDisplayed();
        boolean selected = pageElement.isSelected();
        boolean enabled = pageElement.isEnabled();
        // opublikowanie (wysłanie) formularza - może być wykonane na dowolnym elemencie
        // formularza
        pageElement.submit();

        // waity - Selenium
        // waity - imlicit
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // waity - explicit
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(registerButtonSelector));

        // warunki parametryzowane selektorami
        ExpectedCondition<WebElement> presenceCondition = ExpectedConditions
                .presenceOfElementLocated(registerButtonSelector);
        ExpectedCondition<WebElement> clickableCondition = ExpectedConditions
                .elementToBeClickable(By.id("register-button"));
        ExpectedCondition<WebElement> visibilityCondition = ExpectedConditions
                .visibilityOfElementLocated(registerButtonSelector);
        ExpectedCondition<Boolean> invisibilityCondition = ExpectedConditions
                .invisibilityOfElementLocated(registerButtonSelector);

        // warunki parametryzowane istniejącymi WebElementami
        WebElement registerButton = driver.findElement(registerButtonSelector);
        ExpectedCondition<WebElement> clickableWebElementCondition = ExpectedConditions
                .elementToBeClickable(registerButton);
        ExpectedCondition<WebElement> visibilityWebElementCondition = ExpectedConditions.visibilityOf(registerButton);
        ExpectedCondition<Boolean> invisibilityWebElementCondition = ExpectedConditions.invisibilityOf(registerButton);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.textToBe(By.tagName("h2"), "Hello World"));

        // waity - fluent
        WebElement webElement = webDriverWait.ignoring(NotFoundException.class)
                .pollingEvery(Duration.of(2, ChronoUnit.HOURS)).withTimeout(Duration.of(100, ChronoUnit.SECONDS))
                .ignoring(Exception.class)
                .until(ExpectedConditions.visibilityOf(pageElement));
        // new FluentWait<WebDriver>(driver)
        // .ignoring()

        // options
        WebDriver.Options manage = driver.manage();

        manage.deleteAllCookies();
        manage.addCookie(new Cookie("cookie-name", "cookie-value"));
        Set<Cookie> cookies = manage.getCookies();

        manage.timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        manage.timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        manage.timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        manage.window().maximize();
        manage.window().setSize(new Dimension(500, 200));
        Dimension windowsSize = manage.window().getSize();
    }

}
