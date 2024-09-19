package bg.appium.autotest.activities;

import bg.appium.autotest.utils.Application;
import io.appium.java_client.android.AndroidDriver;

import static bg.appium.autotest.core.AppiumSettings.appiumRemoteAddress;

public class SplashActivity extends Application implements Activity {

    @Override
    public void launchActivity() {
        capabilities.setCapability("appium:appActivity", ".splashscreen.SplashActivity");
        capabilities.setCapability("appium:autoGrantPermissions", true);
        driver = new AndroidDriver(appiumRemoteAddress(), capabilities);
    }
}
