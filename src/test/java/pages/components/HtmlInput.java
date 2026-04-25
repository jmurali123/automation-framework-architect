package pages.components;

public class HtmlInput extends BaseComponent {

    public HtmlInput(String locatorKey){
        super(locatorKey);
    }

    public void enterText(String text){
        waitForVisibility().clear();
        waitForVisibility().sendKeys(text);
    }

    public String getValue(){
        return waitForVisibility().getAttribute("value");
    }

    public void clear(){
        waitForVisibility().clear();
    }
}