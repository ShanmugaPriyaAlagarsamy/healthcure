package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseclass.BaseClass;

public class ExtentReport implements ITestListener {

    public static ExtentSparkReporter sparkreporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public void onStart(ITestContext context) {

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        sparkreporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "//reports//TestAutomationReport" + timestamp + ".html");
        sparkreporter.config().setDocumentTitle("Test Automation execution Report");
        sparkreporter.config().setTheme(Theme.STANDARD);
        sparkreporter.config().setReportName("Automation summary report");
        extent = new ExtentReports();
        extent.attachReporter(sparkreporter);

        String browserfromXml = context.getCurrentXmlTest().getParameter("browser");
        String osfromXml = context.getCurrentXmlTest().getParameter("os");

        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "priya");
        extent.setSystemInfo("Browser", browserfromXml);
        extent.setSystemInfo("OS", osfromXml);

    }

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest("Test Class: " + result.getTestClass().getName() + "<br>" + "Test Method: "
                + result.getMethod().getMethodName());
        test.log(Status.PASS, result.getMethod().getMethodName() + "is Passed");
        String message = ContextManager.getMessage();
        if (message != null) {
            test.log(Status.INFO, message);
        }
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest("Test Class: " + result.getTestClass().getName() + "<br>" + "Test Method: "
                + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getName() + "is Failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            test.log(Status.INFO, "Screenshot");
            String screenshotPath = new BaseClass().takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        // not implemented
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
