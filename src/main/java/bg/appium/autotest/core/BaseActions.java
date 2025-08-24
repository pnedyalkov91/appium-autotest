package bg.appium.autotest.core;

import bg.appium.autotest.utils.Application;
import bg.appium.autotest.utils.driver.DriverManager;
import org.openqa.selenium.By;

public class BaseActions {

    protected static void click(By locator) {
        DriverManager.getDriver().findElement(locator).click();
    }

    protected static void type(By locator, String text) {
        DriverManager.getDriver().findElement(locator).sendKeys(text);
    }

    protected static void getText(By locator) {
        DriverManager.getDriver().findElement(locator).getText();
    }

    protected static void clear(By locator) {
        DriverManager.getDriver().findElement(locator).clear();
    }
}
