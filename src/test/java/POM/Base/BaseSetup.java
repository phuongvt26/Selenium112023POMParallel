package POM.Base;

import CONFIG.Helpers.CaptureHelper;
import CONFIG.Helpers.PropertiesHelper;
import CONFIG.Drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class BaseSetup {
    @BeforeSuite
    public void setupEnvironment(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver= setupDriver(browser);
        DriverManager.setDriver(driver); //gán giá trị driver vào threa local
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver = null;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        WebDriver driver = null;

        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        WebDriver driver = null;

        System.out.println("Launching Edge browser...");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        WebDriver driver = null;

        System.out.println("Launching Firefox browser...");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE)
            CaptureHelper.screenshot(iTestResult.getName());
        DriverManager.quit();
    }

    public void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
