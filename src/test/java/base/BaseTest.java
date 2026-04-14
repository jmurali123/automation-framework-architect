package base;

import constants.Constants;
import filters.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;

    @BeforeClass
    public void setUp(){
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.JSONPLACEHOLDER_BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new LoggingFilter())
                .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}