package pages.components;

public class HtmlImage extends BaseComponent {

    public HtmlImage(String locatorKey){
        super(locatorKey);
    }

    public String getSrc(){
        return waitForVisibility().getAttribute("src");
    }

    public String getAltText(){
        return waitForVisibility().getAttribute("alt");
    }
}