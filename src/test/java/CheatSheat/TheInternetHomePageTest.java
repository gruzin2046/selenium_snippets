package CheatSheat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class TheInternetHomePageTest {
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
        driver.get("http://the-internet.herokuapp.com");
        String title = driver.getTitle();
        assertEquals(title,"The Internet");
    }

    /**
     * Test sprawdzający, czy po otworzeniu strony głównej http://the-internet.herokuapp.com
     * przeglądarka nie przekieruje użytkownika na inną stronę, tj. czy adres pozostanie taki sam
     */
    @Test
    void openingHomepageShouldNotRedirectAnywhere() {
        driver.get("http://the-internet.herokuapp.com");
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://the-internet.herokuapp.com/",currentUrl);
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
        driver.get("http://the-internet.herokuapp.com");
        // https://the-internet.herokuapp.com/login
        // copied from Dev Tools
        //#content > ul > li:nth-child(21) > a
        //*[@id="content"]/ul/li[21]/a
        WebElement link1 = driver.findElement(By.linkText("Form Authentication"));
        WebElement link2 = driver.findElement(By.cssSelector("a[href='/login']"));
        WebElement link3 = driver.findElement(By.xpath("//a[@href='/login']"));

        link1.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://the-internet.herokuapp.com/login",currentUrl);

    }

    /**
     * Test sprawdzający, czy istnieje na stronie wstążka z napisem 'Fork me on Github'
     * oraz czy naciśnięcie jej przenosi na stronę projektu na Githubie
     */
    @Test
    void forkMeOnGithubRibbon_shouldBePresentAndRouteToCorrectPage() {
        driver.get("http://the-internet.herokuapp.com");
        WebElement img1 = driver.findElement(By.cssSelector("img[src='/img/forkme_right_green_007200.png']"));
        WebElement img2 = driver.findElement(By.cssSelector("img[src$='png']"));
        WebElement img3 = driver.findElement(By.cssSelector("img[alt='Fork me on GitHub']"));

        // "https://github.com/tourdedave/the-internet"

        img3.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/tourdedave/the-internet",currentUrl);
        // przy małym rozmiarze okna element jest nieklikalny


    }


}