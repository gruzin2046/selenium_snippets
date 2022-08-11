package p02_pom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// Page Object
public class CustomersPage {
    private static final String PAGE_URL = "http://68.183.78.90:81/";

    private static final By SELECTOR_CUSTOMER_BLOCK = By.className("Customer-data");
    private static final By SELECTOR_CUSTOMER_NAME = By.cssSelector("span.customer-name");
    // div.Customers-list strong.Delete-button
    private static final By SELECTOR_DELETE_BUTTON = By.cssSelector("strong.Delete-button");

    private final WebDriver driver;

    public CustomersPage() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    // //*[@id="app"]/div[1]/header/div[2]/div/div/div/nav/ul/li[1]/section/div
    // /html/body/div[1]/div[1]/header/div[2]/div/div/div/nav/ul/li[1]/section/div
    // zwraca informację ilu jest klientów
    public int countCustomers() {
        List<WebElement> allCustomers = driver.findElements(SELECTOR_CUSTOMER_BLOCK);

        return allCustomers.size();
    }

    public List<String> findCustomersNames() {
//        List<WebElement> allCustomersNames = driver.findElements(SELECTOR_CUSTOMER_NAME);

//        List<String> names = new ArrayList<>();
//
//        for (WebElement elem : allCustomersNames) {
//            names.add(elem.getText());
//        }
//
//        return names;

        return driver.findElements(SELECTOR_CUSTOMER_NAME).stream().map(webElement -> webElement.getText().trim())
//              .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void deleteFirstCustomer() {
        driver.findElement(SELECTOR_DELETE_BUTTON).click();
    }
}