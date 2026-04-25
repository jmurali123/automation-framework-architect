package pages.components;

import org.openqa.selenium.interactions.Actions;
import utils.DriverManager;

public class HtmlButton extends BaseComponent {

    public HtmlButton(String locatorKey){
        super(locatorKey);
    }

    public void click(){
        waitForClickability().click();
    }

    public void doubleClick(){
        Actions actions = new Actions(DriverManager.getDriver());
        actions.doubleClick(waitForClickability()).perform();
    }

    public void hover(){
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(waitForVisibility()).perform();
    }

    public String getText(){
        return waitForVisibility().getText();
    }

    public boolean isEnabled(){
        return waitForVisibility().isEnabled();
    }
}