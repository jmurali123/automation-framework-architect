package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.DriverManager;
import utils.LocatorReader;

public class HtmlDropdown {

    private String locatorKey;

    public HtmlDropdown(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public void selectByText(String text){
        WebElement element = DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey));
        new Select(element).selectByVisibleText(text);
    }

    public void selectByValue(String value){
        WebElement element = DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey));
        new Select(element).selectByValue(value);
    }

    public void selectByIndex(int index){
        WebElement element = DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey));
        new Select(element).selectByIndex(index);
    }

    public String getSelectedValue(){
        WebElement element = DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey));
        return new Select(element).getFirstSelectedOption().getText();
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }
}