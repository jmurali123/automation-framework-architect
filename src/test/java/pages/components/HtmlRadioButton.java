package pages.components;

public class HtmlRadioButton extends BaseComponent {

    public HtmlRadioButton(String locatorKey){
        super(locatorKey);
    }

    public void select(){
        if(!isSelected()){
            waitForClickability().click();
        }
    }

    public boolean isSelected(){
        return waitForVisibility().isSelected();
    }
}