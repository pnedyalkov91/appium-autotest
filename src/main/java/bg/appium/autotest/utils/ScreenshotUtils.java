package bg.appium.autotest.utils;

import bg.appium.autotest.utils.driver.DriverManager;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for taking and managing screenshots in an Appium test environment.
 */
public class ScreenshotUtils {

    private static final Path SCREENSHOTS_DIRECTORY = Paths.get("src", "test", "resources", "screenshots");
    private static final double SIMILARITY_THRESHOLD = 0.01;
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    /**
     * Saves a screenshot file to the screenshots directory with the given name.
     *
     * @param srcFile        the source screenshot file
     * @param screenshotName the name to save the screenshot as
     * @throws RuntimeException if saving fails
     */
    private static void saveScreenshot(File srcFile, String screenshotName) {
        try {
            Path destPath = SCREENSHOTS_DIRECTORY.resolve(screenshotName);
            FileUtils.copyFile(srcFile, destPath.toFile());
            logger.info("Screenshot saved: {}", screenshotName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + screenshotName, e);
        }
    }

    /**
     * Takes a screenshot of the specified WebElement and saves it.
     *
     * @param element        the WebElement to capture
     * @param screenshotName the name to save the screenshot as
     */
    public static void takeScreenshotFromElement(WebElement element, String screenshotName) {
        saveScreenshot(element.getScreenshotAs(OutputType.FILE), screenshotName);
    }

    /**
     * Takes a screenshot of the specified WebElement and saves it.
     *
     * @param screenshotName the name to save the screenshot as
     */
    public static void takeScreenshotFromMobileScreen(String screenshotName) {
        saveScreenshot(DriverManager.getDriver().getScreenshotAs(OutputType.FILE), screenshotName);
    }

    /**
     * Deletes a screenshot file from the screenshots' directory.
     *
     * @param screenshotFilename the filename of the screenshot to delete
     */
    public static void deleteScreenshot(String screenshotFilename) {
        Path screenshotPath = SCREENSHOTS_DIRECTORY.resolve(screenshotFilename);
        File screenshotFile = screenshotPath.toFile();
        if (screenshotFile.exists()) {
            try {
                FileUtils.forceDelete(screenshotFile);
                logger.info("Screenshot deleted: {}", screenshotFilename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete screenshot: " + screenshotFilename, e);
            }
        } else {
            throw new RuntimeException("Screenshot not found: " + screenshotFilename);
        }
    }

    /**
     * Compares two screenshots for visual similarity and asserts that the similarity score exceeds the threshold.
     *
     * @param actualScreenshot   the filename of the actual screenshot
     * @param expectedScreenshot the filename of the expected screenshot
     * @throws RuntimeException if comparison fails
     */
    public static void compareScreenshots(String actualScreenshot, String expectedScreenshot) {
        boolean isActualScreenshotChecked = false;

        try {
            Path actualPath = SCREENSHOTS_DIRECTORY.resolve(actualScreenshot);
            Path expectedPath = SCREENSHOTS_DIRECTORY.resolve(expectedScreenshot);

            SimilarityMatchingOptions options = new SimilarityMatchingOptions().withEnabledVisualization();
            SimilarityMatchingResult result = DriverManager.getDriver().getImagesSimilarity(
                    expectedPath.toFile(), actualPath.toFile(), options);

            Assert.assertTrue(result.getScore() > SIMILARITY_THRESHOLD, "Similarity result is: " + result.getScore());
        } catch (IOException e) {
            throw new RuntimeException("Failed to compare screenshots", e);
        }

        if (!isActualScreenshotChecked) {
            deleteScreenshot(actualScreenshot);
        }
    }
}
