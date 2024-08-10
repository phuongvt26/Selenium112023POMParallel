package POM.ScreenshotRecordVideo;

import CONFIG.Contains.DataConfig;
import CONFIG.Drivers.DriverManager;
import CONFIG.Helpers.CaptureHelper;
import CONFIG.Keywords.WebUI;
import POM.Base.BaseSetup;
import POM.Page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoRecordVideo extends BaseSetup {
    @Test
    public void testHomePage1() {
        CaptureHelper.startRecord("Record testHomePage1");

        WebUI.openURL(DataConfig.URL);
        LoginPage loginPage = new LoginPage();
        loginPage.loginCRM(DataConfig.EMAIL, DataConfig.PASSWORD);
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Dashboard");
        CaptureHelper.stopRecord();

    }
}
