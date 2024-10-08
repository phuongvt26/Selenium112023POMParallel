package POM.ScreenshotRecordVideo;

import CONFIG.Contains.DataConfig;
import CONFIG.Drivers.DriverManager;
import CONFIG.Helpers.CaptureHelper;
import CONFIG.Keywords.WebUI;
import POM.Base.BaseSetup;
import POM.Page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoScreenShot extends BaseSetup {

   @Test
    public void testHomePage1() {
//       SoftAssert softAssert = new SoftAssert();
        WebUI.openURL(DataConfig.URL);
        LoginPage loginPage = new LoginPage();
        loginPage.loginCRM(DataConfig.EMAIL, DataConfig.PASSWORD);
//       softAssert.assertEquals(DriverManager.getDriver().getTitle(), "Dashboard");
       Assert.assertEquals(DriverManager.getDriver().getTitle(), "Dashboard");
       CaptureHelper.screenshot("Screenshot login");
//       softAssert.assertAll();


   }

}
