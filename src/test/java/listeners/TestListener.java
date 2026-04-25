package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result){
        result.getMethod().setRetryAnalyzerClass( RetryAnalyzer.class);
        System.out.println("=================================");
        System.out.println("Test Started : " + result.getName());
        System.out.println("Thread       : " + Thread.currentThread().getName()); // ← add this!
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

        // Take screenshot if WebDriver is available!
        if(DriverManager.getDriver() != null){
            takeScreenshot(result.getName());
        }
    }

    private void takeScreenshot(String testName){
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "target/screenshots/" + testName + "_"
                    + System.currentTimeMillis() + ".png";
            File finalDestination = new File(destination);
            finalDestination.getParentFile().mkdirs();
            Files.copy(source.toPath(), finalDestination.toPath());
            System.out.println("Screenshot saved: " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped: " + result.getName());
    }
}