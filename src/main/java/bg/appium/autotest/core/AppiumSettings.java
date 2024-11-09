package bg.appium.autotest.core;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumSettings {

    public static URL appiumRemoteAddress() {
        try {
            return new URI("http://127.0.0.1:4723").toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
    