package bg.appium.autotest.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    public static File takeScreenshotFromElement(WebElement element, String filePath) throws IOException {
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath, "Actual Screenshot.png");
        FileUtils.copyFile(srcFile, destFile);
        return destFile;
    }

    public static void deleteScreenshot(String filePath) throws IOException {
        FileUtils.delete(new File(filePath));
    }
}
