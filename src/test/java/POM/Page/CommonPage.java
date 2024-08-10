package POM.Page;
import CONFIG.Keywords.WebUI;
import org.openqa.selenium.By;
public class CommonPage {
    private CommonPage commonPage;
    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProject = By.xpath("//span[normalize-space()='Projects']");

    public DashboardPage clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
        return new DashboardPage();
    }

    public CustomersPage clickMenuCustomers() {
        WebUI.clickElement(menuCustomers);
        return new CustomersPage();
    }

    public ProjectPage clickMenuProject() {
        WebUI.clickElement(menuProject);
        return new ProjectPage();
    }
}
