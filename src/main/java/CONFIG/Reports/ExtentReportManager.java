package CONFIG.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
//        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport/ExtentReport.html");
        ExtentSparkReporter reporter1 = new ExtentSparkReporter("reports/ExtentReport/ExtentReport1.html");
//        Cái "reports/extentreport/extentreport.html" là đường dẫn xuất ra file html của report. Folder "reports" sẽ nằm ngoài cùng ngang cấp src.
//        reporter.config().setReportName("Extent Report | Võ Thị Phương");
        reporter1.config().setReportName("Extent Report | Võ Thị Phương");
//        extentReports.attachReporter(reporter);
        extentReports.attachReporter(reporter1);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Võ Thị Phương");
        extentReports.setSystemInfo("Author", "Võ Thị Phương");
        extentReports.setSystemInfo("Version", "1.0");
        return extentReports;
    }
}
