package bg.appium.autotest.utils;

import bg.appium.autotest.activities.Activity;
import bg.appium.autotest.activities.MainActivity;
import bg.appium.autotest.activities.OnboardingActivity;
import bg.appium.autotest.activities.SplashActivity;
import bg.appium.autotest.exceptions.NoAppActivityFound;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static bg.appium.autotest.core.AppiumSettings.startNewAppiumSession;

public class Application {
    public static AndroidDriver driver;
    protected static DesiredCapabilities capabilities;

    @BeforeTest
    public static void launch(String activity) throws NoAppActivityFound {
        // startNewAppiumSession();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.accuweather.android");

        switch (activity) {
            case "Main":
                Activity mainActivity = new MainActivity();
                mainActivity.launchActivity();
                break;
            case "Onboarding":
                Activity onboardingActivity = new OnboardingActivity();
                onboardingActivity.launchActivity();
                break;
            case "Splash":
                Activity splashActivity = new SplashActivity();
                splashActivity.launchActivity();
                break;
            default:
                throw new NoAppActivityFound("No application activity found");
        }
    }

    @AfterTest
    public static void quit() {
        driver.quit();
    }
}