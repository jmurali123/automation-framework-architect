package tests;

import base.BaseTest;
import filters.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FilterTest extends BaseTest {

    @Test
    public void testLoggingFilter(){
        given().spec(reqSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }
}
