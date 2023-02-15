package Single_Page_Objects.heroku.Hovers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class Hovers {

    private final WebDriver driver;
    private final Actions act;

    public Hovers(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
        act = new Actions(this.driver);
    }

    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/hovers";
    private static final By SELECTOR_PAGE_HEADER = By.xpath("//h3");
    private static final By SELECTOR_USER_PAGE_HEADER = By.xpath("//h1");

    public boolean pageShouldLoad() {
        return driver.getCurrentUrl().contains("hovers") && driver.findElement(SELECTOR_PAGE_HEADER).getText().equals("Hovers");
    }

    private By getUserPhotoSelector(int userOrderNumber) {
        return By.xpath("(//img[@alt='User Avatar'])[" + userOrderNumber + "]");
    }

    private By getUserNameSelector(int userOrderNumber) {
        return By.xpath("(//h5)[" + userOrderNumber + "]");
    }

    private By getViewUserProfileLocator(int userOrderNumber) {
        return By.xpath("//a[@href='/users/" + userOrderNumber + "']");
    }

    public void hoverOverUserPhoto(int userOrderNumber) {
        moveToElement(getUserPhotoSelector(userOrderNumber));
    }

    private void moveToElement(By locator) {
        act.moveToElement(driver.findElement(locator)).perform();
    }

    public boolean checkIfUserNameIsDisplayed() {
        return driver.findElement(getUserNameSelector(1)).isDisplayed();
    }

    public void clickOnViewProfile(int userOrderNumber) {
        hoverOverUserPhoto(userOrderNumber);
        moveToElement(getViewUserProfileLocator(userOrderNumber));
        driver.findElement(getViewUserProfileLocator(userOrderNumber)).click();
    }

    public boolean checkIfUserPageIsOpened(int userOrderNumber) {
        return driver.findElement(SELECTOR_USER_PAGE_HEADER).getText().equals("Not Found")
                && driver.getCurrentUrl().endsWith("/users/" + userOrderNumber);
    }
}
