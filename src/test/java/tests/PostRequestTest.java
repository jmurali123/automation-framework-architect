package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestTest extends BaseTest {
    @Test(groups={"api", "sanity", "regression"})
    public void createPostTest(){
        String requestBody = "{\n" +
                "  \"title\": \"My First Post\",\n" +
                "  \"body\": \"This is the body of my post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("My First Post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test(groups={"api", "sanity", "regression"})
    public void createUserTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("John Doe"))
                .body("email",equalTo("john@gmail.com"))
                .body("id",notNullValue());

    }

    @Test(groups={"api", "sanity", "regression"})
    public void createPostAndValidateIdTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
       int id= given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getInt("id");

        System.out.println("Created User ID: " + id);
    }

    @Test(groups={"api", "sanity", "regression"})
    public void createCustomPostTest(){
        String requestBody="{\n" +
                "  \"title\": \"Murali\",\n" +
                "  \"body\": \"javvadi.murali@gmail.com\",\n" +
                "  \"userId\": 5\n" +
                "}";

        int id=given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getInt("id");

        System.out.println(id);

    }
}
