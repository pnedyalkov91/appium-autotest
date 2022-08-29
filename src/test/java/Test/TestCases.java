package Test;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Constants.MobileElementsIDs.*;
import static Constants.TestConstants.*;
import static Utils.Buttons.*;
import static Utils.Buttons.getButtonsNumber;
import static Utils.Calculations.*;


public class TestCases extends setup.SetupAppium {

    @Test (priority = 1)
    public void checkCalculatorButtons() {

        int digitButtonsInCalc = getButtonsNumber("android.widget.TextView");
        int symbolButtons = getButtonsNumber("android.widget.ImageView");

        // Simple mode
        // Check symbol buttons in simple mode
        if (symbolButtons == calcButtonsSimple().length) {
            Assert.assertTrue(true, "Symbol buttons count is correct");
        } else {
            Assert.fail("Symbol buttons count is not correct. The expected number should be " +
                    calcButtonsSimple().length);
        }

        // Check digit buttons in simple mode
        if (calcDigitButtonsSimple().size() == digitButtonsInCalc) {
            Assert.assertTrue(true, "Digit buttons count is correct");
        } else {
            Assert.fail("Digit buttons count is not correct. The expected number should be " +
                    calcDigitButtonsSimple().size());
        }

        // Check symbol buttons in scientific mode
        driver.findElement(By.id(switchToScienceCalcBtn)).click();
        symbolButtons = getButtonsNumber("android.widget.ImageView");
        if (symbolButtons == calcButtonsScientific().length) {
            Assert.assertTrue(true, "Symbol buttons count is correct");
        } else {
            Assert.fail("Symbol buttons count is not correct. The expected number should be " +
                    calcButtonsSimple().length);
        }

        // Check digit buttons in scientific mode
        digitButtonsInCalc = getButtonsNumber("android.widget.TextView");
        if (digitButtonsScientific().size() == digitButtonsInCalc) {
            Assert.assertTrue(true, "Digit buttons count is correct");
        } else {
            Assert.fail("Digit buttons count is not correct. The expected number should be " +
                    digitButtonsScientific().size());
        }
    }

    @Test (priority = 2)
    public void checkButtonsAttributes() {
        checkButtons();
    }

    @Test (priority = 3)
    public void calculateTwoNumbersTest() {
        clearCalculator();
        calculateTwoNumbers(20, 3, "*");
    }

    @Test (priority = 4)
    public void calculateNumbersTest() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(10, 2, randomCalculations());
        }
    }

    @Test (priority = 5)
    public void calculateRandomGeneratedNumbers() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(getRandomInt(10), getRandomInt(5), randomCalculations());
        }
    }
}

