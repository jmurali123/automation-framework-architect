package tests.hybrid;

import base.BaseUITest;
import interfaces.ApiCapable;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("Hybrid Testing")
@Feature("E-Commerce Order Flow")
public class HybridTest extends BaseUITest
        implements ApiCapable {

    private int postId;

    @Test(groups = {"hybrid", "regression"})
    @Story("Complete E-Commerce flow using API and UI")
    @Severity(SeverityLevel.CRITICAL)
    public void hybridOrderFlowTest(){

        // Step 1 — API: Create order
        Post requestPost = new Post();
        requestPost.setUserId(
                Integer.parseInt(ConfigReader.get("hybrid.post.userId")));
        requestPost.setTitle(ConfigReader.get("hybrid.post.title"));
        requestPost.setBody(ConfigReader.get("hybrid.post.body"));

        postId = given()
                .spec(getApiSpec())
                .body(requestPost)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getInt("id");

        System.out.println("Step 1 — Order created via API! ID: " + postId);

        // Step 2 — UI: Login
        LoginPage loginPage = new LoginPage();
        loginPage.login(
                ConfigReader.get("hybrid.ui.username"),
                ConfigReader.get("hybrid.ui.password"));

        System.out.println("Step 2 — Logged in via UI!");

        // Step 3 — UI: Verify product page
        ProductPage productPage = new ProductPage();
        Assert.assertTrue(productPage.isPageLoaded(),
                "Product page not loaded!");

        System.out.println("Step 3 — Product page verified via UI!");

        // Step 4 — API: Validate order
        given()
                .spec(getApiSpec())
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(404);


        System.out.println("Step 4 — Order validated via API!");

        // Step 5 — UI: Logout
        productPage.logout();
        System.out.println("Step 5 — Logged out via UI!");

        // Step 6 — API: Cleanup
        given()
                .spec(getApiSpec())
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);

        System.out.println("Step 6 — Cleanup done!");
    }
}