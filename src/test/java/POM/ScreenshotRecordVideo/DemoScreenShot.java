package POM.ScreenshotRecordVideo;

import CONFIG.Helper.Capturehelper;
import POM.Base.BaseSetup;
import POM.Contains.DataConfig;
import POM.Contains.WebUI;
import POM.Page.LoginPage;
import org.testng.annotations.Test;

public class DemoScreenShot extends BaseSetup {

   @Test
    public void testHomePage1() {
        WebUI.openURL(DataConfig.URL);
        LoginPage loginPage = new LoginPage();
        loginPage.loginCRM(DataConfig.EMAIL, DataConfig.PASSWORD);
//        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Dashboard");
       Capturehelper.screenshot("Screenshot Dashboard");
    }

}
