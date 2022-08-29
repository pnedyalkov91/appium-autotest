package Utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

import static Constants.MobileElementsIDs.*;
import static Utils.Buttons.clickBtnDigit;

public class Calculations extends setup.SetupAppium {


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

    /**
     * Calculate two terms using MIUI calculator and Java. After that compare results between Java calculation and
     * MIUI calculator.
     *
     * @param termOne  - Term one.
     * @param termTwo  - Term two.
     * @param operator - Use the following operators: +, -, *, /.
     */
    public static void calculateTwoNumbers(int termOne, int termTwo, String operator) {
        double javaResult = 0.0;

        switch (operator) {
            case "+" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnPlus)).click();
                clickBtnDigit(termTwo);
                javaResult = termOne + termTwo;
            }
            case "-" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnMinus)).click();
                clickBtnDigit(termTwo);
                javaResult = termOne - termTwo;
            }
            case "*" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnMultiply)).click();
                clickBtnDigit(termTwo);
                javaResult = termOne * termTwo;
            }
            case "/" -> {
                clickBtnDigit(termOne);
                driver.findElement(By.id(btnDivide)).click();
                clickBtnDigit(termTwo);
                javaResult = termOne * 1.0 / termTwo;
            }
        }
        driver.findElement(By.id(btnEquals)).click();

        // Compare results between Java calculation and MIUI calculator
        compareResults(javaResult);
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
        double javaResult = 0.0;

        switch (operator) {
            case "+" -> {
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnPlus)).click();
                    javaResult += term;
                }
            }
            case "-" -> {
                javaResult = term * 2;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnMinus)).click();
                    javaResult -= term;
                }
            }
            case "*" -> {
                javaResult = 1;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnMultiply)).click();
                    javaResult *= term;
                }
            }
            case "/" -> {
                javaResult = term  * 10;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnDivide)).click();
                    javaResult /= term;
                }
            }
        }
        driver.findElement(By.id(btnEquals)).click();

        // Compare result between Java calculation and MIUI calculator
        compareResults(javaResult);
    }

    /**
     * Get result from MIUI Calculator and parse to double.
     *
     * @return result from MIUI Calculator.
     */
    private static double getResult() {
        String strResult = driver.findElement(By.id(resultField)).getAttribute("text");
        strResult = strResult.replaceAll("[^\\d-e.]", "");
        return Double.parseDouble(strResult);
    }

    /**
     * Get random data from the Credentials dictionary.
     *
     * @return - generating a random number 1 - 100.
     */
    public static int getRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }

    /**
     * Get random calculation
     *
     * @return random operation.
     */
    public static String randomCalculations() {
        String[] calculations = {"+", "-", "*", "/"};
        Random random = new Random();
        int index = random.nextInt(calculations.length);
        return calculations[index];
    }

    /**
     * Compare results between Java calculation and MIUI calculator..
     *
     * @param javaResult - Result from Java calculations. It's compared to result from MIUI Calculator
     */
    private static void compareResults(double javaResult) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        if (driver.findElement(By.id(resultField)).getAttribute("text").equals("= Can't divide by zero")
                && javaResult == Double.POSITIVE_INFINITY) {
            Assert.assertTrue(true, "Can't divide by zero");
        } else {
            double result = getResult();
            Assert.assertEquals(Math.floor(result), Math.floor(javaResult));
        }
    }
}