package pages.components;

import utils.DriverManager;
import utils.LocatorReader;

public class HtmlRadioButton {

    private String locatorKey;

    public HtmlRadioButton(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void select(){
        if(!isSelected()){
            DriverManager.getDriver()
                    .findElement(LocatorReader.get(locatorKey))
                    .click();
        }
    }

    public boolean isSelected(){
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