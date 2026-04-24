package tests.ui;

import base.BaseUITest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverManager;

public class LoginTest extends BaseUITest {

    @Test(groups = {"smoke", "ui", "regression"})
    public void loginTest(){

        // BaseUITest already navigated to login page!
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
                DriverManager.getDriver()
                        .getTitle().contains("Swag Labs"),
                "Login failed!");
    }

    @Test(groups = {"ui", "regression"})
    public void invalidLoginTest(){

        // BaseUITest already navigated to login page!
        LoginPage loginPage = new LoginPage();
        loginPage.login("wrong_user", "wrong_pass");

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message not displayed!");
    }
}