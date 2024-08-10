package POM.TestCases;

import CONFIG.Helpers.ExcelHelpers;
import POM.Base.BaseSetup;
import CONFIG.Contains.DataConfig;
import POM.DataProvider.DataProviderFactory;
import CONFIG.Drivers.DriverManager;
import POM.Page.DashboardPage;
import POM.Page.LoginPage;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class LoginTest extends BaseSetup {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    //    @Test
//    @Parameters({"Email","Password"})
//    public void testLoginSuccessNocheckRememberMe(String Email, String Password) {
//        loginPage = new LoginPage(driver);
//        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
//        dashboardPage = loginPage.loginCRM(Email, Password);
//        loginPage.verifyLoginScucess();
//        System.out.println(driver.getCurrentUrl());
//    }
    @Test
    public void testLoginSuccessNocheckRememberMe() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(DataConfig.EMAIL, DataConfig.PASSWORD);
        loginPage.verifyLoginScucess();
        System.out.println(DriverManager.getDriver().getCurrentUrl());
//        dashboardPage.logOutCRM();
    }

    //login với dataExcel
    @Test
    public void testLoginSuccessNocheckRememberMeWithDataExcel() {
        loginPage = new LoginPage();
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");
        for (int i = 1; i <= 3; i++) {
            dashboardPage = loginPage.loginCRM
                    (excelHelpers.getCellData("EMAIL", i),
                            excelHelpers.getCellData("PASSWORD", i));
            loginPage.verifyLoginScucess();
            dashboardPage.logOutCRM();
        }
    }
    //Login Provider
  @Test(dataProvider = "datalogin", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessNocheckRememberMeWithDataProvider(String Email, String Password) {
            loginPage = new LoginPage();
            dashboardPage = loginPage.loginCRM(Email,Password);
            loginPage.verifyLoginScucess();
            dashboardPage.logOutCRM();
        }
    //Login ProviderWithExcelFile
    @Test(dataProvider = "dataLoginFromExcel", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessNocheckRememberMeWithDataProviderExcelFile(String Email, String Password) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(Email,Password);
        loginPage.verifyLoginScucess();
        dashboardPage.logOutCRM();
    }
    //Login ProviderWithExcelFileMutibleRow
    @Test(dataProvider = "dataLoginFromExcelMutilbleRow", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessNocheckRememberMeWithDataProviderExcelFileMutibleRow(Hashtable< String, String > data) {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM(data.get("EMAIL"), data.get("PASSWORD"));
        loginPage.verifyLoginScucess();
        dashboardPage.logOutCRM();
    }
    @Test
    public void testLoginSuccesscheckRememberMe() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRMCheckRememberMe("admin@example.com", "123456");
        loginPage.verifyLoginScucess();
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin123@example.cosssdam", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithEmailInvalidWithDataExel() {
        loginPage = new LoginPage();
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");
        dashboardPage = loginPage.loginCRM
                (excelHelpers.getCellData("EMAIL", 2),
                        excelHelpers.getCellData("PASSWORD", 2));
        loginPage.verifyLoginScucess();
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithPasswordInvalid() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin123@example.com", "sds");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test
    public void testLoginFailWithEmailNull() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("", "sds");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void testLoginFailWithPasswordNull() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin123@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test
    public void testLoginFailWithEmailandPasswordNull() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("", "");
        loginPage.verifyLoginPassFailWhenEmailNull("The Email Address field is required.", "The Password field is required.");
    }

    @Test
    public void testClickForgotPasswordSuccess() throws InterruptedException {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin123@example.cosssdam", "123456");
        loginPage.clickForgotPassword();
        loginPage.verifyClickForgotPasswordSuccess();
        System.out.println(DriverManager.getDriver().getCurrentUrl());
    }

    @Test
    public void testClickForgotPasswordFail() throws InterruptedException {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRMCheckRememberMe("", "");
        loginPage.clickForgotPassword();
        loginPage.enterEmail("avc");
        loginPage.clickButtonConfirm();
//        loginPage.verifyForgotPassFailWhenEmailInvalid("A part followed by '@' should not contain the symbol ' '.");
    }

    @Test
    public void testForgotPasswordFailwithEmailNull() throws InterruptedException {
        loginPage = new LoginPage();
        DriverManager.getDriver().get(DataConfig.URL);
        loginPage.clickForgotPassword();
        loginPage.enterEmail("");
        loginPage.clickButtonConfirm();
        loginPage.verifyForgotPassWhenEmailNull("Email not found");
    }
}
