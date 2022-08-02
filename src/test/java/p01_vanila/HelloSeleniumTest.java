package p01_vanila;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class HelloSeleniumTest {
    private static WebDriver driver;

    @BeforeAll
    public static void warmup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void cleanup() {
        driver.quit();
    }

    @Test
    void homePageShouldLoad() {
        driver.get("http://the-internet.herokuapp.com");
    }

    @Test
    void homePageShouldLoadAndHaveCorrectTitle() {
    }

    /**
     * Test sprawdzający, czy po otworzeniu strony głównej http://the-internet.herokuapp.com
     * przeglądarka nie przekieruje użytkownika na inną stronę, tj. czy adres pozostanie taki sam
     */
    @Test
    void openingHomepageShouldNotRedirectAnywhere() {
    }

    /**
     * Test sprawdzający, czy na stronie głównej jest poprawny tytuł 'Welcome to the-internet'
     * tj. nagłówek na stronie o takiej zawartości
     */
    @Test
    void homePageShouldHaveProperContentTitle() {
        driver.get("http://the-internet.herokuapp.com");
        WebElement contentTitle1 = driver.findElement(By.tagName("h1"));
        WebElement contentTitle2 = driver.findElement(By.className("heading"));
        WebElement contentTitle3 = driver.findElement(By.cssSelector("h1"));
        WebElement contentTitle4 = driver.findElement(By.cssSelector(".heading"));
        WebElement contentTitle5 = driver.findElement(By.cssSelector("h1.heading"));

        WebElement contentTitle6 = driver.findElement(By.cssSelector("#content > h1"));
        WebElement contentTitle8 = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));

        WebElement contentTitle9 = driver.findElement(By.cssSelector("#content h1"));
        WebElement contentTitle10 = driver.findElement(By.xpath("//*[@id=\"content\"]//h1"));

        WebElement contentTitle7 = driver.findElement(By.xpath("/html/body/div[2]/div/h1"));

        assertEquals("Welcome to the-internet",contentTitle6.getText());
    }

    /**
     * Test sprawdzający, czy istnieje link do "Form Authentication" i czy naciśnięcie go
     * otwiera poprawną stronę
     */
    @Test
    void formAuthenticationLink_shouldBePresentAndRouteToCorrectPage() {

    }

    /**
     * Test sprawdzający, czy istnieje na stronie wstążka z napisem 'Fork me on Github'
     * oraz czy naciśnięcie jej przenosi na stronę projektu na Githubie
     */
    @Test
    void forkMeOnGithubRibbon_shouldBePresentAndRouteToCorrectPage() {
    }


}