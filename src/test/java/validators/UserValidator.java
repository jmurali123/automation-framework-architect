package validators;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UserValidator {

    public static void validateUser(Response response){
        // single user validation
        response.then().body(matchesJsonSchemaInClasspath("user-schema.json"));

        // Soft assertions — field level!
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(
                response.jsonPath().getString("name"), "name is null!");
        softAssert.assertNotNull(
                response.jsonPath().getString("username"), "username is null!");
        softAssert.assertNotNull(
                response.jsonPath().getString("email"), "email is null!");
        softAssert.assertTrue(
                response.jsonPath().getString("email").contains("@"),
                "Invalid email format!");

        softAssert.assertAll();

        //
//        Assert.assertNotNull(response.jsonPath().getString("name"));
//        Assert.assertNotNull(response.jsonPath().getString("username"));
//        Assert.assertNotNull(response.jsonPath().getString("email"));
//        Assert.assertTrue(response.jsonPath().getString("email").contains("@"));
    }

    public static void validateUsersList(Response response){

        response.then().body(hasSize(10));
        response.then().body("name",hasItem("Leanne Graham"));
        Assert.assertEquals(response.jsonPath().getString("address[0].city"), "Gwenborough");

    }
}