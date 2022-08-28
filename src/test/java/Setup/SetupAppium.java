package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Constants.AppiumCapabilities.*;


public class SetupAppium {

    public static URL url;
    public static DesiredCapabilities cap;
    public static AndroidDriver driver;

    @BeforeTest
    public void setupAppiumDriver() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);

        cap = new DesiredCapabilities();
        cap.setCapability("appium:platformName", PLATFORM_NAME);
        cap.setCapability("appium:platformVersion", ANDROID_VERSION);
        cap.setCapability("appium:deviceName", DEVICE_NAME);  // ID from the "About" menu
        cap.setCapability("appium:udid", UDID); //Device ID from "adb devices"
        cap.setCapability("appium:appPackage", APPLICATION_UNDER_TEST);
        cap.setCapability("appium:automationName", AUTOMATION_DRIVER);
        cap.setCapability("appium:newCommandTimeout", NEW_COMMAND_TIMEOUT);
        cap.setCapability("appium:fullReset", FULL_RESET);
        cap.setCapability("appium:noReset", NO_RESET);
        cap.setCapability("appium:appActivity", APP_ACTIVITY);

        driver = new AndroidDriver(url, cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}

