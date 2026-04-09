import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class CrudFlowTest {
    @Test
    public void completeCrudFlowTest(){
        String requestBody = "{\n" +
                "\"title\": \"John Doe\",\n" +
                "\"body\": \"Learning Rest Assured\",\n" +
                "\"userId\": 1\n" +
                "}";
        int id= given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                 .getInt("id");

        System.out.println(id);
        given().when().get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body("title",containsString("John Doe"))
                .body("body", containsString("Learning"));
        String requestBodyput="{\n" +
                "\"title\": \"Jenny Doe\",\n" +
                "\"body\": \"Learning Rest Assured\",\n" +
                "\"userId\": 1\n" +
                "}";
        given().contentType(ContentType.JSON)
                .body(requestBodyput)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);

        given().when().delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);

    }
}
