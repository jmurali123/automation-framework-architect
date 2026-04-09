import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestTest {
    @Test
    public void createPostTest(){
        String requestBody = "{\n" +
                "  \"title\": \"My First Post\",\n" +
                "  \"body\": \"This is the body of my post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("My First Post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    public void createUserTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
        given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("John Doe"))
                .body("email",equalTo("john@gmail.com"))
                .body("id",notNullValue());

    }

    @Test
    public void createPostAndValidateIdTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
       int id= given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getInt("id");

        System.out.println("Created User ID: " + id);
    }

    @Test
    public void createCustomPostTest(){
        String requestBody="{\n" +
                "  \"title\": \"Murali\",\n" +
                "  \"body\": \"javvadi.murali@gmail.com\",\n" +
                "  \"userId\": 5\n" +
                "}";

        int id=given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getInt("id");

        System.out.println(id);

    }
}
