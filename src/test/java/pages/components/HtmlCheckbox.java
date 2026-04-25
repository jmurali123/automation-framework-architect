package pages.components;

public class HtmlCheckbox extends BaseComponent {

    public HtmlCheckbox(String locatorKey){
        super(locatorKey);
    }

    public void check(){
        if(!isChecked()){
            waitForClickability().click();
        }
    }

    public void uncheck(){
        if(isChecked()){
            waitForClickability().click();
        }
    }

    public boolean isChecked(){
        return waitForVisibility().isSelected();
    }
}