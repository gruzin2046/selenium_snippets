package Interview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InterviewPage {

    private final WebDriver driver;

    public InterviewPage(WebDriver driver){
        this.driver = driver;
        driver.get(LOGIN_PAGE_URL);
    }

    private static final String LOGIN_PAGE_URL = "";
    private static final By SELECTOR_PAGE_HEADER = By.xpath("");

    public boolean pageShouldLoad() {
        return driver.getCurrentUrl().contains(" ") && driver.findElement(SELECTOR_PAGE_HEADER).getText().equals("");
    }
}
