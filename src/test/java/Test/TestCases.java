package Test;


import org.testng.annotations.Test;

import static Constants.TestConstants.testRepetition;
import static Utils.Calculations.*;


public class TestCases extends setup.SetupAppium {

    @Test
    public void calculateTwoNumbersTest() {
        clearCalculator();
        calculateTwoNumbers(20, 3, "*");
    }

    @Test
    public void calculateNumbersTest() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(10, 2, randomCalculations());
        }
    }

    @Test
    public void calculateRandomGeneratedNumbers() {
        for (int test = 0; test < testRepetition; test++) {
            clearCalculator();
            calculateNumbers(getRandomInt(10), getRandomInt(5), randomCalculations());
        }
    }
}

