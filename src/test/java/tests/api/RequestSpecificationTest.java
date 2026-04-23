package tests.api;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Post;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationTest extends BaseTest {


    @Test
    public void getPostWithSpecTest(){
        given()
                .spec(reqSpec)
                .when()
                .get("/posts/1")
                .then()
                .log().ifValidationFails()
                .spec(resSpec)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }
    @Test
    public void getPostsWithSpecTest(){
        given()
                .spec(reqSpec)
                .when()
                .get("/posts")
                .then()
                .log().ifValidationFails()
                .spec(resSpec)
                .body("$",hasSize(100))
                .body("userId", everyItem(notNullValue()));
    }

    @Test
    public void getUserWithSpecTest()
    {
        given().spec(reqSpec)
                .when()
                .get("/users/1")
                .then()
                .log().ifValidationFails()
                .spec(resSpec)
                .body("id",equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    public void createPostWithSpecTest(){
        Post post = new Post();
        post.setUserId(1);
        post.setBody("Test Body");
        post.setTitle("Test Post");
        given().spec(reqSpec)
                .body(post)
                .when()
                .post("/posts")
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Test Post"));
    }

    @Test
    public void deletePostWithSpecTest(){
        given().spec(reqSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void loggingTest(){
        given()
                .spec(reqSpec)
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().ifValidationFails()
                .spec(resSpec)
                .body("id",equalTo(1));
    }
}
