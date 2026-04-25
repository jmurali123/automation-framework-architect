package pages.components;

import annotations.FindByLocator;
import decorators.CustomFieldDecorator;
import base.BaseUITest;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {

    @FindByLocator("product.burger.menu")
    private HtmlButton burgerMenu;

    @FindByLocator("product.logout")
    private HtmlButton logoutButton;

    @FindByLocator("product.cart.icon")
    private HtmlButton cartIcon;

    public NavigationBar(){
        PageFactory.initElements(
                new CustomFieldDecorator(
                        BaseUITest.getLocatorFactory()),
                this);
    }

    public void logout(){
        burgerMenu.click();
        logoutButton.click();
    }

    public void clickCart(){
        cartIcon.click();
    }

    public boolean isCartDisplayed(){
        return cartIcon.isDisplayed();
    }
}