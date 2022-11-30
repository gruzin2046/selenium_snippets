package Single_Page_Objects.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {

        switch (browser) {

            case "firefox":
                System.setProperty("webdriver.geco.driver", System.getProperty("user.home")
                        + "/tools/webdrivers/geckodriver.exe");
                System.setProperty("webdriver.geco.whitelistedIps", "");
                driver.set(new FirefoxDriver());
                break;

            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.home")
                        + "/tools/webdrivers/chromedriver.exe");
                System.setProperty("webdriver.chrome.whitelistedIps", "");
                driver.set(new ChromeDriver());
                break;
        }

        return driver.get();
    }
}
