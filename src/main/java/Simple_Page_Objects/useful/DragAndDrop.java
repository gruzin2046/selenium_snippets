package Simple_Page_Objects.useful;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class DragAndDrop {
    private static final String LOGIN_PAGE_URL = "https://jqueryui.com/droppable/";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']/h1");
    private static final By selectorSourceSquare = By.id("draggable");
    private static final By selectorDestSquare = By.id("droppable");
    private static final By selectorDestSquareParagraph = By.xpath("//div[@id='droppable']/p");

    private final WebDriver driver;

    public DragAndDrop(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return driver.getCurrentUrl().contains("droppable")
                && driver.findElement(selectorPageHeader).getText().contains("Droppable");
    }

    public void switchToFrame() {
        WebElement frame = driver.findElement(By.xpath("//div[@id='content']/iframe"));
        driver.switchTo().frame(frame);
    }

    private WebElement findElem(By by) {
        return driver.findElement(by);
    }

    public String getDestSquareText() {
        return findElem(selectorDestSquareParagraph).getText();
    }

    public void dragAndDropSquareStepByStep() {
        // create new Actions instance
        Actions act = new Actions(driver);
        // 1. click and hold using clickAndHold(WebElement source)
        act.clickAndHold(findElem(selectorSourceSquare))
                // 2. move to element using moveToElement(WebElement source)
                .moveToElement(findElem(selectorDestSquare))
                // 3. release mouse using release()
                .release()
                // 4. perform this action
                .perform();
    }

    public void dragAndDropSquareOnElement() {
        Actions act = new Actions(driver);
        // drag and drop using dragAndDrop(WebElement source, WebElement destination)
        act.dragAndDrop(findElem(selectorSourceSquare) ,findElem(selectorDestSquare))
                .perform();
}

    public void dragAndDropByOffset() {
        Actions act = new Actions(driver);
        // drag and drop using dragAndDropBy(WebElement source, int xOffset, int yOffset)
        act.dragAndDropBy(findElem(selectorSourceSquare), 165, 50)
                .perform();
    }
}
