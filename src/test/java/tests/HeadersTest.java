package tests;

import constants.Constants;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HeadersTest {

    @Test
    public void customHeaderTest() {

        given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(Constants.JSONPLACEHOLDER_BASE_URI+"/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(1));
    }

    @Test
    public void queryParamTest(){
        given()
                .queryParam("userId",1)
                .when()
                .get(Constants.JSONPLACEHOLDER_BASE_URI+"/posts")
                .then()
                .statusCode(200)
                .body("$",hasSize(10))
                .body("userId",everyItem(equalTo(1)));
    }

    @Test
    public void multipleQueryParamTest(){
        given()
                .queryParam("postId",1)
                .when()
                .get(Constants.JSONPLACEHOLDER_BASE_URI+"/comments")
                .then()
                .statusCode(200)
                .body("$",hasSize(5))
                .body("postId",everyItem(equalTo(1)));
    }

    @Test
    public void basicAuthTest() {
        given()
                .auth()
                .basic("admin", "password123")
                .when()
                .get("https://httpbin.org/basic-auth/admin/password123")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .body("user", equalTo("admin"));
    }

    @Test
    public void bearerTokenTest(){
        given().header("Authorization","Bearer mytoken123")
                .when()
                .get("https://httpbin.org/bearer")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .body("token",equalTo("mytoken123"));
    }
}