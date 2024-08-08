package CONFIG.Helper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;


public class Capturehelper {
    public static void screenshot(String imageName) {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDrivers();
        File source = ts.getScreenshotAs(OutputType.FILE);

        File theDir = new File("./screenshots/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            FileHandler.copy(source, new File("./screenshots/testHomePage1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot success !!");

    }
}
