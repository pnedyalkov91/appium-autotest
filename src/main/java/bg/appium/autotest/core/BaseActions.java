package bg.appium.autotest.core;

import bg.appium.autotest.utils.Application;
import org.openqa.selenium.By;

public class BaseActions {

    protected static void click(By locator) {
        Application.driver.findElement(locator).click();
    }

    protected static void type(By locator, String text) {
        Application.driver.findElement(locator).sendKeys(text);
    }

    protected static void getText(By locator) {
        Application.driver.findElement(locator).getText();
    }

    protected static void clear(By locator) {
        Application.driver.findElement(locator).clear();
    }
}
