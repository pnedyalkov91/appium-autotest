package bg.appium.autotest.core;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.SessionNotCreatedException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class AppiumSettings {

    public static URL appiumRemoteAddress() {
        try {
            return new URI("http://127.0.0.1:4723").toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startNewAppiumSession() {
        // Workaround if we receive "SessionNotCreatedException: Could not start a new session. Response code 500."
        if (Objects.equals(SystemUtils.OS_NAME, "Linux")) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", "adb uninstall io.appium.uiautomator2.server.test");
                processBuilder.start();
                processBuilder.command("bash", "-c", "adb uninstall io.appium.uiautomator2.server");
                processBuilder.start();
            }
            catch(SessionNotCreatedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("cmd.exe", "/c", "adb uninstall io.appium.uiautomator2.server.test");
                processBuilder.start();
                processBuilder.command("cmd.exe", "/c", "adb uninstall io.appium.uiautomator2.server");
                processBuilder.start();
            } catch (SessionNotCreatedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
    