package pages.components;

import utils.DriverManager;
import utils.LocatorReader;

public class HtmlCheckbox {

    private String locatorKey;

    public HtmlCheckbox(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void check(){
        if(!isChecked()){
            DriverManager.getDriver()
                    .findElement(LocatorReader.get(locatorKey))
                    .click();
        }
    }

    public void uncheck(){
        if(isChecked()){
            DriverManager.getDriver()
                    .findElement(LocatorReader.get(locatorKey))
                    .click();
        }
    }

    public boolean isChecked(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isSelected();
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }
}