package bg.appium.autotest.utils;

import bg.appium.autotest.utils.driver.DriverManager;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static bg.appium.autotest.utils.Application.driver;

/**
 * Utility class for taking and managing screenshots in an Appium test environment.
 */
public class ScreenshotUtils {

    private static final Path SCREENSHOTS_DIRECTORY = Paths.get("src", "test", "resources", "screenshots");
    private static final double SIMILARITY_THRESHOLD = 0.01;

    /**
     * Takes a screenshot of the specified WebElement and saves it.
     *
     * @param element The WebElement from which to take the screenshot.
     * @param screenshotName The name to be given to the screenshot file.
     * @param filePath The directory where the screenshot should be saved.
     * @throws IOException If an I/O error occurs.
     */
    public static void takeScreenshotFromElement(WebElement element, String screenshotName, String filePath) throws IOException {
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        Path destPath = Paths.get(filePath, screenshotName);
        FileUtils.copyFile(srcFile, destPath.toFile());
    }

    /**
     * Takes a screenshot of the mobile screen and saves it.
     *
     * @param screenshotName The name to be given to the screenshot file.
     * @throws IOException If an I/O error occurs.
     */
    public static void takeScreenshotFromMobileScreen(String screenshotName) throws IOException {
        File srcFile = DriverManager.getDriver().getScreenshotAs(OutputType.FILE);
        Path destPath = SCREENSHOTS_DIRECTORY.resolve(screenshotName);
        FileUtils.copyFile(srcFile, destPath.toFile());
    }

    /**
     * Deletes the specified screenshot.
     *
     * @param screenshotFilename The name of the screenshot file to delete.
     * @throws IOException If an I/O error occurs.
     */
    public static void deleteScreenshot(String screenshotFilename) throws IOException {
        Path screenshotPath = SCREENSHOTS_DIRECTORY.resolve(screenshotFilename);
        File screenshotFile = screenshotPath.toFile();
        if (screenshotFile.exists()) {
            FileUtils.forceDelete(screenshotFile);
        } else {
            throw new NoSuchFileException("Screenshot not found: " + screenshotFilename);
        }
    }

    /**
     * Compares two screenshots and verifies similarity score is above the threshold.
     *
     * @param actualScreenshot The filename of the actual screenshot.
     * @param expectedScreenshot The filename of the expected screenshot.
     * @throws IOException If an I/O error occurs.
     */
    public static void compareScreenshots(String actualScreenshot, String expectedScreenshot) throws IOException {
        Path actualPath = SCREENSHOTS_DIRECTORY.resolve(actualScreenshot);
        Path expectedPath = SCREENSHOTS_DIRECTORY.resolve(expectedScreenshot);

        SimilarityMatchingOptions options = new SimilarityMatchingOptions().withEnabledVisualization();
        SimilarityMatchingResult result = DriverManager.getDriver().getImagesSimilarity(expectedPath.toFile(), actualPath.toFile(), options);

        Assert.assertTrue(result.getScore() > SIMILARITY_THRESHOLD, "Similarity result is: " + result.getScore());
    }
}
