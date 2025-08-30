package bg.appium.autotest.utils;

import bg.appium.autotest.utils.appium.AppiumServer;
import bg.appium.autotest.utils.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.List;

/**
 * Utility class for managing the lifecycle of the Android application under test.
 * Handles launching, installation checks, and cleanup for Appium-based tests.
 */
public class Application {
    private static final String APPLICATION_PACKAGE = "org.breezyweather.debug";

    private static final Logger logger = LogManager.getLogger(Application.class);

    private static final DesiredCapabilities capabilities = new DesiredCapabilities();


    /**
     * Launches the application for testing.
     * Starts the Appium server, sets desired capabilities, and installs the app if not present.
     */
    @BeforeTest
    public static void launch() {
        AppiumServer.start();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appActivity", "org.breezyweather.ui.main.MainActivity");

        if (isAppInstalled()) {
            capabilities.setCapability("appium:appPackage", APPLICATION_PACKAGE);
        } else {
            Application.install();
        }

        DriverManager.setDriver(capabilities);
    }

    /**
     * Install the application from the specified APK file if it is not already installed.
     */
    private static void install() {
        try {
            File apkPath = new File(
                    System.getProperty("user.dir") + "/src/test/resources/application/application-debug.apk");
            capabilities.setCapability("appium:app", apkPath.getAbsolutePath());
            logger.info("The application is not installed. Installing from APK. Please wait...");
        } catch (Exception error) {
            throw new RuntimeException("adb command not found or failed to execute", error);
        }
    }

    /**
     * Checks if the application is installed on the connected Android device.
     *
     * @return true if the app is installed, false otherwise
     */
    private static boolean isAppInstalled() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    System.getenv("ANDROID_HOME") + "/platform-tools/adb", "shell", "pm", "list", "packages");
            Process process = processBuilder.start();
            List<String> adbOutput = process.inputReader().lines().toList();

            for (String line : adbOutput) {
                if (line.contains(Application.APPLICATION_PACKAGE)) {
                    logger.info("{} is installed", Application.APPLICATION_PACKAGE);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if app is installed", e);
        }
        return false;
    }

    /**
     * Quits the driver and stops the Appium server after tests are complete.
     */
    @AfterTest
    public static void quit() {
        DriverManager.quitDriver();
        AppiumServer.stop();
    }
}