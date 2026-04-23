package tests.ui;

import base.BaseUITest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseUITest {
    @Test(groups={"smoke","ui","regression"})
    public void loginTest(){
        // Step 1 — Open URL
        driver.get("https://www.saucedemo.com");

        // Step 2 — Enter username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // Step 3 — Enter password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Step 4 — Click login button
        driver.findElement(By.id("login-button")).click();
        // Step 5 — Verify login successful
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Swag Labs"));

    }
}
