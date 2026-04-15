package base;

import filters.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {

    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;

    @BeforeClass
    public void setUp(){
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("jsonplaceholder.base.uri"))
                .setContentType(ContentType.JSON)
                .addFilter(new LoggingFilter())
                .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}