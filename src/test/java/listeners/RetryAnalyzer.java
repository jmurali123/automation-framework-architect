package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = Integer.parseInt(ConfigReader.get("retry.count"));;

    @Override
    public boolean retry(ITestResult result) {
        if(retryCount < MAX_RETRY){
            retryCount++;
            System.out.println("Retrying test: " + result.getName()
                    + " attempt " + retryCount);
            return true;
        }
        return false;
    }
}