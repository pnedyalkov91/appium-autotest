package bg.appium.autotest.utils.driver;

import bg.appium.autotest.utils.appium.AppiumServer;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Utility class for managing the Appium driver.
 */
public class DriverManager {
    // Initialize the driver variable
    private static AndroidDriver driver;

    /**
     * Sets the driver instance to the specified AndroidDriver.
     * @param capabilities The desired capabilities for the driver
     */
    public static void setDriver(DesiredCapabilities capabilities) {
        driver = new AndroidDriver(AppiumServer.getUrl(), capabilities);
    }

    /**
     * Returns the driver instance.
     *
     * @return The driver instance.
     */
    public static AndroidDriver getDriver() {
        return driver;
    }

    /**
     * Quits the driver if it is currently running.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
