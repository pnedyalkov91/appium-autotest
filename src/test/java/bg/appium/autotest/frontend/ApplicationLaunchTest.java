package bg.appium.autotest.frontend;

import bg.appium.autotest.utils.Application;
import org.testng.annotations.Test;


public class ApplicationLaunchTest {

    @Test
    public void checkSplashScreen() {
        Application.launch();
    }
}