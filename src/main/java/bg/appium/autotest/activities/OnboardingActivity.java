package bg.appium.autotest.activities;

import bg.appium.autotest.utils.Application;
import io.appium.java_client.android.AndroidDriver;

import static bg.appium.autotest.utils.AppiumServer.getAppiumUrl;

public class OnboardingActivity extends Application implements Activity {
    @Override
    public void launchActivity() {
        capabilities.setCapability("appium:appActivity", ".onboarding.OnboardingActivity");
        driver = new AndroidDriver(getAppiumUrl(), capabilities);
    }
}
