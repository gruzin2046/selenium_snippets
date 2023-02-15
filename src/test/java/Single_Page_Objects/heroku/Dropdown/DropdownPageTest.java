package Single_Page_Objects.heroku.Dropdown;
import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.DropdownPage.DropdownPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropdownPageTest extends BaseTest {
    private DropdownPage dropdownPage;

    @BeforeEach
    void prepareTest() {
        dropdownPage = new DropdownPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(dropdownPage.pageShouldLoad());
    }

    @Test
    void afterSelectSecondOptionItShouldBeSelected() {
        dropdownPage.selectOption(2);
        assertTrue(dropdownPage.optionIsSelected(2));
    }

}
