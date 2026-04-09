import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class pathParamTest {
    RequestSpecification requestSpec;
    @BeforeClass
    public void setup()
    {
         requestSpec= new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void pathParam(){

        Map<String,Object> pathParams=new HashMap<String,Object>();
        pathParams.put("postId",1);

        given().spec(requestSpec).pathParams(pathParams).when().get("/posts/{postId}/comments")
                .then().statusCode(200)
                .log().all();
    }
}
