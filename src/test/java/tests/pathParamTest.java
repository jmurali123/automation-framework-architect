package tests;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class pathParamTest extends BaseTest {

    @Test(groups={"api", "regression"})
    public void pathParam(){

        Map<String,Object> pathParams=new HashMap<String,Object>();
        pathParams.put("postId",1);

        given().spec(reqSpec).pathParams(pathParams).when().get("/posts/{postId}/comments")
                .then().spec(resSpec)
                .log().all();
    }
}
