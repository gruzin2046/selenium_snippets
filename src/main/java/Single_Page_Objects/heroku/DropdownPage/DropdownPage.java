package Single_Page_Objects.heroku.DropdownPage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/dropdown";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorDropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("dropdown", selectorPageHeader, "Dropdown List");
    }

    public void selectOption(int orderNumber) {
        WebElement dropdownElement = findElementQuietly(selectorDropdown);
        Select dropdown = new Select(dropdownElement);

        // There are three ways to use Select class:
        // 1. selectByValue
        dropdown.selectByValue(String.valueOf(orderNumber));
        // 2. selectByValue
        // dropdown.selectByIndex(orderNumber);
        // 3. selectBy
        // dropdown.selectByVisibleText("Option " + orderNumber);
    }

    public boolean optionIsSelected(int orderNumber) {
        WebElement dropdownElement = findElementQuietly(selectorDropdown);
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText().equals("Option " + orderNumber);
    }
}
