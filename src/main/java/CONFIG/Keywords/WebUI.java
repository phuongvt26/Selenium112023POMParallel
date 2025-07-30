package CONFIG.Keywords;

import CONFIG.Helpers.CaptureHelper;
import CONFIG.Helpers.PropertiesHelper;
import CONFIG.Helpers.SystemHelper;
import CONFIG.Reports.AllureManager;
import CONFIG.Reports.ExtentTestManager;
import CONFIG.Utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

import static CONFIG.Drivers.DriverManager.getDriver;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
    private static double STEP_TIME = Integer.parseInt(PropertiesHelper.getValue("STEP_TIME"));
    private static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            LogUtils.error(e.getMessage());
        }
    }
    public static void logConsole(Object message) {
        LogUtils.info(message);
    }
    @Step("Open URL {0}")
    public static void openURL(String url) {
       getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: "+ url);
        // Sử dụng hàm makeslug để thay thế k ự khoảng trắng/ký tự đặc biệt thành dấu gạch dưới
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("Open url:"+ url));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("Open url:"+ url));
            AllureManager.saveScreenshotPNG();
        }
    }
    @Step("Get Current URL ")
    public static String getCurrentUrl() {
        waitForPageLoaded();
        LogUtils.info("Get Current URL: " + getDriver().getCurrentUrl());
        ExtentTestManager.logMessage(Status.PASS, "Get Current URL: " + getDriver().getCurrentUrl());
        // Sử dụng hàm makeslug để thay thế k ự khoảng trắng/ký tự đặc biệt thành dấu gạch dưới
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")) {
            CaptureHelper.screenshot(SystemHelper.makeSlug("Get Current URL:" + getDriver().getCurrentUrl()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug(SystemHelper.makeSlug("Get Current URL:" + getDriver().getCurrentUrl())));
            AllureManager.saveScreenshotPNG();
        }
        return getDriver().getCurrentUrl();
    }
    @Step("Move to element {0}")
    public static void actionClassMoveTo(By by) {
        waitForElementToBeClickable(by);
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by)).build().perform();
        LogUtils.info("actionClassMoveTo");
        ExtentTestManager.logMessage(Status.PASS, "Move to element: "+ by);

    }
    @Step("Move to element toạ độ X {0} và Y {1}")
    public static void actionClassMoveToXY(int x, int y) {
        Actions action = new Actions(getDriver());
        action.moveByOffset(x, y).click().perform();
        LogUtils.info("actionClassMoveToXY");
        ExtentTestManager.logMessage(Status.PASS, "Move to element toạ độ x, y "+ x+ "," + y);

    }
    @Step("Switch to Alert")
    public static void Alert() {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
        LogUtils.info("Switchto Alert");
        ExtentTestManager.logMessage(Status.PASS, "Switch to Alert ");

    }
    @Step("Select Element {0} with value {1}")
    public static void selectElement(By by, String value) {
        Select select = new Select(getDriver().findElement((by)));
        select.selectByVisibleText(value);
        LogUtils.info("Select Element:" +by + "value: " +value);
        ExtentTestManager.logMessage(Status.PASS, "Select element: "+ by + "value: " +value);
    }
    @Step("Click element {0}")
    public static void clickElement(By by) {
        waitForElementToBeClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by);
        // Sử dụng hàm makeslug để thay thế k ự khoảng trắng/ký tự đặc biệt thành dấu gạch dưới
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("Click element:"+ by));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug(SystemHelper.makeSlug("Click element:"+ by)));
            AllureManager.saveScreenshotPNG();
        }
        ExtentTestManager.logMessage(Status.PASS, "Click on element: "+ by);
    }
    @Step("Click element {0} with timeout {1}")
    public static void clickElement(By by, long timeout) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDFE2 Click element: " + by + "timeout:" + timeout);
        ExtentTestManager.logMessage(Status.PASS, "Click element: "+ by + "timeout:" + timeout);
    }
    @Step("Get text element {0}")
    public static String getTextWebElement(By by) {
        waitForPageLoaded();
        String text = getDriver().findElement(by).getText().trim();
        LogUtils.info("Get text element: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text element: "+ text);
        AllureManager.saveTextLog("Get text element: "+ text);
        return text;
    }
    @Step("Get Element {0}")
    public static WebElement getWebElement(By by) {
        LogUtils.info("Get Element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Get element: "+ by);
        AllureManager.saveTextLog("Get element: "+ by);
        return getDriver().findElement(by);

    }
    @Step("Get list element {0}")
    public static List<WebElement> getWebElements(By by) {
        LogUtils.info("Get list element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Get list element "+ by);
        AllureManager.saveTextLog("Get list element: "+ by);
        return getDriver().findElements(by);
    }
    @Step("Set text {0} with value {1}")
    public static void setText(By by, String value) {
        waitForElementPresent(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("\uD83D\uDFE2 Set text: " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element: " + by);
        // Sử dụng hàm makeslug để thay thế k ự khoảng trắng/ký tự đặc biệt thành dấu gạch dưới
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("SET_TEXT_"+ by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("SET_TEXT_"+ by.toString()));
            AllureManager.saveScreenshotPNG();
        }

    }
    @Step("Set text {0} with value {1} and key {3}")
    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value+ "key: " +key.name()+ " on element: " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text and key element: "+ by + "value: "+ value+ "Key: "+ key);

    }
    @Step("Set key element {0} with key {1}")
    public static void setKey(By by, Keys keys) {
       getDriver().findElement(by).sendKeys(keys);
        LogUtils.info("\uD83D\uDFE2 Set key: " + keys.name() + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set key element: "+ by + "keys: "+ keys.name());
        AllureManager.saveTextLog("Set key element: "+ by + "keys: "+ keys.name());
        // Sử dụng hàm makeslug để thay thế k ự khoảng trắng/ký tự đặc biệt thành dấu gạch dưới
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_ALL").equals("true")){
            CaptureHelper.screenshot(SystemHelper.makeSlug("SET_KEY_"+ by.toString()));
            ExtentTestManager.addScreenshot(SystemHelper.makeSlug("SET_KEY_"+ by.toString()));
            AllureManager.saveScreenshotPNG();
        }
    }
    @Step("Get ElementAttribute element {0} with attributeName {1}")
    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("➡\uFE0F Attribute value: " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get ElementAttribute element: "+ by + "AttributeName: "+ attributeName);
        AllureManager.saveTextLog("Get ElementAttribute element: "+ by + "AttributeName: "+ attributeName);
        return text;
    }

    public static void scrollToElementOnTop(By element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(element));
        LogUtils.info("➡\uFE0F scrol To Element On Top : " + element);
        ExtentTestManager.logMessage(Status.PASS, "scroll To Element On Top "+element);
    }

    public static void scrollToElementOnBottom(By element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(element));
        LogUtils.info("➡\uFE0F scrol To Element On Bottom : " + element);
        ExtentTestManager.logMessage(Status.PASS, "scroll To Element On Bottom: "+element);
    }

    /**
     * Cuộn đến vị trí phần tử chỉ định
     *
     * @param element
     * @param position nếu giá trị 'true' thì cuộn lên trên. Nếu giá trị 'false' thì cuộn xuống dưới
     */
    public static void scrollToElement(By element, String position) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(" + position + ");", getWebElement(element));
        LogUtils.info("➡\uFE0F scrol To Element with position : " + element + "position: "+ position);
        ExtentTestManager.logMessage(Status.PASS, "scroll To Element On Bottom: "+element+ "position: "+ position);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("➡\uFE0F scrol To Element: " + element);
        ExtentTestManager.logMessage(Status.PASS, "scroll To Element: " +element+ "position: ");
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
        LogUtils.info("➡\uFE0F scrol To Position: " + X+","+ Y);
        ExtentTestManager.logMessage(Status.PASS, "scrol To Position:"+ X+","+ Y);
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(getDriver());
            action.moveToElement(getWebElement(toElement)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean mouseHover(By by) {
        try {
            Actions action = new Actions(getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='2px solid red'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }

    public static WebElement highLightElement(By by, String colorName) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='2px solid " + colorName + "'", getWebElement(by));
            sleep(STEP_TIME);
        }
        return getWebElement(by);
    }
    @Step("Verify equals actual {0} with expected {1}")
    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        boolean check = actual.equals(expected);
        return check;
    }
    @Step("Assert equals actual {0} with expected {1} and message {2}")
    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert equals: " + actual + "and: " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert equals: " + actual + "and: " + expected);
        Assert.assertEquals(actual, expected, message);
    }
    @Step("Verify contains actual {0} with expected {1}")
    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Verify contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Verify contains: " + actual + "and: " + expected);
        boolean check = actual.contains(expected);
        return check;
    }
    @Step("Assert contains actual {0} with expected {1} and message {2}")
    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("⭐\uFE0F Assert contains: " + actual + "and: " + expected);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Assert contains: " + actual + "and: " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());

        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());

        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementToBeClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementToBeClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());

        }
    }

    public static void waitForAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(WebDriver driver, By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void uploadFileDir(By by, String file) {
        file = (System.getProperty("user.dir") + "C:\\Users\\phuongvt26\\Downloads\\anh0 (2).jpg");
        LogUtils.info("⭐\uFE0F Upload file: " + file+ "on element: "+ by);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Upload file: " + file+ "on element: "+ by);
        getDriver().findElement(by).sendKeys(file);
    }
    @Step("Upload file on element {0} with path {1}")
    public static String uploafFile(By by, String path) {
        setText(by, path);
        File file = new File(path);
        LogUtils.info("⭐\uFE0F Upload file: " + path+ "on element: "+ by);
        ExtentTestManager.logMessage(Status.PASS, "⭐\uFE0F Upload file: " + path+ "on element: "+ by);
        return file.getAbsolutePath();
    }
    @Step("Check Element Exist {0}")
    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("✅ Check Element Exist: " + true + " ---> " + by);
            ExtentTestManager.logMessage(Status.PASS, "✅ Check Element Exist: " + true + " ---> " + by);
            AllureManager.saveTextLog("✅ Check Element Exist:"  + true + "--->" + by);
            return true;
        } else {
            LogUtils.error("❌ Check Element Exist: " + false + " ---> " + by);
            ExtentTestManager.logMessage(Status.FAIL, "❌ Check Element Exist: " + false + " ---> " + by);
            AllureManager.saveTextLog("✅ Check Element Exist:"  + false + "--->" + by);
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                LogUtils.error("FAILED. Timeout waiting for page load.");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForPageLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                LogUtils.error("FAILED. Timeout waiting for page load.");
                LogUtils.error(error.getMessage());
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

}
