package org.example.utils.reports;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener extends ExtentManager implements ITestListener {

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getMethod().getDescription());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getMethod().getDescription(), ExtentColor.RED));
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, result.getMethod().getDescription());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.log(Status.INFO, "Test case failed but within success percentage: " + result.getMethod().getDescription());
    }

    public void onStart(ITestContext context) {
//        test.log(Status.INFO, "Test Execution Started");
    }

    public void onFinish(ITestContext context) {
            Map<String, Object> testResult = new HashMap<>();
            testResult.put("TotalTestCaseCount", context.getAllTestMethods().length);
            testResult.put("PassedTestCaseCount", context.getPassedTests().size());
            testResult.put("FailedTestCaseCount", context.getFailedTests().size());
            testResult.put("SkippedTestCaseCount", context.getSkippedTests().size());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String filePath = "target/test-output/ExtentReport/TestExecutionReport.json";
        try {
            mapper.writeValue(new File(filePath), testResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
