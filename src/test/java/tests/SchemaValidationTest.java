package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest {
    @Test
    public void validateUserSchemaTest(){
        given().when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));

    }

    @Test
    public void validateUsersArraySchemaTest(){
        given().when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("users-schema.json"));
    }

    @Test
    public void SchemaValidationTest(){
        given().when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("posts-schema.json"));
    }
}
