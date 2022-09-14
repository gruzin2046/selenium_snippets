<<<<<<< HEAD:src/test/java/p02_pom/heroku/HerokuWaitTest.java
package p02_pom.heroku;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
=======
package Simple_Page_Objects.heroku;

>>>>>>> ws4.1/Albert:src/test/java/Simple_Page_Objects/heroku/HerokuWaitTest.java
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

<<<<<<< HEAD:src/test/java/p02_pom/heroku/HerokuWaitTest.java
=======
import java.time.Duration;

>>>>>>> ws4.1/Albert:src/test/java/Simple_Page_Objects/heroku/HerokuWaitTest.java
public class HerokuWaitTest {
    private static WebDriver driver;

    @BeforeAll
    public static void warmup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void testCheckboxDeleted() {
        By deleteButtonSelector = By.cssSelector("#checkbox-example button");
        By checkBoxSelector = By.cssSelector("#checkbox input[type='checkbox']");

        WebElement deleteButton = driver.findElement(deleteButtonSelector);
        deleteButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.invisibilityOfElementLocated(checkBoxSelector));
    }

    @Test
    public void testIfCheckboxCanBeReverted() {
        By deleteButtonSelector = By.cssSelector("#checkbox-example button");
        By checkBoxSelectorBefore = By.cssSelector("#checkbox input[type='checkbox']");
        By checkBoxSelectorAfter = By.cssSelector("input#checkbox[type='checkbox']");

        WebElement deleteButton = driver.findElement(deleteButtonSelector);
        deleteButton.click();

<<<<<<< HEAD:src/test/java/p02_pom/heroku/HerokuWaitTest.java
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfElementLocated(checkBoxSelectorBefore));
=======
         new WebDriverWait(driver, Duration.ofSeconds(30))
                 .until(ExpectedConditions.invisibilityOfElementLocated(checkBoxSelectorBefore));
>>>>>>> ws4.1/Albert:src/test/java/Simple_Page_Objects/heroku/HerokuWaitTest.java

        deleteButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(checkBoxSelectorAfter));
    }
}
