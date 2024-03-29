package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static constants.MobileElementsIDs.*;
import static constants.TestConstants.testRepetition;
import static utils.Buttons.*;
import static utils.Calculations.*;

public class TestCases extends setup.SetupAppium {

    @Test(priority = 1)
    public void checkCalculatorButtons() {

        // Detect calculator view
        clearCalculator();
        boolean calculatorDisplayed = driver.findElement(By.id(calculatorView)).isDisplayed();
        Assert.assertTrue(calculatorDisplayed, "Calculator view is displayed");
        boolean tabBarDisplayed = driver.findElement(By.id(actionTabBar)).isDisplayed();
        Assert.assertTrue(tabBarDisplayed, "Calculator view is displayed");

        int digitButtonsInCalc = getButtonsNumber("android.widget.TextView");
        int symbolButtons = getButtonsNumber("android.widget.ImageView");

        // Simple mode
        // Check symbol buttons in simple mode
        Assert.assertEquals(calcButtonsSimple().length, symbolButtons, "Symbol buttons count is correct");

        // Check digit buttons in simple mode
        Assert.assertEquals(digitButtonsInCalc, calcDigitButtonsSimple().size(), "Digit buttons count is correct");

        // Check symbol buttons in scientific mode
        driver.findElement(By.id(switchToScienceCalcBtn)).click();
        symbolButtons = getButtonsNumber("android.widget.ImageView");
        Assert.assertEquals(calcButtonsScientific().length, symbolButtons, "Symbol buttons count is correct");


        // Check digit buttons in scientific mode
        digitButtonsInCalc = getButtonsNumber("android.widget.TextView");
        Assert.assertEquals(digitButtonsInCalc, digitButtonsScientific().size(), "Digit buttons count is correct");
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

    @Test(priority =  8)
    public void specialCalculations () {
        for (int i = 1; i <= testRepetition; i++) {
            calculateTrigonometricFunctions();
            calculateWithBrackets(getRandomInt(), getRandomInt(), getRandomInt());
            calculateLogarithms();
            calculatePow();
            calculateSquareRoot(getRandomInt());
            calculateFactorial();
            calculateReciprocal(getRandomInt());
            calculatePiAndEuler(getRandomInt(), getRandomOperation());
        }
    }
}