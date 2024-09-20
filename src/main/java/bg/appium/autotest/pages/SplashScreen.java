package bg.appium.autotest.pages;

import java.io.IOException;

import static bg.appium.autotest.core.ScreenshotHelper.*;
import static bg.appium.autotest.utils.Application.driver;

/**
 * The SplashScreen class represents the splash screen page of the mobile application
 * and provides methods to verify that the screen displays correctly using screenshots.
 * <p>
 * This class uses the ScreenshotHelper class to capture and compare screenshots,
 * ensuring that the splash screen matches the expected appearance.
 */
public class SplashScreen {

    // The directory where screenshots are stored
    private static final String SCREENSHOTS_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/screenshots/";

    // The expected splash screen image name (just the filename, not the full path)
    private static final String expectedScreenshotName = "Expected Splashscreen.png";

    /**
     * Verifies that the splash screen matches the expected appearance by:
     * - Taking a screenshot of the current splash screen.
     * - Comparing the current splash screen screenshot with an expected screenshot.
     * - Deleting the actual screenshot after the comparison is made.
     *
     * @throws IOException If an I/O error occurs, such as issues capturing or deleting screenshots.
     */
    public static void verifySplashScreen() throws IOException {
        String actualScreenshotName = "Actual Splashscreen.png"; // Name for the actual screenshot
        String actualScreenshotPath = SCREENSHOTS_DIRECTORY + actualScreenshotName; // Full path to the actual screenshot

        // Capture the screenshot of the splash screen
        takeScreenshotFromMobileScreen(actualScreenshotName, SCREENSHOTS_DIRECTORY);

        // Compare the actual splash screen screenshot with the expected one
        driver.matchImagesFeatures(
                actualScreenshotPath.getBytes(),
                (SCREENSHOTS_DIRECTORY + expectedScreenshotName).getBytes()
        );

        // Delete the actual splash screen screenshot after comparison
        deleteScreenshot(actualScreenshotPath);
    }
}
