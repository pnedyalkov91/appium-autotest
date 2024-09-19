package bg.appium.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static bg.appium.autotest.core.ScreenshotHelper.deleteScreenshot;
import static bg.appium.autotest.core.ScreenshotHelper.takeScreenshotFromElement;
import static bg.appium.autotest.utils.Application.driver;

public class SplashScreen {

    private static final String SCREENSHOTS_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/screenshots";
    private static final WebElement SPLASH_SCREEN = driver.findElement(By.id("com.accuweather.android:id/action_bar_root"));

    public static void verifySplashScreen() throws IOException {
        takeScreenshotFromElement(SPLASH_SCREEN, SCREENSHOTS_DIRECTORY);
        String expectedScreenshot = SCREENSHOTS_DIRECTORY + "/Expected Screenshot.png";
        String actualScreenshot = SCREENSHOTS_DIRECTORY + "/Actual Screenshot.png";
        driver.matchImagesFeatures(actualScreenshot.getBytes(), expectedScreenshot.getBytes());
        deleteScreenshot(actualScreenshot);
    }
}
