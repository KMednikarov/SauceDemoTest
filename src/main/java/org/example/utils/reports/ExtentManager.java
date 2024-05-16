package org.example.utils.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.IOException;

public class ExtentManager {
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static void createExtentReport() throws IOException {
        reporter = new ExtentSparkReporter(
                "target/test-output/ExtentReport/"
                        + "TestExecutionReport"
                        + ".html");

        reporter.viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{
                        ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.CATEGORY,
                        ViewName.EXCEPTION,
                        ViewName.LOG
                })
                .apply();
        reporter.loadXMLConfig("src/main/resources/reports/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    public static void saveReport() {
        extent.flush();
    }
}
