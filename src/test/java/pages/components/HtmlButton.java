package pages.components;

import org.openqa.selenium.By;
import utils.DriverManager;
import utils.LocatorReader;

public class HtmlButton {

    private String locatorKey;

    public HtmlButton(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void click(){
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .click();
    }

    public boolean isEnabled(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isEnabled();
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }

    public String getText(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getText();
    }
}