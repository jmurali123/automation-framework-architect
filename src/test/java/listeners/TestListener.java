package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result){
        result.getMethod().setRetryAnalyzerClass( RetryAnalyzer.class);
        System.out.println("=================================");
        System.out.println("Test Started : " + result.getName());
        System.out.println("Class        : " + result.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("Test Passed  : " + result.getName());
        System.out.println("Duration     : " + duration + "ms");
        System.out.println("=================================");
    }

    @Override
    public void onTestFailure(ITestResult result){
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("Test FAILED  : " + result.getName());
        System.out.println("Duration     : " + duration + "ms");
        System.out.println("Reason       : " + result.getThrowable().getMessage());
        System.out.println("=================================");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped: " + result.getName());
    }
}