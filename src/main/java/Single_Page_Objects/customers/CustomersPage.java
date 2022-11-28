package Single_Page_Objects.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

// Page Object
public class CustomersPage {
    private static final String PAGE_URL = "http://68.183.78.90:81/customers";

    private static final By SELECTOR_CUSTOMER_BLOCK = By.className("Customer-data");
    private static final By SELECTOR_CUSTOMER_NAME = By.cssSelector("span.customer-name");
    // div.Customers-list strong.Delete-button
    private static final By SELECTOR_DELETE_BUTTON = By.cssSelector("strong.Delete-button");
    private static final By SELECTOR_USERNAME_INPUT = By.cssSelector("#add-customer-input");
    private static final By SELECTOR_ADD_BUTTON = By.cssSelector("#add-customer-button");

    private final WebDriver driver;

    public CustomersPage() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    public void quitDriver() {
        driver.quit();
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

    public boolean usernameInputExists() {
        // v1 - użycie findElements - zwraca pustą listę gdy nie istnieje żaden element
        List<WebElement> allUsernameInputs = driver.findElements(SELECTOR_USERNAME_INPUT);
        return !allUsernameInputs.isEmpty();

        // v2 -
//        try {
//            driver.findElement(SELECTOR_USERNAME_INPUT);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
    }

    public String getCustomerInputValue(){
       return driver.findElement(SELECTOR_USERNAME_INPUT).getAttribute("value");
    }

    public void enterUserData(String name) {
        WebElement usernameInput = driver.findElement(SELECTOR_USERNAME_INPUT);
        usernameInput.clear();
        usernameInput.click();

        if (name.isEmpty()) {
            usernameInput.sendKeys(" " + Keys.BACK_SPACE);
        } else {
            usernameInput.sendKeys(name);
        }
    }

    public void addUser(String name) {
        enterUserData(name);

        WebElement addUserButton = driver.findElement(SELECTOR_ADD_BUTTON);
        addUserButton.click();
    }

    public boolean isAddCustomerButtonEnabled() {
        WebElement addUserButton = driver.findElement(SELECTOR_ADD_BUTTON);
        return addUserButton.isEnabled();
    }

    public void deleteUser(int orderNumber) {
        String selector = "//div[@class='Customer-data'][" + orderNumber + "]//strong[@class='Delete-button']";

        driver.findElement(By.xpath(selector)).click();
    }

    public List<Customer> findAllCustomers() {
//        List<WebElement> allCustomerBlocks = driver.findElements(SELECTOR_CUSTOMER_BLOCK);
//        // v1 - klasyczna (bez java 8)
//
//        List<Customer> customers = new ArrayList<>();
//
//        for (WebElement customerBlock : allCustomerBlocks) {
//            WebElement customerIdElement = customerBlock.findElement(By.className("customer-id"));
//            String customerIdAsText = customerIdElement.getText();
//            long customerId = Long.parseLong(customerIdAsText);
//
//            WebElement customerNameElement = customerBlock.findElement(By.className("customer-name"));
//            String customerNameAsText = customerNameElement.getText();
//
//            Customer customerCreatedFromWebElement = new Customer(customerId, customerNameAsText);
//            customers.add(customerCreatedFromWebElement);
//        }
//
//        return customers;

        // v2 - stream
        return driver.findElements(SELECTOR_CUSTOMER_BLOCK).stream()
                .map(customerBlock -> {
                    long id = Long.parseLong(customerBlock.findElement(By.className("customer-id")).getText());

                    String name = customerBlock.findElement(By.className("customer-name")).getText();

                    return new Customer(id, name);
                }).collect(Collectors.toList());
    }


    public void deleteAllUsers() {
        int customersNumber = countCustomers();

        for (int i = 0; i < customersNumber; i++) {
            deleteFirstCustomer();
        }
    }

    /***
     * //*[@class="Customers-list"]/div/div/span[@class="customer-id"][contains(text(),'"+ id +"')]/../../strong[@class="Delete-button"]"
     */


    // //div[@class="Customers-list"]//strong[@class='Delete-button']


    //  //div[@class='Customer-data'][4]//strong[@class='Delete-button']


    // XPATH - wyszukanie N-tego przycisku usuń
    // //div[@class='Customer-data'][4]//strong[@class='Delete-button']

    // CSS - wyszukanie N-tego przycisku usuń
    // div.Customers-list > div:nth-child(2) > strong
    // div.Customers-list > div.Customer-data:nth-child(4) strong.Delete-button

}
