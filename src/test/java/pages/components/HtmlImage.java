package pages.components;

import utils.DriverManager;
import utils.LocatorReader;

public class HtmlImage {

    private String locatorKey;

    public HtmlImage(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }

    public String getSrc(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getAttribute("src");
    }

    public String getAltText(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getAttribute("alt");
    }
}