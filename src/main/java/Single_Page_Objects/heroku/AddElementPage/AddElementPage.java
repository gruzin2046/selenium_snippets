package Single_Page_Objects.heroku.AddElementPage;

import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddElementPage extends BasePage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/add_remove_elements/";
    private static final By SELECTOR_ADD_ELEMENT_BUTTON = By.xpath("//button[text()='Add Element']");
    private static final By SELECTOR_DELETE_BUTTON = By.className("added-manually");
    private static final By SELECTOR_TITLE_HEADER = By.xpath("//h3[text()='Add/Remove Elements']");

    public AddElementPage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public void addElement() {
        click(SELECTOR_ADD_ELEMENT_BUTTON);
    }

    public void addElements(int howMany) {
        for (int i = 0; i < howMany; i++) {
            click(SELECTOR_ADD_ELEMENT_BUTTON);
        }
    }

    public void removeElements(int howMany) {
        for (int i = 0; i < howMany; i++) {
            removeElement();
        }
    }

    public void removeElement() {
        click(SELECTOR_DELETE_BUTTON);
    }

    public boolean isDisplayed() {
        return driver.getCurrentUrl().contains("/add_remove_elements") &&
                findElementQuietly(SELECTOR_TITLE_HEADER) != null;
    }

    public int countElements() {
        return driver.findElements(SELECTOR_DELETE_BUTTON).size();
    }
}
