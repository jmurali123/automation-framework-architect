import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FilterTest {
    RequestSpecification reqSpec;
    @BeforeClass
    public void setUp(){
        reqSpec= new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new LoggingFilter())
                .build();
    }
    @Test
    public void testLoggingFilter(){
        given().spec(reqSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);
    }
}
