package bg.appium.autotest.utils;

import bg.appium.autotest.utils.appium.AppiumServer;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Application {
    public static AndroidDriver driver;
    protected static DesiredCapabilities capabilities;

    @BeforeTest
    public static void launch() {
        AppiumServer.start();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.breezyweather.debug");
        capabilities.setCapability("appium:appActivity", "org.breezyweather.ui.main.MainActivity");
        driver = new AndroidDriver(AppiumServer.getUrl(), capabilities);
    }

    @AfterTest
    public static void quit() {
        driver.quit();
        AppiumServer.stop();
    }
}