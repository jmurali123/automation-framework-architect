package tests.api;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Feature("Authentication")
@Epic("Security")
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

    @Test(groups = {"smoke","api","regression"})
    @Story("Basic Auth validation")
    @Severity(SeverityLevel.CRITICAL)
    public void basicAuthTest(){
        given().spec(authreqSpec)
                .auth().preemptive().basic("postman","password").log().all()
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();

    }

    @Test(groups={"smoke", "api", "regression"})
    @Story("Bearer Token test")
    @Severity(SeverityLevel.CRITICAL)
    public void bearerTokenTest(){
        given().spec(authreqSpec)
                .auth().oauth2(token)
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();
    }
}
