package base;

import filters.LoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

public class BaseTest {

    protected static RequestSpecification reqSpec;
    protected static ResponseSpecification resSpec;


    static{
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