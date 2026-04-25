package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.DriverManager;
import utils.LocatorReader;

import java.time.Duration;

public class BaseComponent {

    protected String locatorKey;

    public BaseComponent(String locatorKey){
        this.locatorKey = locatorKey;
    }

    protected WebElement waitForVisibility(){
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(
                        Long.parseLong(ConfigReader.get("explicit.wait"))));
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(LocatorReader.get(locatorKey)));
    }

    protected WebElement waitForClickability(){
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(
                        Long.parseLong(ConfigReader.get("explicit.wait"))));
        return wait.until(ExpectedConditions
                .elementToBeClickable(LocatorReader.get(locatorKey)));
    }

    public boolean isDisplayed(){
        return waitForVisibility().isDisplayed();
    }
}