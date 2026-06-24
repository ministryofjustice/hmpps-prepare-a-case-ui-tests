package org.pacfs.test.listeners;

import org.pacfs.framework.config.Settings;
import org.pacfs.framework.utilities.ExtendReport;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Ibi on 08/05/2026.
 */
public class ReportListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

        System.out.println("On Test Start");
        Settings.logs.Write("On Test Start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        System.out.println("On Test Success");
        Settings.logs.Write("On Test Success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        System.out.println("On Test Failure");
        Settings.logs.Write("On Test Failure");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

        System.out.println("On Test Skipped");
        Settings.logs.Write("On Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

        System.out.println("On Test Failed But Within Success Percentage");
        Settings.logs.Write("On Test Failed But Within Success Percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {

        System.out.println("On Start");
        Settings.logs.Write("On Start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        System.out.println("On Finish");
        Settings.logs.Write("On Finish");
        ExtendReport.extentReports.flush();
    }
}