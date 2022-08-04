package p01_vanila;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TheInternetLoginPageTest {
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
    void loginPageShouldLoad() {
        driver.get("http://the-internet.herokuapp.com/login");
    }

    @Test
    void loginPageShouldLoadAndHaveCorrectTitle() {
        driver.get("http://the-internet.herokuapp.com/login");
        String title = driver.getTitle();
        assertEquals(title,"The Internet");
    }

    /*
        1. Logowanie bez żadnych danych - "Your username is invalid!"
            - https://the-internet.herokuapp.com/login
        2. Logowanie z błędnymi danymi - "Your username is invalid!"
            - https://the-internet.herokuapp.com/login
        3. Logowanie z błędnym hasłem - "Your password is invalid!"
            - https://the-internet.herokuapp.com/login
        4. Logowanie poprawnymi danymi
            - https://the-internet.herokuapp.com/secure
            - Message: You logged into a secure area!
            - Content Title: Secure Area
     */

    @Test
    void submittingEmptyLoginFormRendersUsernameInvalidMsg(){
        driver.get("http://the-internet.herokuapp.com/login");
        WebElement loginButton1 = driver.findElement(By.cssSelector("#login > button"));
        /*
        WebElement loginButton2 = driver.findElement(By.xpath("//form[@id='login']/button"));
        WebElement loginButton3 = driver.findElement(By.xpath("//*[@id='login']/button") );
        WebElement form = driver.findElement(By.xpath("//form[@id='login']"));
        WebElement loginButton4 = driver.findElement(By.cssSelector("#login button"));
        WebElement loginButton5 = driver.findElement(By.xpath("//*[@id='login']//button"));
        */
        loginButton1.submit();
        WebElement flash = driver.findElement(By.id("flash"));
        String expectedMsg = "Your username is invalid!";

        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals("http://the-internet.herokuapp.com/login",driver.getCurrentUrl());
    }

    @Test
    void submitting_wrongLoginDataForm_rendersUsernameInvalidMsg(){
        driver.get("http://the-internet.herokuapp.com/login");
        WebElement loginButton1 = driver.findElement(By.cssSelector("#login > button"));
        // wpisz login i hasło (WebElement e ===> e.sendKeys("blednylogin"))

        loginButton1.submit();
        WebElement flash = driver.findElement(By.id("flash"));
        String expectedMsg = "Your username is invalid!";

        assertTrue(flash.isDisplayed());
        assertTrue(flash.getText().contains(expectedMsg));
        assertEquals("http://the-internet.herokuapp.com/login",driver.getCurrentUrl());
    }





}