package Single_Page_Objects.gateOpener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GateOpenerTest {
    private GateOpener gateOpener;

    @BeforeEach
    public void setupBeforeEveryTest() {
        gateOpener = new GateOpener();
    }

    @AfterEach
    public void cleanup() {
        gateOpener.quitDriver();
    }

    @Test
    public void pageShouldLoad() {
        assertTrue(gateOpener.isDisplayed());
    }

    @Test
    public void afterPageInitialLoadShouldThereBe3Buttons() {
        int buttonsNumber = gateOpener.countButtons();
        assertEquals(3, buttonsNumber, "Buttons number on page should be equal to 3");
    }

    @Test
    public void afterPageInitialLoadShouldThereBe3Valves() {
        int valvesNumber = gateOpener.countValves();
        assertEquals(3, valvesNumber, "Valves number on page should be equal to 3");
    }

    @Test
    public void afterPageInitialLoadFirstButtonShouldNotBeEnabled() {
        boolean firstButtonEnabled = gateOpener.isButtonEnabled(0);
        assertFalse(firstButtonEnabled);
    }

    @Test
    public void afterPageInitialLoadSecondAndRestButtonsShouldBeEnabled() {
        int[] buttonsIndexesToCheck = new int[]{1, 2};
        assertTrue(gateOpener.areButtonsEnabled(buttonsIndexesToCheck));
    }

    @Test
    public void afterPageInitialLoadFirstValveShouldBeOpen() {
        boolean firstValveGreen = gateOpener.getValveColor(0).equals("rgba(34, 139, 34, 1)");
        boolean firstValveOpen = gateOpener.isValveOpen(0);
        assertTrue(firstValveGreen && firstValveOpen);
    }

    @Test
    public void afterPageInitialLoadSecondAndRestValvesShouldNotBeOpen() {
        int[] valvesIndexesToCheck = new int[]{1, 2};
        boolean valvesRed = gateOpener.areValvesRed(valvesIndexesToCheck);
        boolean valvesOpen = gateOpener.areValvesOpen(valvesIndexesToCheck);
        assertTrue(valvesRed && !valvesOpen);
    }

    @Test
    public void afterSecondButtonClickRestShouldBeEnabled() {
        int[] buttonsIndexesToCheck = new int[]{0, 2};
        gateOpener.clickButton(1);
        gateOpener.waitUntilButtonsWillBeEnabled(buttonsIndexesToCheck);
    }

    @Test
    public void afterSecondButtonItShouldBeNotEnabled() {
        int buttonIndexToCheck = 1;
        gateOpener.clickButton(1);
        gateOpener.waitUntilButtonWillNotBeEnabled(buttonIndexToCheck);
    }

    @Test
    public void afterSecondButtonClickRestValvesShouldNotBeOpen() {
        int[] valvesIndexesToCheck = new int[]{0, 2};
        gateOpener.clickButton(1);
        gateOpener.waitUntilValvesWillBeClosed(valvesIndexesToCheck);
    }

    @Test
    public void afterSecondButtonSecondValveShouldNotBeOpen() {
        int valveIndexToCheck = 1;
        gateOpener.clickButton(1);
        gateOpener.waitUntilValveWillBeOpen(valveIndexToCheck);
    }
}
