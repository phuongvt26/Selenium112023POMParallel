package POM.Page;
import CONFIG.Contains.DataConfig;
import CONFIG.Keywords.WebUI;
import CONFIG.Drivers.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {
    //Khai báo driver cục bộ

    //Khai báo các element dạng đối tượng By
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By menuDasboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert alert-danger')]");
    private By forgotPassword = By.xpath("//a[contains(text(),'Forgot Password?')]");
    private By titlePageForgotPassword = By.xpath("//h1[normalize-space()='Forgot Password']");
    private By buttonConfirm = By.xpath("//button[normalize-space()='Confirm']");
    private By errorEmailNotFound = By.xpath("//div[@class='alert alert-danger text-center']");
    private By errorEmailRequired = By.xpath("//div[contains(text(),'The Email Address field is required.')]");
    private By errorPassRequired = By.xpath("//div[contains(text(),'The Password field is required.')]");

//    public static String DefindErrormessageInvalidEmailWhenForgotPass(String email) {
//        WebElement inputEmail = DriverManager.getDriver().findElement(By.xpath("//input[@id='email']"));
//        inputEmail.sendKeys(email);
//        String validationMessage = inputEmail.getAttribute("validationMessage");
//        return validationMessage;
//    }

    private By checkboxRememberme = By.xpath("//label[normalize-space()='Remember me']");


    public void enterEmail(String email) {
        WebUI.setText(inputEmail, email);
//        DefindErrormessageInvalidEmailWhenForgotPass(email);
    }

    public void enterPassword(String password) {
        WebUI.setText(inputPassword, password);
    }

    public void clickButtonLogin() {
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginScucess() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(menuDasboard), "Không redirect sang trang dasboard");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/", "url not match");
    }

    public void verifyLoginFail(String expectedMessage) {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(errorMessage), "error message not display");
        Assert.assertEquals(WebUI.getTextWebElement(errorMessage), expectedMessage, "FAIL. The content of error massge not match.");
    }

    public void clickForgotPassword() throws InterruptedException {
        Thread.sleep(2000);
        WebUI.clickElement(forgotPassword);
    }

    public void verifyClickForgotPasswordSuccess() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(titlePageForgotPassword), "titlePageForgotPassword not match");
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://crm.anhtester.com/admin/authentication/forgot_password", "url not match");

    }

//    public void verifyForgotPassFailWhenEmailInvalid(String expectedEmail) {
//        WebUI.waitForPageLoaded();
//        Assert.assertEquals(DefindErrormessageInvalidEmailWhenForgotPass(expectedEmail), "A part followed by '@' should not contain the symbol ' '.");
//    }

    public void verifyLoginPassFailWhenEmailNull(String expectedEmail, String expectedPass) {
        Assert.assertEquals(WebUI.getTextWebElement(errorEmailRequired), expectedEmail, "content of error msg not match");
        Assert.assertEquals(WebUI.getTextWebElement(errorPassRequired), expectedPass, "content of error msg not match");
    }

    public void verifyForgotPassWhenEmailNull(String expectedEmail) {
        Assert.assertEquals(WebUI.getTextWebElement(errorEmailNotFound), expectedEmail, "content of error msg not match");
    }

    public void clickButtonConfirm() {
        WebUI.clickElement(buttonConfirm);
    }

    public void clickCheckboxRememberMe() {
        WebUI.clickElement(checkboxRememberme);
    }

    public DashboardPage loginCRM(String email, String password) {
        WebUI.openURL(DataConfig.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
        return new DashboardPage();
    }

    public DashboardPage loginCRMCheckRememberMe(String email, String password) {
        WebUI.openURL(DataConfig.URL);
        WebUI.waitForPageLoaded();
        enterEmail(email);
        enterPassword(password);
        clickCheckboxRememberMe();
        clickButtonLogin();
        return new DashboardPage();
    }

}
