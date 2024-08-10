package POM.Page;

import CONFIG.Keywords.WebUI;
import CONFIG.Drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class DashboardPage extends CommonPage {


    // khai báo các element
    private By menuCustomers = By.xpath("//span[contains(text(),'Customers')]");
    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By listoptionDashboard = By.xpath("//div[normalize-space()='Dashboard Options']");
    private By totalCustomer = By.xpath("//span[normalize-space()='Total Customers']/parent::div/span[1]");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");
    private By checkboxFinanceOverview = By.xpath("//input[@id='widget_option_finance_overview']");
    private By checkMyToDoItems = By.xpath("//input[@id='widget_option_todos']");
    private By checkLatestProjectActivity = By.xpath("//input[@id='widget_option_projects_activity']");
    private By sectionQuickStatistics = By.xpath("//div[@id='widget-top_stats']");
    private By sectionFinanceOverview = By.xpath("//div[@id='widget-finance_overview']");
    private By sectionMytodoItems = By.xpath("//div[@id='widget-todos']");
    private By sectionLatestProjectActivity = By.xpath("//div[@id='widget-projects_activity']");
    private By inputSearch = By.xpath("//input[@id='search_input']");
    private By deleteSearch = By.xpath("(//i[@class='fa fa-remove'])[1]");
    private By notification = By.xpath("//a[contains(@class,'notifications-icon')]");
    private By dropdownNotification = By.xpath("//ul[@class='dropdown-menu notifications animated fadeIn width400 tw-pb-0']");
    private By MarkAllAsRead = By.xpath("//a[normalize-space()='Mark all as read']");
    private By buttonViewAllNotifications = By.xpath("//a[normalize-space()='View all notifications']");
    private By iteamTodo = By.xpath("//li[@class='icon header-todo']//a//span");
    private By buttonNewtodo = By.xpath("//a[normalize-space()='New To Do']");
    private By popupAddNewTodo = By.xpath("//div[@id='__todo']//div[@role='document']");
    private By buttonSaveAddNewTodo = By.xpath("//button[normalize-space()='Save']");
    private By buttonCloseAddNewTodo = By.xpath("//button[normalize-space()='Close']");
    private By listTodo = By.xpath("//ul[@class='list-unstyled todo unfinished-todos todos-sortable ui-sortable']");
    private By buttonLoadmorelistTodo = By.xpath("//a[@class='btn btn-default text-center unfinished-loader']");
    private By dropdownProfile = By.xpath("//li[contains(@class,'user-profile')]");
    private By optionLogout = By.xpath("//a[text()='Logout']");


    public void checkTotalInvoicesAwaitingPayment(String counttotalInvoicesAwaitingPayment) {
        Assert.assertTrue(WebUI.checkElementExist(totalInvoicesAwaitingPayment), "secction InvoicesAwaitingPayment not exits");
        Assert.assertEquals(WebUI.getWebElement(totalInvoicesAwaitingPayment).getText(), counttotalInvoicesAwaitingPayment, "FAIL. Invoices Awaiting Payment total not match.");
    }

    public void checktotalConvertedLeads(String counttotalConvertedLeads) {
        Assert.assertTrue(WebUI.checkElementExist(totalConvertedLeads), "secction InvoicesAwaitingPayment not exits");
        Assert.assertEquals(WebUI.getWebElement(totalConvertedLeads).getText(), counttotalConvertedLeads, "FAIL. The Coverted Lead total not match.");
    }

    public void checktotalProjectsInProgress(String counttotalProjectsInProgress) {
        Assert.assertTrue(WebUI.checkElementExist(totalProjectsInProgress), "secction InvoicesAwaitingPayment not exits");
        Assert.assertEquals(WebUI.getWebElement(totalProjectsInProgress).getText(), counttotalProjectsInProgress, "FAIL. The Projects InProgress total not match.");
    }

    public void checktotalTasksNotFinished(String counttotalTasksNotFinished) {
        Assert.assertTrue(WebUI.checkElementExist(totalTasksNotFinished), "secction InvoicesAwaitingPayment not exits");
        Assert.assertEquals(WebUI.getWebElement(totalTasksNotFinished).getText(), counttotalTasksNotFinished, "FAIL. The Tasks Not Finished total not match.");
    }

    public void clickButtonDashboardOptions() {
        WebUI.clickElement(listoptionDashboard);
    }

    public void verifySectionQuickStatistics() {
        WebUI.sleep(1);
        Assert.assertTrue(WebUI.getWebElement(checkboxQuickStatistics).isSelected(), "Fail, the vulue checkbox quick statistics is not select");
        Assert.assertTrue(WebUI.getWebElement(sectionQuickStatistics).isDisplayed(), "True, The section Quick Statistics is Display");
        WebUI.clickElement(checkboxQuickStatistics);
        Assert.assertFalse(WebUI.getWebElement(sectionQuickStatistics).isDisplayed(), "Fail, The section Quick Statistics is Display");
    }

    public void verifySectionFinanceOverview() {
        WebUI.sleep(1);
        Assert.assertTrue(WebUI.getWebElement(checkboxFinanceOverview).isSelected(), "Fail, the vulue checkbox Finance Overview is not select");
        Assert.assertTrue(WebUI.getWebElement(sectionFinanceOverview).isDisplayed(), "True, The section Finance Overview is Display");
        WebUI.clickElement(checkboxFinanceOverview);
        Assert.assertFalse(WebUI.getWebElement(sectionFinanceOverview).isDisplayed(), "Fail, The section Finance Overview is Display");
    }

    public void verifySectionMyToDoItems() {
        WebUI.sleep(1);
        Assert.assertTrue(WebUI.getWebElement(checkMyToDoItems).isSelected(), "Fail, the vulue checkbox MyToDoItems is not select");
        WebUI.clickElement(checkMyToDoItems);
        Assert.assertFalse(WebUI.getWebElement(sectionMytodoItems).isDisplayed(), "Fail, The section Finance Overview is Display");
    }

    public void verifyLatestProjectActivity() {
        WebUI.sleep(1);
        Assert.assertTrue(WebUI.getWebElement(checkLatestProjectActivity).isSelected(), "Fail, the vulue checkbox MyToDoItems is not select");
        WebUI.clickElement(checkLatestProjectActivity);
        Assert.assertFalse(WebUI.getWebElement(sectionLatestProjectActivity).isDisplayed(), "Fail, The section Finance Overview is Display");
    }

    public void checkSearch(String textSearch) {
        WebUI.setText(inputSearch, textSearch);
        WebUI.sleep(2);
        WebUI.clickElement(By.xpath("//div[@id='top_search']//li[3]"));
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/clients/client/46", "url not match");
    }

    public void getHistorySearch(String textsearch) {
        WebUI.setText(inputSearch, textsearch);
        WebUI.getWebElement(inputSearch).clear();
        WebUI.clickElement(inputSearch);
        List<WebElement> elements = DriverManager.getDriver().findElements(By.id("search-history"));
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).getText());
            Assert.assertTrue(elements.get(i).getText().contains(textsearch), "Không tồn tại text search");
        }
    }

    public void checkdeletehistorySearch() {
        WebUI.clickElement(inputSearch);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(deleteSearch), "Không tồn tại items");
        WebUI.clickElement(deleteSearch);
    }

    public void checkclicknotification() {
        WebUI.clickElement(notification);
        Assert.assertTrue(WebUI.getWebElement(dropdownNotification).isDisplayed(), "Not display dropdown notification");
    }

    public void checkClickMarkAllAsRead() {
        WebUI.clickElement(MarkAllAsRead);
    }

    public void checkClickViewAllNotification() {
        WebUI.clickElement(buttonViewAllNotifications);
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/profile?notifications=true", "url not match");

    }

    public void checkpopupAddnewTodoDisplay() {
        WebUI.clickElement(iteamTodo);
        WebUI.sleep(2);
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/todo", "url not match");
        WebUI.clickElement(buttonNewtodo);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.getWebElement(popupAddNewTodo).isDisplayed(), "Not display pop-up Add new todo");
    }

    public WebElement checkAddNewTodo(String description) {
        WebElement AddNewTodo = DriverManager.getDriver().findElement(By.xpath("//textarea[@id='description']"));
        AddNewTodo.sendKeys(description);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSaveAddNewTodo);
        return AddNewTodo;
    }

    public void checkGetListTodo(String todoName) {
        WebUI.clickElement(iteamTodo);
        WebUI.clickElement(buttonLoadmorelistTodo);
        WebUI.sleep(2);
        List<WebElement> elements = DriverManager.getDriver().findElements(listTodo);
        boolean isTextFound = false;

        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).getText());
            if (elements.get(i).getText().contains(todoName)) {
                isTextFound = true;
            } else {
                System.out.println("Không tồn tại todoName");
                break; // Exit the loop if the search text is not found
            }
        }
        Assert.assertTrue(isTextFound, "Không tồn tại todoName");

    }

    public void checkClosePopupAddNewTodo() {
        WebUI.clickElement(buttonCloseAddNewTodo);
    }

    public void checkCickItemClosePopupAddNewTodo() {
        WebUI.clickElement(iteamTodo);
    }

    public void checkClickOutSideAddNewTodo() {
        WebUI.actionClassMoveToXY(100,100);
    }

    public void logOutCRM() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(dropdownProfile);
        WebUI.clickElement(optionLogout);
    }


}
