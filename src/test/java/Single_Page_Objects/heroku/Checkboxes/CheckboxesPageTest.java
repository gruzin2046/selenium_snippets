package Single_Page_Objects.heroku.Checkboxes;

import Single_Page_Objects.Base.BaseTest;
import Single_Page_Objects.heroku.CheckboxesPage.CheckboxesPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckboxesPageTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void prepareTest() {
        checkboxesPage = new CheckboxesPage(driver);
    }

    @Test
    void pageShouldLoad() {
        assertTrue(checkboxesPage.pageShouldLoad());
    }

    @Test
    void checkIfHaveText() {
        // weird but its working
        String text = checkboxesPage.getCheckbox(0).getText();
        assertEquals("", text);
    }

    @Test
    void checkIfDisplayed() {
        boolean isDisplayed = checkboxesPage.getCheckbox(0).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    void checkIfSelected() {
        boolean isSelected = checkboxesPage.getCheckbox(1).isSelected();
        assertTrue(isSelected);
    }

    @Test
    void checkIfEnabled() {
        boolean isEnabled = checkboxesPage.getCheckbox(0).isEnabled();
        assertTrue(isEnabled);
    }

    @Test
    void clickCheckbox() {
        checkboxesPage.getCheckbox(0).click();
        boolean isSelected = checkboxesPage.getCheckbox(0).isSelected();
        assertTrue(isSelected);
    }

    @Test
    void getCheckedAttribute() {
        String checked = checkboxesPage.getCheckbox(1).getAttribute("checked");
        assertEquals("true", checked);
    }

    // II. Second group

    @Test
    void selectAllCheckboxes() {
        checkboxesPage.selectAllCheckboxes();
        assertTrue(checkboxesPage.checkIfAllCheckboxesAreSelected());
    }

}