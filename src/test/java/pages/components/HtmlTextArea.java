package pages.components;

import utils.DriverManager;
import utils.LocatorReader;

public class HtmlTextArea {

    private String locatorKey;

    public HtmlTextArea(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void enterText(String text){
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .clear();
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .sendKeys(text);
    }

    public String getText(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getAttribute("value");
    }

    public void clear(){
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .clear();
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }
}