package Single_Page_Objects.heroku.JavaScriptExecutor;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.JSExecutor.JSExecutorPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JSExecutorTest extends BaseTest {
    private JSExecutorPage jSExecutorPage;

    @BeforeEach
    void prepareTest() {
        jSExecutorPage = new JSExecutorPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(jSExecutorPage.pageShouldLoad());
    }

    @Test
    void scrollTest() throws InterruptedException {
        jSExecutorPage.scrollToPageBottom();
        jSExecutorPage.sleep();
        jSExecutorPage.scrollToWebElement(jSExecutorPage.getPageSelector());
        jSExecutorPage.sleep();
    }
}
