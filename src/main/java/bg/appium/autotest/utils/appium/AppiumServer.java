package bg.appium.autotest.utils.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import java.net.URL;
import java.time.Duration;

/**
 *
 * Provides methods to start and stop the Appium server, redirecting logs to a file and the console,
 * and configuring the server settings based on the system environment.
 *
 */
public class AppiumServer {
    private static AppiumDriverLocalService appiumServer;
    private static final Logger logger = LogManager.getLogger(AppiumServer.class);

    /**
     * Starts the Appium server if it is not already running.
     *
     */
    public static void start() {
        if (appiumServer == null || !appiumServer.isRunning()) {
            appiumServer = setAppiumServer();
            appiumServer.start();
        }
    }

    /**
     * Stops the Appium server if it is currently running.
     *
     */
    public static void stop() {
        if (appiumServer != null) {
            setAppiumServer().stop();
        }
    }

    /**
     * Returns the URL (IP address + Port) of the running Appium server.
     *
     * @return a URL pointing to the Appium server.
     */
    public static URL getUrl() {
        return appiumServer.getUrl();
    }


    /**
     * Retrieves the Appium server instance, initializing it if necessary.
     *
     * @return the AppiumDriverLocalService instance representing the Appium server.
     */
    private static AppiumDriverLocalService setAppiumServer() {
        if (appiumServer == null) {
            LogUtils.deleteOldLogFiles();
            AppiumServiceBuilder builder = setAppiumConfiguration();
            appiumServer = AppiumDriverLocalService.buildService(builder);
            appiumServer.addOutPutStream(IoBuilder.forLogger(logger).buildOutputStream());
        }
        return appiumServer;
    }


    /**
     * Creates and configures an AppiumServiceBuilder with appropriate settings.
     *
     * @return an AppiumServiceBuilder instance with specified configurations.
     */
    private static AppiumServiceBuilder setAppiumConfiguration() {
        return new AppiumServiceBuilder()
                .withTimeout(Duration.ofSeconds(60))
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug:error")
                .withArgument(GeneralServerFlag.LOG_NO_COLORS);
    }
}
