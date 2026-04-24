package pages.components;

import utils.DriverManager;
import utils.LocatorReader;

public class HtmlLink {

    private String locatorKey;

    public HtmlLink(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void click(){
        DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .click();
    }

    public String getText(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getText();
    }

    public String getHref(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .getAttribute("href");
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }
}