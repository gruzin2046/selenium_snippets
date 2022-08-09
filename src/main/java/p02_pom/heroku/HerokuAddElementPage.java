package p02_pom.heroku;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HerokuAddElementPage {
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/add_remove_elements/";
    private WebDriver driver;

    private static final By SELECTOR_ADD_ELEMENT_BUTTON = By.xpath("//button[text()='Add Element']");
    private static final By SELECTOR_DELETE_BUTTON = By.className("added-manually");
//    private static final By SELECTOR_DELET_BUTTON = By.cssSelector(".added-manually");
    private static final By SELECTOR_TITLE_HEADER = By.xpath("//h3[text()='Add/Remove Elements']");

    public HerokuAddElementPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
    }

    public void addElement() {
        driver.findElement(SELECTOR_ADD_ELEMENT_BUTTON).click();
    }

    public void addElements(int howMany) {
        WebElement addElementButton = driver.findElement(SELECTOR_ADD_ELEMENT_BUTTON);

        for (int i = 0; i < howMany; i++) {
            addElementButton.click();
        }
    }

    public void removeElements(int howMany) {
        for (int i = 0; i < howMany; i++) {
            removeElement();
        }
    }

    public void removeElement() {
        driver.findElement(SELECTOR_DELETE_BUTTON).click();
    }

    public boolean isDisplayed() {
        // TODO - refactor - return value instead of exception
//        driver.findElement(SELECTOR_TITLE_HEADER);

        String pageUrl = driver.getCurrentUrl();

        return pageUrl.contains("/add_remove_elements");
    }

    public int countElements() {
        List<WebElement> allDeleteButtons = driver.findElements(SELECTOR_DELETE_BUTTON);
        return allDeleteButtons.size();

//        return driver.findElements(SELECTOR_DELETE_BUTTON).size();
    }
}
