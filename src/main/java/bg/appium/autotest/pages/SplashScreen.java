package bg.appium.autotest.pages;

import bg.appium.autotest.utils.ScreenshotHelper;

import java.io.IOException;

/**
 * The SplashScreen class represents the splash screen page of the mobile application
 * and provides methods to verify that the screen displays correctly using screenshots.
 */
public class SplashScreen {

    private static final String EXPECTED_SCREENSHOT_NAME = "Expected Splashscreen.png";
    private static final String ACTUAL_SCREENSHOT_NAME = "Actual Splashscreen.png";

    /**
     * Verifies that the splash screen matches the expected appearance.
     *
     * @throws IOException If an I/O error occurs while handling screenshots.
     */
    public static void verifySplashScreen() throws IOException {
        ScreenshotHelper.takeScreenshotFromMobileScreen(ACTUAL_SCREENSHOT_NAME);
        ScreenshotHelper.compareScreenshots(ACTUAL_SCREENSHOT_NAME, EXPECTED_SCREENSHOT_NAME);
        ScreenshotHelper.deleteScreenshot(ACTUAL_SCREENSHOT_NAME);
    }


}
