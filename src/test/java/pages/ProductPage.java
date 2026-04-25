package pages;

import annotations.FindByLocator;
import base.BaseUITest;
import decorators.CustomFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.HtmlButton;
import pages.components.HtmlLabel;
import utils.ConfigReader;
import utils.DriverManager;
import utils.LocatorReader;

import java.time.Duration;

public class ProductPage {

    @FindByLocator("product.title")
    private HtmlLabel pageTitle;

    @FindByLocator("product.cart.icon")
    private HtmlButton cartIcon;

    @FindByLocator("product.burger.menu")
    private HtmlButton burgerMenu;

    @FindByLocator("product.logout")
    private HtmlButton logoutButton;

    public ProductPage(){
        PageFactory.initElements(
                new CustomFieldDecorator(BaseUITest.getLocatorFactory()),
                this);
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public void clickCart(){
        cartIcon.click();
    }

    public void logout(){
        burgerMenu.click();

        // Wait until logout button is clickable!
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(
                        Long.parseLong(ConfigReader.get("explicit.wait")))); // ← from config!

        wait.until(ExpectedConditions.elementToBeClickable(
                LocatorReader.get("product.logout")));

        logoutButton.click();
    }

    public boolean isPageLoaded(){
        return pageTitle.isDisplayed();
    }
    public boolean isCartDisplayed(){
        return cartIcon.isDisplayed();
    }
}