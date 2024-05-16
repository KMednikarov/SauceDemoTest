package org.example.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.utils.reports.ExtentManager;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {

    @BeforeSuite
    public void beforeSuite() throws IOException {
        ExtentManager.createExtentReport();
    }

    @AfterSuite(alwaysRun=true)
    public void afterSuite() {
        ExtentManager.saveReport();
    }
}
