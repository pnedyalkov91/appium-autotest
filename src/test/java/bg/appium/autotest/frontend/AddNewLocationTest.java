package bg.appium.autotest.frontend;

import bg.appium.autotest.utils.Application;
import bg.appium.autotest.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;


public class AddNewLocationTest {

    String actualScreenshotName = "first_time_actual.png";
    String expectedScreenshotName = "first_time_expected.png";

    @Test(description = "Start the application and check splash screen")
    public void checkSplashScreen() {
        Allure.step("Start the application and check splash screen", () -> {
            Application.launch();
            ScreenshotUtils.takeScreenshotFromMobileScreen(actualScreenshotName);
            ScreenshotUtils.compareScreenshots(actualScreenshotName, expectedScreenshotName);
        });
    }
}