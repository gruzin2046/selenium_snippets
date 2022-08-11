package WorkShopAddUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Customer {
    By customerDiv = By.className("Customer-data");
    By customerId = By.className("customer-id");
    By customerName = By.className("customer-name");
    By deleteButton = By.className("Delete-button");
    private WebDriver driver;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    String id;
    String name;

    public Customer (String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

    }


    public By getCustomerDiv() {
        return customerDiv;
    }

    public By getCustomerId() {
        return customerId;
    }

    public By getCustomerName() {
        return customerName;
    }

    public By getDeleteButton() {
        return deleteButton;
    }
}
