package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static Constants.MobileElementsIDs.*;
import static Constants.TestConstants.digitsInCalculator;
import static Utils.Calculations.*;

public class Buttons extends Setup.SetupAppium {

    /**
     * Transform input data from btnNum and click on digit buttons. For example: Our input is 12345 and this method
     * clicks on the 1,2,3,4,5 buttons in MIUI Calculator
     *
     * @param btnNum - input data for clicking buttons.
     */
    public static void clickBtnDigit (int btnNum) {
        String stringNum = Integer.toString(btnNum);
        for (int i = 0; i <= stringNum.length() - 1; i++) {
            driver.findElement(By.id("com.miui.calculator:id/btn_" + stringNum.charAt(i) + "_s")).click();
        }
    }

    /**
     * Count buttons number in the application using Web Elements class name
     *
     * @return buttons number.
     */
    public static int getButtonsNumber(String WebElementClassName) {
        List<WebElement> listOfElements = driver.findElements(By.className(WebElementClassName));
        return listOfElements.size();
    }

    /**
     * Array with strings which contain symbol buttons. They can be used in for simple calculator
     *
     * @return - array strings
     */
    public static String[] calcButtonsSimple() {
        return new String[] {expression, btnClear, btnDelete, btnPercent, btnDivide, btnMultiply, btnMinus, btnPlus,
                btnEquals, switchToScienceCalcBtn, floatingBtn, calculatorTabBtn, converterTabBtn, financeTabBtn,
                moreOptionsBtn};
    }

    /**
     * Array with strings which contain symbol buttons. They can be used in for scientific calculator
     *
     * @return - array strings
     */
    public static String[] calcButtonsScientific() {
        return new String[]{expression, btnClear, btnDelete, btnPercent, btnDivide, btnMultiply, btnMinus, btnPlus,
                btnEquals, calculatorTabBtn, converterTabBtn, financeTabBtn,
                moreOptionsBtn, btnPi, btnReciprocal, btnFactorial, btnSqrt, btnPow, btnSwitchToSecondFunction,
                btnAngleOrDeg, btnSin, btnCos, btnTangent, btnLogarithm, btnNaturalLog, btnLeftParenthesis,
                btnRightParenthesis, switchToSimpleCalcBtn};
    }

    /**
     * Array with strings which contain numeric buttons. They can be used in for simple calculator
     *
     * @return - array strings
     */
    public static ArrayList<String> calcDigitButtonsSimple() {
        ArrayList<String> digitElements = new ArrayList<>();
        String digits = "com.miui.calculator:id/btn_";

        for (int digitButton = 0; digitButton <= digitsInCalculator; digitButton++) {
            digitElements.add(digits + digitButton + "_s");
        }
        digitElements.add(btnDot);
        return digitElements;
    }

    /**
     * Array with strings which contain numeric buttons. They can be used in for scientific calculator
     *
     * @return - array strings
     */
    public static ArrayList<String> digitButtonsScientific() {
        ArrayList<String> digitElements = new ArrayList<>();
        String digits = "com.miui.calculator:id/btn_";

        for (int digitButton = 0; digitButton <= digitsInCalculator; digitButton++) {
            digitElements.add(digits + digitButton + "_s");
        }
        digitElements.add(btnDot);
        digitElements.add(btnEuler);
        return digitElements;
    }

    /**
     * Check enabled and displayed buttons
     */
    public static void checkButtons() {
        clearCalculator();
        String buttons;
        for (int clickable = 0; clickable <= calcButtonsScientific().length - 1; clickable++) {
            buttons = calcButtonsScientific()[clickable];
            checkIfButtonIsEnabled(buttons);
            checkIfButtonIsDisplayed(buttons);
        }

        for (int clickable = 0; clickable <= digitButtonsScientific().size() - 1; clickable++) {
            buttons = digitButtonsScientific().get(clickable);
            checkIfButtonIsEnabled(buttons);
            checkIfButtonIsDisplayed(buttons);
        }
    }

    /**
     * Checks If a button has got "isEnabled" attribute or not
     *
     * @param buttonId - Select button for testing
     */
    public static void checkIfButtonIsEnabled(String buttonId) {
        boolean isButtonEnabled = driver.findElement(By.id(buttonId)).isEnabled();
        Assert.assertTrue(isButtonEnabled, "The button has correct attributes");
    }

    /**
     * Checks If a button has got "isEnabled" attribute or not
     *
     * @param buttonId - Select button for testing
     */
    public static void checkIfButtonIsDisplayed (String buttonId) {
        boolean isButtonDisplayed = driver.findElement(By.id(buttonId)).isDisplayed();
        Assert.assertTrue(isButtonDisplayed,"The button is displayed");
    }
}