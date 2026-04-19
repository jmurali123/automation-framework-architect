package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequestTest extends BaseTest {
    @Test( groups={"api", "sanity", "regression"})
    public void updatePostTest() {

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated Title\",\n" +
                "  \"body\": \"Updated Body\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then().spec(resSpec)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Title"))
                .body("body", equalTo("Updated Body"));
    }

    @Test( groups={"api", "sanity", "regression"})
    public void updateUserTest() {
        String requestbody="{\n" +
                "  \"id\": 2,\n" +
                "  \"name\": \"Updated Name\",\n" +
                "  \"email\": \"updated@gmail.com\",\n" +
                "  \"username\": \"updateduser\"\n" +
                "}";
        given().spec(reqSpec)
                .body(requestbody)
                .when()
                .put("/posts/2")
                .then()
                .spec(resSpec)
                .body("name",equalTo("Updated Name"))
                .body("email", equalTo("updated@gmail.com"));
    }

}
