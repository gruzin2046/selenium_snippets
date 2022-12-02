package Single_Page_Objects.heroku.UploadFilePage;
import Single_Page_Objects.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadFilePage extends BasePage {

    private static final String PAGE_URL = "https://the-internet.herokuapp.com/upload";
    private static final By selectorPageHeader = By.xpath("//div[@id='content']//h3");
    private static final By selectorFileUploadInput = By.id("file-upload");
    private static final By selectorFileUploadButton = By.id("file-submit");
    private static final By selectorUploadedFileText = By.id("uploaded-files");

    public UploadFilePage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public boolean pageShouldLoad() {
        return pageShouldLoad("upload", selectorPageHeader, "File Uploader");
    }

    public String getHeaderText() {
        return findElementQuietly(selectorPageHeader).getText();
    }

    public String getUploadedFileName() {
        return findElementQuietly(selectorUploadedFileText).getText();
    }

    private String createFilePath(String fileName) {
        return System.getProperty("user.dir") + ".\\src\\main\\resources\\Files\\" + fileName;
    }

    public void uploadFile(String fileName) {
        type(selectorFileUploadInput, createFilePath(fileName));
        click(selectorFileUploadButton);
    }
}
