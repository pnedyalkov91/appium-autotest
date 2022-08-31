package Utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

import static Constants.MobileElementsIDs.*;
import static Utils.Buttons.clickBtnDigit;

public class Calculations extends Setup.SetupAppium {


    /**
     * Clear MIUI calculator
     */
    public static void clearCalculator() {
        if (!Objects.equals(driver.findElement(By.id(expression)).getAttribute("content-desc"), "0")) {
            driver.findElement(By.id(btnClear)).click();
            if (Objects.equals(driver.findElement(By.id(expression)).getAttribute("content-desc"), "0")) {
                System.out.println("Calculator is cleared");
            }
        } else {
            System.out.println("Calculator has already been reset");
        }
    }

    public static void calculatePercent() {
        // Clear calculator
        clearCalculator();

        // Get random number and parse it to int
        int randomNumbers = getRandomInt();

        // Click the saved number on buttons
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnPercent)).click();
        double calcResult = getResult();
        double javaCalculation = randomNumbers / 100.0;

        // Compare results between calculator and Java
        Assert.assertEquals(calcResult, javaCalculation);
    }

    /**
     * Calculate two terms using MIUI calculator and Java. After that compare results between Java calculation and
     * MIUI calculator.
     *
     * @param termOne  - Term one.
     * @param termTwo  - Term two.
     * @param operator - Use the following operators: +, -, *, /.
     */
    public static void calculateTwoNumbers(int termOne, int termTwo, String operator) {
        double result = 0.0;

        switch (operator) {
            case "+" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnPlus)).click();
                clickBtnDigit(termTwo);
                result = termOne + termTwo;
            }
            case "-" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnMinus)).click();
                clickBtnDigit(termTwo);
                result = termOne - termTwo;
            }
            case "*" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnMultiply)).click();
                clickBtnDigit(termTwo);
                result = termOne * termTwo;
            }
            case "/" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnDivide)).click();
                clickBtnDigit(termTwo);
                result = termOne * 1.0 / termTwo;
            }
        }
        driver.findElement(By.id(btnEquals)).click();

        // Compare results between Java calculation and MIUI calculator
        // Compare result between Java calculation and MIUI calculator
        if (result == Double.POSITIVE_INFINITY) {
            divideByZero(result);
        } else {
            compareResults(result);
        }
    }

    /**
     * Calculate two terms using MIUI calculator and Java. After that compare results between Java calculation and
     * MIUI calculator.
     *
     * @param term - Input data
     * @param terms - How many terms will be used.
     * @param operator - Use the following operators: +, -, *, /.
     */
    public static void calculateNumbers(int term, int terms, String operator) {
        double result = 0.0;

        switch (operator) {
            case "+" -> {
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnPlus)).click();
                    result += term;
                }
            }
            case "-" -> {
                result = term * 2;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnMinus)).click();
                    result -= term;
                }
            }
            case "*" -> {
                result = 1;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnMultiply)).click();
                    result *= term;
                }
            }
            case "/" -> {
                result = term  * 10;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnDivide)).click();
                    result /= term;
                }
            }
        }
        driver.findElement(By.id(btnEquals)).click();

        // Compare result between Java calculation and MIUI calculator
        if (result == Double.POSITIVE_INFINITY) {
            divideByZero(result);
        } else {
            compareResults(result);
        }
    }

    /**
     * Get result from MIUI Calculator and parse to double.
     *
     * @return result from MIUI Calculator.
     */
    public static double getResult() {
        String strResult = driver.findElement(By.id(resultField)).getAttribute("text");
        strResult = strResult.replaceAll("[^\\d-e.]", "");
        return Double.parseDouble(strResult);
    }

    /**
     * Get random data from the Credentials dictionary.
     *
     * @return - generating a random number 1 - 100.
     */
    public static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(20);
    }

    /**
     * Get random calculation
     *
     * @return random operation.
     */
    public static String getRandomOperation() {
        String[] operations = {"+", "-", "*", "/"};
        Random random = new Random();
        int index = random.nextInt(operations.length);
        return operations[index];
    }

    /**
     * Compare divide by zero between Java calculation and MIUI calculator..
     *
     * @param resultFromJava - Result from Java calculations. It's compared to result from MIUI Calculator
     */
    private static void divideByZero(double resultFromJava) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Assert.assertTrue(driver.findElement(By.id(resultField)).getAttribute("text")
                .equals("= Can't divide by zero") && resultFromJava == Double.POSITIVE_INFINITY);
    }

    /**
     * Compare results between Java calculation and MIUI calculator..
     *
     * @param resultFromJava - Result from Java calculations. It's compared to result from MIUI Calculator
     */
        public static void compareResults (double resultFromJava) {
            double result = getResult();
            Assert.assertEquals(Math.ceil(result), Math.ceil(resultFromJava));
        }
    }