package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverManager;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseUITest {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpBrowser(
            @Optional("chrome") String browser){

        String gridUrl = ConfigReader.get("grid.url");
        boolean useGrid = gridUrl != null
                && !gridUrl.isEmpty();

        try {
            if(useGrid){
                // Grid execution!
                if(browser.equalsIgnoreCase("chrome")){
                    ChromeOptions options = new ChromeOptions();
                    DriverManager.setDriver(
                            new RemoteWebDriver(
                                    new URL(gridUrl), options));

                } else if(browser.equalsIgnoreCase("firefox")){
                    FirefoxOptions options = new FirefoxOptions();
                    DriverManager.setDriver(
                            new RemoteWebDriver(
                                    new URL(gridUrl), options));
                }

            } else {
                // Local execution!
                if(browser.equalsIgnoreCase("chrome")){
                    ChromeOptions options = new ChromeOptions();

                    if(System.getenv("CI") != null){
                        options.addArguments("--headless");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }

                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-features=PasswordManagerEnabled");
                    options.addArguments("--password-store=basic");
                    options.addArguments("--no-first-run");
                    options.addArguments("--disable-default-apps");

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);
                    options.setExperimentalOption("prefs", prefs);
                    options.setExperimentalOption("excludeSwitches",
                            Arrays.asList("enable-automation"));

                    WebDriverManager.chromedriver().setup();
                    DriverManager.setDriver(new ChromeDriver(options));

                } else if(browser.equalsIgnoreCase("firefox")){
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if(System.getenv("CI") != null){
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.addPreference(
                            "signon.rememberSignons", false);
                    firefoxOptions.addPreference(
                            "signon.autofillForms", false);
                    WebDriverManager.firefoxdriver().setup();
                    DriverManager.setDriver(
                            new FirefoxDriver(firefoxOptions));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(
                ConfigReader.get("saucedemo.base.url"));
    }

    @BeforeMethod(alwaysRun = true)
    public void navigateToBaseUrl(){
        if(DriverManager.getDriver() != null){
            DriverManager.getDriver().manage().deleteAllCookies();
            DriverManager.getDriver().get(
                    ConfigReader.get("saucedemo.base.url"));
        }
    }

    public static AjaxElementLocatorFactory getLocatorFactory(){
        return new AjaxElementLocatorFactory(
                DriverManager.getDriver(),
                Integer.parseInt(ConfigReader.get("ajax.timeout")));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownBrowser(){
        System.out.println("=== Closing browser ===");
        DriverManager.quitDriver();
        System.out.println("=== Browser closed ===");
    }
}