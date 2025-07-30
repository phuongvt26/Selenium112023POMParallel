package POM.Listeners;

import CONFIG.Helpers.CaptureHelper;
import CONFIG.Helpers.PropertiesHelper;
import CONFIG.Reports.AllureManager;
import CONFIG.Reports.ExtentReportManager;
import CONFIG.Reports.ExtentTestManager;
import CONFIG.Utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }
    @Override
    public void onStart(ITestContext iTestResult) {
        LogUtils.info("⭐\uFE0F ***** START TESTING" +iTestResult.getStartDate()+ " *****");
        PropertiesHelper.loadAllFiles();
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onFinish(ITestContext iTestResult) {
        LogUtils.info("⭐\uFE0F ***** END FINISHED "+iTestResult.getEndDate()+ " *****");
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("⭐\uFE0F Start test case " + getTestName(iTestResult));
        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.startRecord(getTestName(iTestResult));
        }
        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("✅ Test case " + getTestName(iTestResult) + " succesed");
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")){
            CaptureHelper.screenshot(getTestName(iTestResult));
        }
        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, getTestName(iTestResult) + " is passed.");


    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("❌ Test case " + getTestName(iTestResult) + " Failed");
        LogUtils.error(iTestResult.getThrowable()); // Lấy ra lý do lỗi
        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());// in ra lý do lỗi
        ExtentTestManager.logMessage(Status.FAIL, getTestName(iTestResult) + " is failed.");
        //Allure Report
//        AllureManager.saveTextLog(iTestResult.getThrowable().toString());
//        AllureManager.saveTextLog(iTestResult.getName() + " is failed.");
        if(PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")){
            CaptureHelper.screenshot(getTestName(iTestResult));
            ExtentTestManager.addScreenshot(getTestName(iTestResult));
            AllureManager.saveScreenshotPNG();
        }
        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("\uD83D\uDD1C Test case "+ getTestName(iTestResult) + " skipped");
        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, iTestResult.getThrowable().toString());

    }
@Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.info("⭐\uFE0F onTestFailedButWithinSuccessPercentage");

    }
}
