package POM.TestCases;

import POM.Base.BaseSetup;
import POM.Page.DashboardPage;
import POM.Page.LoginPage;
import org.testng.annotations.Test;


public class DashboardTest extends BaseSetup {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void testClickDashboard() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage.clickMenuDashboard();
        dashboardPage.clickMenuDashboard();
        dashboardPage.clickButtonDashboardOptions();
    }

    @Test
    public void testVerifySectionQuickStatisticsDisplay() {
        testClickDashboard();
        dashboardPage.verifySectionQuickStatistics();
    }

    @Test
    public void testVerifySectionFinanceOverviewDisplay() {
        testClickDashboard();
        dashboardPage.verifySectionFinanceOverview();
    }

    @Test
    public void testVerifySectionLatestProjectActivityDisplay() {
        testClickDashboard();
        dashboardPage.verifyLatestProjectActivity();
    }

    @Test
    public void testCheckTotalSectionStatistics() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkTotalInvoicesAwaitingPayment("3 / 3");
        dashboardPage.checktotalConvertedLeads("6 / 11");
        dashboardPage.checktotalProjectsInProgress("5 / 5");
        dashboardPage.checktotalTasksNotFinished("7 / 8");
    }

    @Test
    public void testCheckTotalSectionMyToDoItems() {
        testClickDashboard();
        dashboardPage.verifySectionMyToDoItems();
    }

    @Test
    public void testClickoptionSearch() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkSearch("test");
    }

    @Test
    public void testGetHistorySearch() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.getHistorySearch("ok");
    }

    @Test
    public void testDeleteHistorySearch() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkdeletehistorySearch();
    }

    @Test
    public void testClickNotificstion() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkclicknotification();
    }

    @Test
    public void testClicMarkAllAsRead() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkclicknotification();
        dashboardPage.checkClickMarkAllAsRead();
        dashboardPage.checkClickViewAllNotification();
    }

    @Test
    public void testPopupAddnewTodoDisplay() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
    }

    @Test
    public void testAddnewTodoWithDesscription() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
        dashboardPage.checkAddNewTodo("test");
    }

    @Test
    public void testAddnewTodoWithBlankDesscription() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
        dashboardPage.checkAddNewTodo("");
    }

    @Test
    public void verifyListTodoExitsTotoAdd() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkGetListTodo("8");
    }

    @Test
    public void testClickOutSitePopupAddNewTodo() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
        dashboardPage.checkClickOutSideAddNewTodo();
    }

    @Test
    public void testClickItemClosePopupAddNewToDo() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
        dashboardPage.checkCickItemClosePopupAddNewTodo();
    }

    @Test
    public void testClickClosePopupAddNewToDo() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.checkpopupAddnewTodoDisplay();
        dashboardPage.checkClosePopupAddNewTodo();
    }

}
