import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest {
    RequestSpecification reqSpec;
    String token;

    @BeforeClass
    public void setUp(){
        reqSpec=new RequestSpecBuilder()
                .setBaseUri("https://postman-echo.com")
                .build();
        token="mySecretToken123";

    }

    @Test
    public void basicAuthTest(){
        given().spec(reqSpec)
                .auth().preemptive().basic("postman","password").log().all()
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();

    }

    @Test
    public void bearerTokenTest(){
        given().spec(reqSpec)
                .auth().oauth2(token)
                .when()
                .get("/headers")
                .then()
                .statusCode(200).log().all();
    }
}
