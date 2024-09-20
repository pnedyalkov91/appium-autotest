package bg.appium.autotest.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static bg.appium.autotest.utils.Application.driver;

/**
 * Utility class for taking and managing screenshots in an Appium test environment.
 * <p>
 * This class provides methods to take screenshots from mobile elements and the mobile screen,
 * as well as delete saved screenshots. It also includes validation for the screenshot directory.
 */
public class ScreenshotHelper {

    // Logger to log information about screenshots taken or deleted
    private static final Logger logger = Logger.getLogger(ScreenshotHelper.class.getName());

    /**
     * Validates that the provided file path exists and is a directory.
     *
     * @param filePath The directory path where the screenshot should be saved.
     * @throws IOException If the directory does not exist or is not a valid directory.
     */
    private static void validateDirectory(String filePath) throws IOException {
        File directory = new File(filePath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("Invalid directory: " + filePath);
        }
    }

    /**
     * Takes a screenshot of the specified WebElement and saves it with the specified name
     * in the provided directory.
     *
     * @param element The WebElement from which to take the screenshot.
     * @param screenshotName The name to be given to the screenshot file.
     * @param filePath The directory where the screenshot should be saved.
     * @throws IOException If an I/O error occurs, such as invalid directory or file writing issues.
     */
    public static void takeScreenshotFromElement(WebElement element, String screenshotName, String filePath) throws IOException {
        validateDirectory(filePath); // Reuse validation logic

        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath, screenshotName);

        FileUtils.copyFile(srcFile, destFile);
        logger.info("Screenshot saved: " + destFile.getAbsolutePath());
    }

    /**
     * Takes a screenshot of the entire mobile screen and saves it with the specified name
     * in the provided directory.
     *
     * @param screenshotName The name to be given to the screenshot file.
     * @param filePath The directory where the screenshot should be saved.
     * @throws IOException If an I/O error occurs, such as invalid directory or file writing issues.
     */
    public static void takeScreenshotFromMobileScreen(String screenshotName, String filePath) throws IOException {
        validateDirectory(filePath);

        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath, screenshotName);

        FileUtils.copyFile(srcFile, destFile);
        logger.info("Screenshot saved: " + destFile.getAbsolutePath());
    }

    /**
     * Deletes the screenshot located at the specified file path.
     *
     * @param filePath The file path of the screenshot to delete.
     * @throws IOException If an I/O error occurs during file deletion.
     */
    public static void deleteScreenshot(String filePath) throws IOException {
        File screenshotFile = new File(filePath);
        if (screenshotFile.exists()) {
            FileUtils.delete(screenshotFile);
            logger.info("Screenshot deleted: " + filePath);
        } else {
            logger.warning("File not found for deletion: " + filePath);
        }
    }
}