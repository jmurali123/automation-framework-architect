package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import models.Post;
import models.User;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PojoTest extends BaseTest {

    @Test
    public void createPostWithPojoTest() {

        // Create request body using POJO
        Post post = new Post();
        post.setUserId(1);
        post.setTitle("My POJO Post");
        post.setBody("This is created using POJO!");

        given()
                .spec(reqSpec)
                .body(post)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("My POJO Post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    public void getPostAsPojoTest(){
        Post post = given()
                .spec(reqSpec)
                .when()
                .get("/posts/1")
                .then()
                .spec(resSpec)
                .extract()
                .as(Post.class);

        System.out.println("Title: " + post.getTitle());
        System.out.println("UserId: " + post.getUserId());
        System.out.println("Body: " + post.getBody());
    }

    @Test
    public void getUserAsPojoTest(){
        User user =  given()
                .spec(reqSpec)
                .when()
                .get("/users/1")
                .then().spec(resSpec).extract().as(User.class);
        System.out.println("user name "+user.getName());
        System.out.println("user email"+user.getEmail());
    }
}