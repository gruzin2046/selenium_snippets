package Single_Page_Objects.heroku.CheckboxesPage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/checkboxes";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorCheckboxes = By.xpath("//form[@id='checkboxes']/input");

    public CheckboxesPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("checkboxes", selectorPageHeader, "Checkboxes");
    }

    public List<WebElement> getCheckboxes() {
        return findElements(selectorCheckboxes);
    }

    public WebElement getCheckbox(int index) {
        return getCheckboxes().get(index);
    }

    public void selectAllCheckboxes() {
        getCheckboxes().stream()
                .filter(e -> !e.isSelected())
                .forEach(WebElement::click);
    }

    public boolean checkIfAllCheckboxesAreSelected() {
        return getCheckboxes().stream()
                .allMatch(WebElement::isSelected);
    }
}