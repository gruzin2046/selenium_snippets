package Simple_Page_Objects.heroku;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HerokuAddElementPageTest {
    private static WebDriver driver;
    private HerokuAddElementPage herokuAddElementPage;

    // metoda odpalana RAZ przed uruchomieniem "klasy"
    @BeforeAll
    public static void warmup() {
        // inicjalizacja webdrivera (wskazanie pliku na dysku i "załadowanie")
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/tools/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    // metoda odpalana RAZ po uruchomieniu "klasy"
    @AfterAll
    public static void cleanup() {
        driver.quit();
    }

    // metoda odpalana PRZED KAŻDYM TESTEM
    @BeforeEach
    void prepareTest() {
        herokuAddElementPage = new HerokuAddElementPage(driver);
    }

    /***
     * - Po naciśnięciu Add Element 4x pojawią się 4 przyciski Delete
     * - Po dodaniu jednego elementu naciśnięcie go usuwa go ze strony
     * - Po dodaniu 4 elementów, naciśnięcie wszystkich usunie wszystkie ze strony
     * - Po dodaniu 4 elementów, naciśnięcie jednego usunie tylko jeden element
     *
     *
     * - * Test linku do githuba (ribbona) przy przesłoniętej wstążce
     */


    // Czy w ogóle się strona ładuje/wyświetla
    @Test
    public void pageShouldLoad() {
        assertTrue(herokuAddElementPage.isDisplayed());
    }

    // Nie ma żadnego przycisku Delete
    @Test
    public void noneDeleteButtonShouldExist() {
        int elementsOnPageCount = herokuAddElementPage.countElements();

        assertEquals(0, elementsOnPageCount);
    }

    // Po naciśnięciu Add Element pojawia się przycisk Delete
    @Test
    public void clickOnAddElementShouldAddDeleteButton() {
        assertEquals(0, herokuAddElementPage.countElements());

        herokuAddElementPage.addElement();

        assertEquals(1, herokuAddElementPage.countElements());
    }


    // Po naciśnięciu Add Element 4x pojawią się 4 przyciski Delete
    @Test
    public void clickOnAddElement4TimesShouldAdd4DeleteButtons() {
        assertEquals(0, herokuAddElementPage.countElements());

        herokuAddElementPage.addElement();
        herokuAddElementPage.addElement();
        herokuAddElementPage.addElement();
        herokuAddElementPage.addElement();

        assertEquals(4, herokuAddElementPage.countElements());
    }

    // Po dodaniu jednego elementu naciśnięcie go usuwa go ze strony
    @Test
    public void clickOnDeleteShouldRemoveAddedElement() {
        herokuAddElementPage.addElement();
        herokuAddElementPage.removeElement();

        assertEquals(0, herokuAddElementPage.countElements());
    }

    // Po dodaniu 4 elementów, naciśnięcie wszystkich usunie wszystkie ze strony
    @Test
    public void clickOn4DeleteButtonsShouldRemoveAllElements() {
        herokuAddElementPage.addElements(4);
        herokuAddElementPage.removeElements(4);

        assertEquals(0, herokuAddElementPage.countElements());
    }

    // Po dodaniu 4 elementów, naciśnięcie jednego usunie tylko jeden element
    @Test
    public void clickDeleteOnOneOf4ShouldDeleteOnlyOne() {
        herokuAddElementPage.addElements(4);
        herokuAddElementPage.removeElement();

        assertEquals(3, herokuAddElementPage.countElements());
    }
}
