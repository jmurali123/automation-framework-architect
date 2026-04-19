package tests;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest extends BaseTest {
    RequestSpecification authreqSpec;
    String token;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        authreqSpec=new RequestSpecBuilder()
                .setBaseUri("https://postman-echo.com")
                .build();
        token="mySecretToken123";

    }

    @Test
    public void basicAuthTest(){
        given().spec(authreqSpec)
                .auth().preemptive().basic("postman","password").log().all()
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();

    }

    @Test(groups={"smoke", "api", "regression"})
    public void bearerTokenTest(){
        given().spec(authreqSpec)
                .auth().oauth2(token)
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();
    }
}
