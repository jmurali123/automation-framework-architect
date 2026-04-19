package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class CrudFlowTest extends BaseTest {
    @Test(groups={"api", "sanity", "regression"})
    public void completeCrudFlowTest(){
        String requestBody = "{\n" +
                "\"title\": \"John Doe\",\n" +
                "\"body\": \"Learning Rest Assured\",\n" +
                "\"userId\": 1\n" +
                "}";
        int id= given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                 .getInt("id");

        System.out.println(id);
        given().spec(reqSpec).when().get("/posts/"+id)
                .then()
                .statusCode(404);
                //.body("title",containsString("John Doe"))
               // .body("body", containsString("Learning"));
        String requestBodyput="{\n" +
                "\"title\": \"Jenny Doe\",\n" +
                "\"body\": \"Learning Rest Assured\",\n" +
                "\"userId\": 1\n" +
                "}";
        given().spec(reqSpec)
                .body(requestBodyput)
                .when()
                .put("/posts/1")
                .then()
                .spec(resSpec);

        given().spec(reqSpec).when().delete("/posts/1")
                .then()
                .spec(resSpec);

    }
}
