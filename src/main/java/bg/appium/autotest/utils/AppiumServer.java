package bg.appium.autotest.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.output.TeeOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static bg.appium.autotest.utils.Application.appiumPlugin;

/**
 *
 * Provides methods to start and stop the Appium server, redirecting logs to a file and the console,
 * and configuring the server settings based on the system environment.
 *
 */
public class AppiumServer {

    static AppiumDriverLocalService server;

    /**
     * Starts the Appium server if it is not already running.
     *
     * @throws IOException if an I/O error occurs during server startup.
     */
    public static void start() throws IOException {
        if (server == null || !server.isRunning()) {
            server = getServer();
            server.start();
        }
    }

    /**
     * Stops the Appium server if it is currently running.
     *
     * @throws IOException if an I/O error occurs during server shutdown.
     */
    public static void stop() throws IOException {
        if (server != null) {
            getServer().stop();
        }
    }

    /**
     * Retrieves the Appium server instance, initializing it if necessary.
     *
     * @return the AppiumDriverLocalService instance representing the Appium server.
     * @throws IOException if an I/O error occurs during server setup.
     */
    private static AppiumDriverLocalService getServer() throws IOException {
        if (server == null) {
            PrintStream printStream = appiumLogger();
            AppiumServiceBuilder builder = getAppiumServiceBuilder();
            server = AppiumDriverLocalService.buildService(builder);

            // Redirect output to both console and file
            server.addOutPutStream(printStream);
        }
        return server;
    }

    /**
     * Creates and configures an AppiumServiceBuilder with appropriate settings.
     *
     * @return an AppiumServiceBuilder instance with specified configurations.
     */
    private static AppiumServiceBuilder getAppiumServiceBuilder() {
        return new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withAppiumJS(getAppiumPath())
                .withArgument(GeneralServerFlag.USE_PLUGINS, getArgumentsPerActivity())
                .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug:error")
                .withArgument(GeneralServerFlag.LOG_NO_COLORS);
    }

    /**
     * Returns the URL (IP address + Port) of the running Appium server.
     *
     * @return a URL pointing to the Appium server.
     */
    public static URL getAppiumUrl() {
        return server.getUrl();
    }

    /**
     * Determines the argument for plugins based on the current Appium activity.
     *
     * @return a string representing the plugin argument if needed, otherwise null.
     */
    private static String getArgumentsPerActivity() {
        return "images".equals(appiumPlugin) ? "images" : null;
    }

    /**
     * Resolves the file path to the Appium main.js executable based on the system configuration.
     *
     * @return a File pointing to the main.js script.
     */
    private static File getAppiumPath() {
        String appiumHome = System.getenv("APPIUM_HOME");

        if (appiumHome != null) {
            return Paths.get(appiumHome, "build", "lib", "main.js").toFile();
        }

        String os = System.getProperty("os.name").toLowerCase();
        Path userHomeDir = Paths.get(System.getProperty("user.home"));

        if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            return new File("/usr/local/lib/node_modules/appium/build/lib/main.js");
        } else {
            return userHomeDir.resolve("AppData/Roaming/npm/node_modules/appium/build/lib/main.js").toFile();
        }
    }

    /**
     * Sets up a logger for the Appium server, outputting to both a timestamped log file and the console.
     *
     * @return a PrintStream configured with both console and file outputs.
     * @throws IOException if an I/O error occurs during logger setup.
     */
    private static PrintStream appiumLogger() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String timestamp = LocalDateTime.now().format(formatter);
        String logFileName = ".logs/" + timestamp + ".log";

        File logFile = new File(logFileName);
        FileOutputStream fileOut = new FileOutputStream(logFile);
        TeeOutputStream teeOut = new TeeOutputStream(System.out, fileOut);

        return new PrintStream(teeOut, true);
    }
}