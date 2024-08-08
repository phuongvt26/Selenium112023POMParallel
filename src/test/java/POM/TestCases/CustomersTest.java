package POM.TestCases;

import POM.Base.BaseSetup;
import POM.Contains.WebUI;
import POM.Page.CustomersPage;
import POM.Page.DashboardPage;
import POM.Page.LoginPage;
import POM.Page.ProjectPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomersTest extends BaseSetup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomersPage customersPage;
    ProjectPage projectPage;

    @Test
    public void testClickCustomersPage() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers(); //Hàm này nằm bên CommonPage
        customersPage.clickNewCustomer();
    }

    @Test
    public void testAddNewCustomerValidateFiledRequired() {
        testClickCustomersPage();
        customersPage.addNewCustomer("phuong test 1");
        customersPage.checkDetailCustomer("phuong test 1");
    }
    @Test
    public void testAddNewCustomerWithDataExcel() {
        testClickCustomersPage();
        customersPage.checkAddNewCustomerWithDataExcel("Khách hàng 2");
        customersPage.checkDetailCustomer("Khách hàng 2");
    }


    @Test
    public void testAddNewCustomerValidFullFiled() {
        String nameCustomer = "Selenium112023_Test18";
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers();
        int totalCustomerBefore = Integer.parseInt(customersPage.getTotalCustomers());
        customersPage.getTotalCustomers();
        System.out.println("Total customer after: " + customersPage.getTotalCustomers());
        customersPage.clickNewCustomer();
        customersPage.checkAddNewCustomer(nameCustomer);
        customersPage.checkCustomerInTableList(nameCustomer);
        Assert.assertEquals(customersPage.getTotalCustomers(), String.valueOf(totalCustomerBefore + 1), "\uD83D\uDC1E FAIL!! Count not equal");
        System.out.println("Total customer now: " + customersPage.getTotalCustomers()); // in tổng số customer hiện có
        customersPage.checkDetailCustomer(nameCustomer);
        projectPage = customersPage.clickMenuProject();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInSelectDropdown(nameCustomer);

    }

    @Test
    public void testCheckCountActiveCustomers() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers();
        customersPage.checkCountActiveCustomers("427");
    }

    @Test
    public void testCheckDeleteCustomer() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers();
        customersPage.checkDeleteCustomer("335");

        // Check xoá ở project nữa
    }

    @Test
    public void testAddNewContact() {
        String email = "Selenium_28@gmail.com";
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers();
        customersPage.checkAddNewContact(email);
        customersPage.checkCustomerInTableList(email);
        customersPage.checkDetailContact("Selenium_011", "Selenium_011", email, "0358153222");
    }

    @Test(description = " verify check Delete all customer/1 page")
    public void testDeleteAllCustomer() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");
        customersPage = dashboardPage.clickMenuCustomers();
        int totalCustomerBefore = Integer.parseInt(customersPage.getTotalCustomers());
        WebUI.logConsole("Total customer after: " + customersPage.getTotalCustomers());
//        System.out.println("Total customer after: "+customersPage.getTotalCustomers());
        customersPage.checkDeleteAllCustomer("Total customers deleted: 25");
        Assert.assertEquals(customersPage.getTotalCustomers(), String.valueOf(totalCustomerBefore - 25), "\uD83D\uDC1E FAIL!! Count not equal");
        System.out.println("Total customer now: " + customersPage.getTotalCustomers()); // in tổng số customer hiện có
    }
}
