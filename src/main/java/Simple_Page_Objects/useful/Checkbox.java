package Simple_Page_Objects.useful;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkbox {
    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/checkboxes";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorCheckboxes = By.xpath("//form[@id='checkboxes']/input");

    private final WebDriver driver;

    public Checkbox(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return driver.getCurrentUrl().contains("checkboxes")
                && driver.findElement(selectorPageHeader).getText().contains("Checkboxes");
    }

    public WebElement getCheckbox(int index) {
        return driver.findElements(selectorCheckboxes).get(index);
    }


}
