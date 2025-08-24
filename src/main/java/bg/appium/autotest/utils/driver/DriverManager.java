package bg.appium.autotest.utils.driver;

import bg.appium.autotest.utils.appium.AppiumServer;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {
    private static AndroidDriver driver;

    public static void setDriver(DesiredCapabilities capabilities) {
        driver = new AndroidDriver(AppiumServer.getUrl(), capabilities);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
