package POM.TestCases;

import POM.Base.BaseSetup;
import POM.Page.DashboardPage;
import POM.Page.LoginPage;
import POM.Page.ProjectPage;
import org.testng.annotations.Test;


public class ProjectTest extends BaseSetup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;

    @Test
    public void testClickMenuProject() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        projectPage = dashboardPage.clickMenuProject();
    }
//    @Test
//    public void testCheckCustomerDisplayInSelectDropdwon(){
//        testClickMenuProject();
//        projectPage.clickAddNewProject();
//        projectPage.checkCustomerDisplayInSelectDropdown("VTCC Viettel 11/06/2024 A1");
//    }
}
