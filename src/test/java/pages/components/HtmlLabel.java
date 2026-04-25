package pages.components;

public class HtmlLabel extends BaseComponent {

    public HtmlLabel(String locatorKey){
        super(locatorKey);
    }

    public String getText(){
        return waitForVisibility().getText();
    }
}