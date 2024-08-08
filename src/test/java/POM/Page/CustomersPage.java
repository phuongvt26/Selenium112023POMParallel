package POM.Page;

import CONFIG.Helper.ExcelHelpers;
import POM.Contains.WebUI;
import POM.Drivers.DriverManager;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

import static POM.Contains.WebUI.*;

public class CustomersPage extends CommonPage {
    private By buttonNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVat = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputGroups = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By inputCurrency = By.xpath("(//button[@data-id='default_currency'])//following-sibling::div//input");
    private By selectGroups = By.xpath("//button[@data-id='groups_in[]']");
    private By selectCurrency = By.xpath("(//button[@data-id='default_currency'])");
    private By selectLanguage = By.xpath("//button[@data-id='default_language']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By selectCountry = By.xpath("//button[@data-id='country']");
    private By inputCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By buttonSave = By.xpath("//button[@class='btn btn-primary only-save customer-form-submiter']");
    private By alertMessage = By.xpath("//span[@class='alert-title']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@placeholder='Search...']");
    private By firstItemCustomerName = By.xpath("//tbody//tr[1]//td[3]/a");
    private By firstItemCustomerID = By.xpath("//tbody/tr[1]//td[2]");
    private By buttonDeleteCustomer = By.xpath("//tbody//tr[1]//td[3]//a[3]");
    private By buttonContactCustomer = By.xpath("//tbody//tr[1]//td[3]//a[2]");
    private By buttonNewContact = By.xpath("//a[normalize-space()='New Contact']");
    private By countTotalCustomers = By.xpath("//span[normalize-space()='Total Customers']//parent::div/span[1]");
    private By countTotalActiveCustomers = By.xpath("//span[normalize-space()='Active Customers']//parent::div/span[1]");
    private By uploadProfileImage = By.xpath("//div[@id='contact-profile-image']//input");
    private By inputFirstName = By.xpath("//input[@id='firstname']");
    private By inputLastName = By.xpath("//input[@id='lastname']");
    private By inputTitle = By.xpath("//input[@id='title']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPhoneContact = By.xpath("//input[@id='phonenumber']");
    private By selectSystemDefault = By.xpath("//button[@title='System Default']");
    private By optionstemDefault = By.xpath("//select[@id='direction']/following-sibling::button");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonSaveAddContact = By.xpath("//button[normalize-space()='Save']");
    private By inputSearchContact = By.xpath("//div[@id='DataTables_Table_0_filter']//input[@placeholder='Search...']");
    private By firstItemEmailContact = By.xpath("//tbody//tr//td[2]//a");
    private By alertMessageDelete = By.xpath("//span[@class='alert-title']");
    private By checkboxAll = By.xpath("//div[@class='checkbox mass_select_all_wrap']");
    private By buttonBulkAction = By.xpath("//span[normalize-space()='Bulk Actions']");
    private By checkboxMassDelete = By.xpath("//label[normalize-space()='Mass Delete']");
    private By buttonConfirm = By.xpath("//a[normalize-space()='Confirm']");
    private By alertMessageDeleteAllCustomer = By.xpath("//span[@class='alert-title']");

    public void clickNewCustomer() {
        clickElement(buttonNewCustomer);
    }

    public void addNewCustomer(String CompanyName) {
        setText(inputCompany, CompanyName);
        clickElement(buttonSave);
    }

    public void checkSelectGroup(String Group) {
        clickElement(selectGroups);
        setText(inputGroups, Group);
        setKey(inputGroups, Keys.ENTER);
        clickElement(selectGroups);

    }

    public void checkSelectCurrency(String Currency) {
        clickElement(selectCurrency);
        WebUI.setText(inputCurrency, Currency);
        WebUI.setKey(inputCurrency, Keys.ENTER);
        clickElement(selectCurrency);

    }

    public void checkSelectLanguage(String Language) {
        clickElement(selectLanguage);
        clickElement(By.xpath("//span[normalize-space()='" + Language + "']"));
    }

    public void checkSelectCountry(String Country) {
        clickElement(selectCountry);
        WebUI.setText(inputCountry, Country);
        WebUI.setKey(inputCountry, Keys.ENTER);
        clickElement(selectCountry);

    }

    public void checkAddNewCustomer(String Company) {
        WebUI.setText(inputCompany, Company);
        WebUI.setText(inputVat, "123");
        WebUI.setText(inputPhone, "0358153222");
        WebUI.setText(inputWebsite, "0358153222");
        checkSelectGroup("VIP");
        checkSelectCurrency("USD");
        checkSelectLanguage("Vietnamese");
        WebUI.setText(inputAddress, "HN");
        WebUI.setText(inputCity, "HN");
        WebUI.setText(inputState, "HN");
        WebUI.setText(inputZip, "HN");
        checkSelectCountry("Vietnam");
        clickElement(buttonSave);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(alertMessage), "\uD83D\uDC1E FAIL!! The alert message success not display.");
        Assert.assertEquals(WebUI.getTextWebElement(alertMessage), "Customer added successfully.", "\uD83D\uDC1E \uD83D\uDC7A FAIL. The content of error massge not match.");
    }
    public void checkAddNewCustomerWithDataExcel(String Company) {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet2");
        WebUI.setText(inputCompany, Company);
        WebUI.setText(inputVat, excelHelpers.getCellData("VAT", 1));
        WebUI.setText(inputPhone, excelHelpers.getCellData("PHONE", 1));
        WebUI.setText(inputWebsite, excelHelpers.getCellData("WEBSITE", 1));
        checkSelectGroup("VIP");
        checkSelectCurrency("USD");
        checkSelectLanguage("Vietnamese");
        WebUI.setText(inputAddress, "HN");
        WebUI.setText(inputCity, "HN");
        WebUI.setText(inputState, "HN");
        WebUI.setText(inputZip, "HN");
        checkSelectCountry("Vietnam");
        clickElement(buttonSave);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(alertMessage), "\uD83D\uDC1E FAIL!! The alert message success not display.");
        Assert.assertEquals(WebUI.getTextWebElement(alertMessage), "Customer added successfully.", "\uD83D\uDC1E \uD83D\uDC7A FAIL. The content of error massge not match.");
    }

    public String getTotalCustomers() {
        return WebUI.getTextWebElement(countTotalCustomers);
    }

    // check customer detail in Customer Detail Page
    public void checkDetailCustomer(String Customer) {
        clickElement(firstItemCustomerName);
        WebUI.waitForPageLoaded();
        Assert.assertEquals(WebUI.getWebElement(inputCompany).getAttribute("value"), Customer);
        Assert.assertEquals(WebUI.getWebElement(inputVat).getAttribute("value"), "123");
        Assert.assertEquals(WebUI.getWebElement(inputPhone).getAttribute("value"), "0358153222");
        Assert.assertEquals(WebUI.getWebElement(inputWebsite).getAttribute("value"), "0358153222");
        Assert.assertEquals(WebUI.getWebElement(selectGroups).getAttribute("title"), "VIP");
        Assert.assertEquals(WebUI.getWebElement(selectCurrency).getAttribute("title"), "USD");
        Assert.assertEquals(WebUI.getWebElement(selectLanguage).getAttribute("title"), "Vietnamese");
        Assert.assertEquals(WebUI.getWebElement(inputAddress).getAttribute("value"), "HN");
        Assert.assertEquals(WebUI.getWebElement(inputCity).getAttribute("value"), "HN");
        Assert.assertEquals(WebUI.getWebElement(inputState).getAttribute("value"), "HN");
        Assert.assertEquals(WebUI.getWebElement(inputZip).getAttribute("value"), "HN");
        Assert.assertEquals(WebUI.getWebElement(selectCountry).getAttribute("title"), "Vietnam");
    }

    public void checkCountActiveCustomers(String count) {
        Assert.assertEquals(WebUI.getTextWebElement(countTotalActiveCustomers), count, "\uD83D\uDC1E FAIL!! Count not matching");
    }

    public void checkSelectSystemDefault(String SystemDefault) {
        clickElement(selectSystemDefault);
        clickElement(By.xpath("//span[normalize-space()='" + SystemDefault + "']"));
    }

    public void checkDeleteCustomer(String CustomerID) {
        WebUI.setText(inputSearchCustomer, CustomerID);
        WebUI.actionClassMoveTo(firstItemCustomerID);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(buttonDeleteCustomer), "\uD83D\uDC1E FAIL!! button Delete Customer not Exits");
        WebUI.sleep(3);
        clickElement(buttonDeleteCustomer);
        WebUI.Alert();
        WebUI.sleep(5);
//        Assert.assertTrue((driver.findElement(alertMessageDelete).isDisplayed()), "\uD83D\uDC1E FAIL!! Alert Message Delete not display");
//        Assert.assertEquals(WebUI.getTextWebElement(alertMessageDelete),AlertMessageDelete,"\uD83D\uDC1E FAIL!! Alert msg delete not matching" );
        WebUI.sleep(1);
        WebUI.setText(inputSearchCustomer, String.valueOf(CustomerID));
        WebUI.sleep(2);
        Assert.assertFalse(WebUI.checkElementExist(firstItemCustomerID), "\uD83D\uDC1E FAIL!! CustomerID Exits");
    }

    public void checkDeleteAllCustomer(String message) {
        WebUI.waitForPageLoaded();
        clickElement(checkboxAll);
        List<WebElement> listCheckboxALl = WebUI.getWebElements(By.xpath("//tbody/tr"));
        System.out.println(listCheckboxALl.size());
        WebUI.waitForPageLoaded();
        for (int i = 1; i < listCheckboxALl.size(); i++) {
            WebElement check = WebUI.getWebElement(By.xpath("//tbody/tr[" + i + "]/td[1]//input"));
            boolean isSelect = check.isSelected();
            System.out.println(check.getText());
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            // scroll đến cuối trang
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            Assert.assertTrue(isSelect, "FAIL, customer no check");
        }
        clickElement(buttonBulkAction);
        sleep(2);
        clickElement(checkboxMassDelete);
        clickElement(buttonConfirm);
        DriverManager.getDriver().switchTo().alert().accept();
        sleep(2);
        Assert.assertEquals(WebUI.getTextWebElement(alertMessageDeleteAllCustomer), message, "\uD83D\uDC1E FAIL!! Alert message delete all customer/page not macthing");

    }

    public void checkAddNewContact(String email) {
        actionClassMoveTo(firstItemCustomerName);
        sleep(1);
        Assert.assertTrue(WebUI.checkElementExist(buttonContactCustomer), "\uD83D\uDC1E FAIL!! button Contact Customer not Exits");
        sleep(1);
        clickElement(buttonContactCustomer);
        clickElement(buttonNewContact);
        sleep(2);
        Assert.assertTrue(WebUI.checkElementExist(uploadProfileImage), "\uD83D\uDC1E FAIL!! button upload not Exits");
//        WebUI.setText(uploadProfileImage, System.getProperty("user.dir") + "\\src\\Test\\Ảnh chụp màn hình (19).png");
        uploafFile(uploadProfileImage, "C:\\Users\\phuongvt26\\Downloads\\image_2024_06_03T03_03_57_641Z.png");
        uploafFile(uploadProfileImage, "C:\\Users\\phuongvt26\\Downloads\\DS BGĐ _thư ký 261223.xlsx");
        setText(inputFirstName, "Selenium_011");
        setText(inputLastName, "Selenium_011");
        setText(inputTitle, "Selenium_011");
        setText(inputEmail, email);
        setText(inputPhoneContact, "0358153222");
        sleep(1);
        checkSelectSystemDefault("LTR");
        sleep(1);
        setText(inputPassword, "Selenium_011");
        clickElement(buttonSaveAddContact);
        sleep(2);
    }

    public void checkCustomerInTableList(String email) {
        setText(inputSearchContact, email);
        sleep(2);
        Assert.assertEquals(WebUI.getTextWebElement(firstItemEmailContact), email, "\uD83D\uDC1E FAIL!, Email is not matching");
        System.out.println(WebUI.getTextWebElement(firstItemEmailContact));

    }

    public void checkDetailContact(String FristName, String LastName, String Email, String Password) {
        waitForPageLoaded();
        DriverManager.getDriver().findElement(By.xpath("//a[normalize-space()='Selenium_011 Selenium_011']")).click();
//       clickElement(firstItemCustomerName);
        waitForPageLoaded();
        sleep(2);
        Assert.assertEquals(WebUI.getWebElement(inputFirstName).getAttribute("value"), FristName, "\uD83D\uDC1E FAIL!, FristName is not matching");
        Assert.assertEquals(WebUI.getWebElement(inputLastName).getAttribute("value"), LastName, "\uD83D\uDC1E FAIL!, LastName is not matching");
        Assert.assertEquals(WebUI.getWebElement(inputTitle).getAttribute("value"), "Selenium_011", "\uD83D\uDC1E FAIL!, Position is not matching");
        Assert.assertEquals(WebUI.getWebElement(inputEmail).getAttribute("value"), Email, "\uD83D\uDC1E FAIL!, Email is not matching");
        Assert.assertEquals(WebUI.getWebElement(inputPhoneContact).getAttribute("value"), "0358153222", "\uD83D\uDC1E FAIL!, Phone is not matching");
        Assert.assertEquals(WebUI.getWebElement(optionstemDefault).getAttribute("title"), "LTR", "\uD83D\uDC1E FAIL!, System default is not matching");
    }

}