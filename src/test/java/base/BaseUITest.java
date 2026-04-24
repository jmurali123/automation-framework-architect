package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseUITest {

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser(){

        String browser = ConfigReader.get("browser");

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            DriverManager.setDriver(new ChromeDriver());
        } else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            DriverManager.setDriver(new FirefoxDriver());
        }

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(
                ConfigReader.get("saucedemo.base.url"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownBrowser(){
        DriverManager.quitDriver();
    }
}