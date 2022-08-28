package Utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Objects;

import static Constants.MobileElementsIDs.*;

public class Buttons extends setup.SetupAppium {

    public static void clickBtnDigit (int btnNum) {

        String stringNum = Integer.toString(btnNum);

        for (int i = 0; i <= stringNum.length() - 1; i++) {
            driver.findElement(By.id("com.miui.calculator:id/btn_" + stringNum.charAt(i) + "_s")).click();
        }

    }
}
