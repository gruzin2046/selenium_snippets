package Tests_without_Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {

//        WebDriverManager.edgedriver().setup();
//        WebDriver driver = new EdgeDriver();

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.home")+"/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com");

        // pobranie tytułu strony (opis w zakładce)
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        driver.close();
    }
}
