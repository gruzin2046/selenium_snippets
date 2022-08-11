package WorkShopAddUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddCustomer {
    By addCustomerInput = By.id("add-customer-input");
    By submitAddCustomer = By.id("add-customer-button");
    List<Customer> customers;
    String url = "http://68.183.78.90:81";
    private WebDriver driver;

    public AddCustomer(WebDriver driver){
        this.driver = driver;
        driver.get("http://68.183.78.90:81");
    }


    public By getAddCustomerInput() {
        return addCustomerInput;
    }

    public void setCustomers() {
        Customer customer = new Customer();
        List<Customer> customers = null;
        List<WebElement> usersOnThePage = driver.findElements(customer.getCustomerDiv());
        for (WebElement c : usersOnThePage) {
            customers.add(new Customer(driver.findElement(customer.customerId).getText(), driver.findElement(customer.customerName).getText()));
        }
    }

    public By getSubmitAddCustomer() {
        return submitAddCustomer;
    }

    public List<Customer> getCustomers() {

        return customers;
    }


    public int howManyUsersOnPage() {
        Customer customer = new Customer();
        List<WebElement> usersOnThePage = driver.findElements(customer.getCustomerDiv());
        return usersOnThePage.size();
    }

    public void addUser(String user) {
        driver.findElement(addCustomerInput).sendKeys(user);
        driver.findElement(submitAddCustomer).click();
    }

    public boolean isUserOnThePage(String user) {
        for (Customer c : customers) {
            if (c.name == user) return true;
            break;
        }
        return false;
    }
}
