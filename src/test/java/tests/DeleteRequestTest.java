package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest extends BaseTest {
    @Test
    public void deletePostTest() {

        given().spec(reqSpec)
                .when()
                .delete("/posts/1")
                .then()
                .spec(resSpec);
    }

    @Test
    public void deleteAndVerifyTest(){
        given().spec(reqSpec).when()
                .delete("/posts/5")
                .then()
                .spec(resSpec);
    }
}
