package common.reportconfigure;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import common.GlobalConstants;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.getGlobalConstants().getROOT_FOLDER() + "/extentV5/ExtentReport.html");
		reporter.config().setReportName("Sytner HTML Report");
		reporter.config().setDocumentTitle("Sytner HTML Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.STANDARD);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Project", "Sytner");
		extentReports.setSystemInfo("Team", "VN");
		extentReports.setSystemInfo("JDK version", GlobalConstants.getGlobalConstants().getJAVA_VERSION());
		return extentReports;
	}
}