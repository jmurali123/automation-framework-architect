package pages.components;

import org.openqa.selenium.By;
import utils.DriverManager;
import utils.LocatorReader;

public class HtmlInput {

    private String locatorKey;

    public HtmlInput(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void enterText(String text){
        By locator = LocatorReader.get(locatorKey);
        DriverManager.getDriver()
                .findElement(locator)
                .clear();
        DriverManager.getDriver()
                .findElement(locator)
                .sendKeys(text);
    }

    public String getValue(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getAttribute("value");
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }

    public void clear(){
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .clear();
    }
}