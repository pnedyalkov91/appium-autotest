package bg.appium.autotest.frontend;

import bg.appium.autotest.exceptions.NoAppActivityFound;
import bg.appium.autotest.pages.SplashScreen;
import bg.appium.autotest.utils.Application;
import org.testng.annotations.Test;

import java.io.IOException;


public class SplashScreenTest {

    @Test
    public void checkSplashScreen() throws NoAppActivityFound, IOException {
        Application.launch("Splash");
        SplashScreen.verifySplashScreen();
    }


}