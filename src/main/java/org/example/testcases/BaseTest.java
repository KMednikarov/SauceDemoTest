package org.example.testcases;

import org.example.utils.reports.ExtentManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {


    @BeforeSuite
    public void beforeSuite() throws IOException {
        ExtentManager.setExtent();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.endReport();
    }
}
