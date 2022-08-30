package Test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static Constants.MobileElementsIDs.*;
import static Constants.TestConstants.testRepetition;
import static Utils.Buttons.*;
import static Utils.Calculations.*;


public class TestCases extends Setup.SetupAppium {

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void checkButtonsAttributes() {
        checkButtons();
    }

    @Test(priority = 3)
    public void calculateTwoNumbersTest() {
        clearCalculator();
        calculateTwoNumbers(20, 3, "*");
    }

    @Test(priority = 4)
    public void calculateNumbersTest() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(10, 5, getRandomOperation());
        }
    }

    @Test(priority = 5)
    public void calculateRandomGeneratedNumbers() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(getRandomInt(), getRandomInt(), getRandomOperation());
        }
    }

    @Test(priority = 6)
    public void calculatePercentInCalculator() {
        for (int test = 0; test < testRepetition; test++) {
            calculatePercent();
        }
    }

    @Test(priority = 7)
    public void checkDeleteButton() {
        // Clear calculator
        clearCalculator();

        // Get random number and parse it to int
        Random random = new Random();
        int randomNumbers = random.nextInt(999999999);

        // Click digit buttons
        for (int i = 0; i <= testRepetition; i++) {
            clickBtnDigit(randomNumbers);
        }

        // Delete digits
        String getResult = driver.findElement(By.id(resultField)).getText();
        for (int i = 0; i <= getResult.length(); i++) {
            driver.findElement(By.id(btnDelete)).click();
        }

        // Compare results
        String getExpression = driver.findElement(By.id(expression)).getAttribute("content-desc");
        int parseExpressionToInt = Integer.parseInt(getExpression);
        Assert.assertEquals(parseExpressionToInt, 0);
    }
}