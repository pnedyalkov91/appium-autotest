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
     * @param termOne  - Input int for term one.
     * @param termTwo  - Input int for term two.
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
     * Calculate Pi
     *
     * @param term - Int input
     * @param operator - Use the following operators: +, -, *, /.
     */
    public static void calculatePiAndEuler(int term, String operator) {
        double javaResultForPi;
        double javaResultForEulerNumber;
        clearCalculator();
        switch (operator) {
            case "+" -> {
                // Calculate Pi
                clickBtnDigit(term);
                driver.findElement(By.id(btnPlus)).click();
                driver.findElement(By.id(btnPi)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForPi = term + Math.PI;
                compareResults(javaResultForPi);

                // Calculate Euler's number
                clearCalculator();
                clickBtnDigit(term);
                driver.findElement(By.id(btnPlus)).click();
                driver.findElement(By.id(btnEuler)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForEulerNumber = term + Math.exp(1);
                compareResults(javaResultForEulerNumber);
            }
            case "-" -> {
                // Calculate Pi
                clickBtnDigit(term);
                driver.findElement(By.id(btnMinus)).click();
                driver.findElement(By.id(btnPi)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForPi = term - Math.PI;
                compareResults(javaResultForPi);

                // Calculate Euler's number
                clearCalculator();
                clickBtnDigit(term);
                driver.findElement(By.id(btnMinus)).click();
                driver.findElement(By.id(btnEuler)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForEulerNumber = term - Math.exp(1);
                compareResults(javaResultForEulerNumber);

            }
            case "*" -> {
                // Calculate Pi
                clickBtnDigit(term);
                driver.findElement(By.id(btnMultiply)).click();
                driver.findElement(By.id(btnPi)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForPi = term * Math.PI;
                compareResults(javaResultForPi);

                // Calculate Euler's number
                clearCalculator();
                clickBtnDigit(term);
                driver.findElement(By.id(btnMultiply)).click();
                driver.findElement(By.id(btnEuler)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForEulerNumber = term * Math.exp(1);
                compareResults(javaResultForEulerNumber);
            }
            case "/" -> {
                // Calculate Pi
                clickBtnDigit(term);
                driver.findElement(By.id(btnDivide)).click();
                driver.findElement(By.id(btnPi)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForPi = term / Math.PI;
                compareResults(javaResultForPi);

                // Calculate Euler's number
                clearCalculator();
                clickBtnDigit(term);
                driver.findElement(By.id(btnDivide)).click();
                driver.findElement(By.id(btnEuler)).click();
                driver.findElement(By.id(btnEquals)).click();
                javaResultForEulerNumber = term / Math.exp(1);
                compareResults(javaResultForEulerNumber);
            }
        }
    }

    /**
     * Calculate Pi
     *
     * @param term - Int input
     */
    public static void calculateReciprocal(int term) {
        double javaResult;
        clearCalculator();

        // Calculate with Calculator
        clickBtnDigit(term);
        driver.findElement(By.id(btnReciprocal)).click();
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = 1.0 / term;
        compareResults(javaResult);
    }

    /**
     * Calculate Factorial
     */
    public static void calculateFactorial() {
        int factorial = 1;

        // Get random number and parse it to int
        Random random = new Random();
        int randomNumbers = random.nextInt(10) ;

        int number;
        clearCalculator();

        // Calculate with Calculator
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnFactorial)).click();
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        number = randomNumbers;

        for (int i = 1; i <= number ; i++) {
            factorial = factorial * i;
        }

        compareResults(factorial);
    }

    /**
     * Calculate Factorial
     *
     * @param term - Int input
     */

    public static void calculateSquareRoot (int term) {
        double javaResult;
        clearCalculator();

        // Calculate with Calculator
        driver.findElement(By.id(btnSqrt)).click();
        clickBtnDigit(term);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = Math.sqrt(term);
        compareResults(javaResult);
    }

    /**
     * Calculate Pow
     *
     * @param termOne  - Input int for term one.
     * @param termTwo  - Input int for term two.
     */
    public static void calculatePow (int termOne, int termTwo) {
        double javaResult;
        clearCalculator();

        // Calculate with Calculator
        clickBtnDigit(termOne);
        driver.findElement(By.id(btnPow)).click();
        clickBtnDigit(termTwo);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = Math.pow(termOne, termTwo);

        //Compare results
        compareResults(javaResult);
    }

    /**
     * Calculate logarithms
     */
    public static void calculateLogarithms () {

        clearCalculator();
        Random random = new Random();
        int randomNumbers = random.nextInt(1000);

        // Calculate using logarithm
        // Calculate with Calculator
        driver.findElement(By.id(btnLogarithm)).click();
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        double javaResult = Math.log10(randomNumbers);

        //Compare results
        compareResults(javaResult);

        // Calculate using natural logarithm
        // Calculate with Calculator
        clearCalculator();
        driver.findElement(By.id(btnNaturalLog)).click();
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = Math.log(randomNumbers);

        //Compare results
        compareResults(javaResult);
    }

    /**
     * Calculate Trigonometric Functions
     */
    public static void calculateTrigonometricFunctions() {

        // Calculate sin
        clearCalculator();
        Random random = new Random();
        int randomNumbers = random.nextInt(360);

        // Calculate with Calculator
        driver.findElement(By.id(btnSin)).click();
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        double javaResult = Math.sin(randomNumbers);
        compareResults(javaResult);


        // Calculate cos
        clearCalculator();
        driver.findElement(By.id(btnCos)).click();
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = Math.cos(randomNumbers);
        compareResults(javaResult);


        // Calculate tan
        clearCalculator();
        driver.findElement(By.id(btnTangent)).click();
        clickBtnDigit(randomNumbers);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = Math.tan(randomNumbers);
        compareResults(javaResult);
    }

    /**
     * Calculate with brackets
     *
     * @param termOne  - Input int for Input int for term one.
     * @param termTwo  - Input int for term two.
     * @param termThree - Input int for term three
     */
    public static void calculateWithBrackets(int termOne, int termTwo, int termThree) {
        double javaResult;
        clearCalculator();

        // Calculate with calculator
        driver.findElement(By.id(btnLeftParenthesis)).click();
        clickBtnDigit(termOne);
        driver.findElement(By.id(btnPlus)).click();
        clickBtnDigit(termTwo);
        driver.findElement(By.id(btnRightParenthesis)).click();
        driver.findElement(By.id(btnMultiply)).click();
        clickBtnDigit(termThree);
        driver.findElement(By.id(btnEquals)).click();

        // Calculate with Java
        javaResult = (termOne + termTwo) * termThree;

        // Compare results
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
     * Compare results between Java calculation and MIUI calculator.
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
            Assert.assertEquals(Math.ceil(result), Math.ceil(javaResult));
        }
    }
}