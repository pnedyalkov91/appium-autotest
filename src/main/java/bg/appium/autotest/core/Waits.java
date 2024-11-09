package bg.appium.autotest.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    /**
     * Waits for the element located by the given locator to be clickable.
     *
     * @param driver   the WebDriver instance
     * @param locator  the locator used to find the element
     * @param duration the maximum time to wait in seconds
     */
    public static void waitForElementToBeClickable(WebDriver driver, By locator, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for the element located by the given locator to be visible.
     *
     * @param driver   the WebDriver instance
     * @param locator  the locator used to find the element
     * @param duration the maximum time to wait in seconds
     */
    public static void visibilityOf(WebDriver driver, By locator, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    /**
     * Waits for a button's text to update and then clicks it.
     *
     * @param driver the WebDriver instance
     * @param locator the By locator to find the button
     * @param expectedText the text to wait for
     * @param duration the maximum time to wait for the text to update
     */
    public static void waitForText(WebDriver driver, By locator, int duration, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
}