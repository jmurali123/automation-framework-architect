package pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HtmlDropdown extends BaseComponent {

    public HtmlDropdown(String locatorKey){
        super(locatorKey);
    }

    public void selectByText(String text){
        new Select(waitForVisibility()).selectByVisibleText(text);
    }

    public void selectByValue(String value){
        new Select(waitForVisibility()).selectByValue(value);
    }

    public void selectByIndex(int index){
        new Select(waitForVisibility()).selectByIndex(index);
    }

    public String getSelectedValue(){
        return new Select(waitForVisibility())
                .getFirstSelectedOption().getText();
    }
}