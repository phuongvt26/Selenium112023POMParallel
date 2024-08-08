package POM.Page;

import POM.Contains.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class ProjectPage extends CommonPage {

    private By buttonNewProject = By.xpath("//a[normalize-space()='New Project']");
    private By selectCustomer = By.xpath("//button[@data-id='clientid']");
    private By inputSearchCustomer = By.xpath("//button[@data-id='clientid']/following-sibling::div//input");
    private By itemCustomerName = By.xpath("//span[@class='text']");

    public void clickAddNewProject() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonNewProject);
    }

    public void checkCustomerDisplayInSelectDropdown(String Customer) {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(selectCustomer);
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer, Customer);
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getTextWebElement(itemCustomerName), Customer, "\uD83D\uDC1E FAIL!! CustomerName not display in project");

    }


}
