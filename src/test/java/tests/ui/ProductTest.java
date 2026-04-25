package tests.ui;

import base.BaseUITest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseUITest {

    @Test(groups = {"smoke", "ui", "regression"})
    public void verifyProductPageLoadedTest(){

        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage();

        // Hard Assert — critical check!
        Assert.assertTrue(productPage.isPageLoaded(),
                "Product page not loaded!");

        // Soft Assert — multiple validations!
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productPage.getPageTitle(),
                "Products", "Title mismatch!");
        softAssert.assertTrue(productPage.isCartDisplayed(),
                "Cart icon not displayed!");
        softAssert.assertAll();
    }

    @Test(groups = {"ui", "regression"})
    public void verifyLogoutTest(){

        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage();
        productPage.logout();

        // Verify back on login page — login button visible!
        LoginPage loginPageAfterLogout = new LoginPage();
        Assert.assertTrue(
                loginPageAfterLogout.isLoginButtonDisplayed(),
                "Should be back on login page!");
    }
}