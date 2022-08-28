package Utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Objects;
import java.util.Random;

import static Constants.MobileElementsIDs.*;
import static Utils.Buttons.clickBtnDigit;

public class Calculations extends setup.SetupAppium {


    /**
     * Method for clearing calculator
     */
    public static void clearCalculator() {
        if (!Objects.equals(driver.findElement(By.id(expression)).getAttribute("content-desc"), "0")) {
            driver.findElement(By.id(btnClear)).click();
            if (Objects.equals(driver.findElement(By.id(expression)).getAttribute("content-desc"), "0")) {
                System.out.println("The calculator is cleared");
            }
        } else {
            System.out.println("Тhe calculator has already been reset");
        }
    }

    /**
     * Addition two terms using MIUI calculator and Java. After that compare them.
     *
     * @param termOne  - Term one
     * @param termTwo  - Term two
     * @param operator - Use the following operators: +, -, *, /
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
                driver.findElement(By.id(btnEquals)).click();
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
        if (driver.findElement(By.id(resultField)).getAttribute("text").equals("= Can't divide by zero") && javaResult == Double.POSITIVE_INFINITY) {
            Assert.assertTrue(true, "Can't divide by zero");
        } else {
            compareResults(javaResult);
        }
    }

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
                javaResult = (term * 1.0 / 2) / 10;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    javaResult *= term;
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnMultiply)).click();
                }
            }
            case "/" -> {
                javaResult = (term * 2) * 10;
                for (int termsCount = 0; termsCount <= terms - 1; termsCount++) {
                    javaResult /= term;
                    clickBtnDigit(term);
                    driver.findElement(By.id(btnDivide)).click();
                }
            }
        }
        driver.findElement(By.id(btnEquals)).click();

        // Compare result between Java calculation and MIUI calculator
        if (driver.findElement(By.id(resultField)).getAttribute("text").equals("= Can't divide by zero") && javaResult == Double.POSITIVE_INFINITY) {
            Assert.assertTrue(true, "Can't divide by zero");
        } else {
            compareResults(javaResult);
        }
    }

    /**
     * Get result from MIUI Calculator and parse to double
     *
     * @return result from MIUI Calculator
     */
    private static double getResult() {
        String strResult = driver.findElement(By.id(resultField)).getAttribute("text");
        strResult = strResult.replaceAll("[^\\d-e.]", "");
        return Double.parseDouble(strResult);
    }

    /**
     * Compare results between Java calculation and MIUI calculator
     *
     * @param javaResult - Result from Java calculations. It's compared to result from MIUI Calculator
     */
    private static void compareResults(double javaResult) {
        double result = getResult();
        Assert.assertEquals(result, javaResult);
    }

    /**
     * Get random data from the Credentials dictionary
     *
     * @return - generating a random number 1 - 100
     */
    public static int getRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }

    /**
     * Get random calculation
     *
     * @return - return random operation
     */
    public static String randomCalculations() {
        String[] calculations = {"+", "-", "*", "/"};
        return calculations[getRandomInt(3)];
    }
}