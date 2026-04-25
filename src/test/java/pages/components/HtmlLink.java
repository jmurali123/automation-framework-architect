package pages.components;

public class HtmlLink extends BaseComponent {

    public HtmlLink(String locatorKey){
        super(locatorKey);
    }

    public void click(){
        waitForClickability().click();
    }

    public String getText(){
        return waitForVisibility().getText();
    }

    public String getHref(){
        return waitForVisibility().getAttribute("href");
    }
}