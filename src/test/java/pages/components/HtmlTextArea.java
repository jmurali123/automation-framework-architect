package pages.components;

public class HtmlTextArea extends BaseComponent {

    public HtmlTextArea(String locatorKey){
        super(locatorKey);
    }

    public void enterText(String text){
        waitForVisibility().clear();
        waitForVisibility().sendKeys(text);
    }

    public String getText(){
        return waitForVisibility().getAttribute("value");
    }

    public void clear(){
        waitForVisibility().clear();
    }
}