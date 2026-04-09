import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequestTest {
    @Test
    public void updatePostTest() {

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated Title\",\n" +
                "  \"body\": \"Updated Body\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Title"))
                .body("body", equalTo("Updated Body"));
    }

    @Test
    public void updateUserTest(){
        String requestbody="{\n" +
                "  \"id\": 2,\n" +
                "  \"name\": \"Updated Name\",\n" +
                "  \"email\": \"updated@gmail.com\",\n" +
                "  \"username\": \"updateduser\"\n" +
                "}";
        given().contentType(ContentType.JSON)
                .body(requestbody)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/2")
                .then()
                .statusCode(200)
                .body("name",equalTo("Updated Name"))
                .body("email", equalTo("updated@gmail.com"));
    }

}
