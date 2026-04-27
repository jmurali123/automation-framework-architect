package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IConfigurationListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener implements ITestListener, IConfigurationListener {

    private static final Logger logger =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result){
        logger.info("=================================");
        logger.info("Test Started : " + result.getName());
        logger.info("Thread       : " +
                Thread.currentThread().getName());
        logger.info("Class        : " +
                result.getTestClass().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        long duration = result.getEndMillis() -
                result.getStartMillis();
        logger.info("Test PASSED  : " + result.getName());
        logger.info("Duration     : " + duration + "ms");
        logger.info("=================================");
    }

    @Override
    public void onTestFailure(ITestResult result){
        long duration = result.getEndMillis() -
                result.getStartMillis();
        logger.error("Test FAILED  : " + result.getName());
        logger.error("Duration     : " + duration + "ms");
        logger.error("Reason       : " +
                result.getThrowable().getMessage());
        logger.info("=================================");

        if(DriverManager.getDriver() != null){
            takeScreenshot(result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result){
        logger.warn("Test SKIPPED : " + result.getName());
        logger.info("=================================");
    }

    @Override
    public void onConfigurationFailure(ITestResult result){
        logger.error("Configuration FAILED: " + result.getName());
        if(DriverManager.getDriver() != null){
            takeScreenshot("config_" + result.getName());
        }
    }

    private void takeScreenshot(String testName){
        try {
            TakesScreenshot ts =
                    (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "target/screenshots/" +
                    testName + "_" +
                    System.currentTimeMillis() + ".png";
            File finalDestination = new File(destination);
            finalDestination.getParentFile().mkdirs();
            Files.copy(source.toPath(),
                    finalDestination.toPath());
            logger.info("Screenshot saved: " + destination);
        } catch (IOException e) {
            logger.error("Screenshot failed: " + e.getMessage());
        }
    }
}