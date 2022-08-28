package Test;


import org.testng.annotations.Test;
import static Utils.Calculations.*;


public class TestCases extends setup.SetupAppium {


    @Test
    public void calculation() {
        clearCalculator();
        calculateTwoNumbers(20, 3, randomCalculations());
        clearCalculator();
        calculateNumbers(10, 2, randomCalculations());
        clearCalculator();
        calculateNumbers(getRandomInt(10), getRandomInt(5), randomCalculations());
    }

    @Test
    public void test1 () {
        calculateTwoNumbers(20, 0, "/");
    }
}

