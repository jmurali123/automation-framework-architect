package pages;

import annotations.FindByLocator;
import decorators.CustomFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pages.components.HtmlButton;
import pages.components.HtmlInput;
import pages.components.HtmlLabel;
import utils.DriverManager;

public class LoginPage {

    @FindByLocator("login.username")
    private HtmlInput username;

    @FindByLocator("login.password")
    private HtmlInput password;

    @FindByLocator("login.button")
    private HtmlButton loginButton;

    @FindByLocator("login.error")
    private HtmlLabel errorMessage;

    public LoginPage(){
        PageFactory.initElements(
                new CustomFieldDecorator(
                        new AjaxElementLocatorFactory(
                                DriverManager.getDriver(), 10)),
                this);
    }

    public void enterUsername(String user){
        username.enterText(user);
    }

    public void enterPassword(String pass){
        password.enterText(pass);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public void login(String user, String pass){
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public boolean isErrorDisplayed(){
        return errorMessage.isDisplayed();
    }
}