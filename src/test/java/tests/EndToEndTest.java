package tests;

import base.BaseTest;
import io.qameta.allure.*;
import models.Post;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("End to End")
@Feature("Post CRUD Flow")

public class EndToEndTest extends BaseTest {
    @Test(groups={"regression","api"})
    @Story("Complete Post LifeCycle")
    @Severity(SeverityLevel.CRITICAL)
    public void postEndToEndTest(){

        // Step 1 — CREATE
        Post requestPost = new Post();
        requestPost.setUserId(1);
        requestPost.setTitle("E2E Test Post");
        requestPost.setBody("E2E Test Body");

        int postId = given().spec(reqSpec)
                .body(requestPost)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getInt("id");

        System.out.println("Created Post ID: " + postId);

        // Step 2 — GET (404 because JSONPlaceholder doesn't persist)
        given().spec(reqSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(404)
                .log().all();

        // Step 3 — UPDATE existing post id 1
        Post updatedPost = new Post();
        updatedPost.setUserId(1);
        updatedPost.setId(1);           // ← set id!
        updatedPost.setTitle("Updated E2E Post");
        updatedPost.setBody("Updated E2E Body");

        given().spec(reqSpec)
                .body(updatedPost)
                .when()
                .put("/posts/1")        // ← hardcoded 1!
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated E2E Post"))
                .log().all();

        // Step 4 — DELETE existing post id 1
        given().spec(reqSpec)
                .when()
                .delete("/posts/1")     // ← hardcoded 1!
                .then()
                .statusCode(200)
                .log().all();

        System.out.println("Post deleted successfully!");
    }

}
