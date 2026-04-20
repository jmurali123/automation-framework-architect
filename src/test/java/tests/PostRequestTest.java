package tests;

import base.BaseTest;
import dataproviders.DataProviders;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestTest extends BaseTest {
    @Test(groups={"api", "sanity", "regression"})
    public void createPostTest(){
        String requestBody = "{\n" +
                "  \"title\": \"My First Post\",\n" +
                "  \"body\": \"This is the body of my post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("My First Post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test(groups={"api", "sanity", "regression"})
    public void createUserTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("John Doe"))
                .body("email",equalTo("john@gmail.com"))
                .body("id",notNullValue());

    }

    @Test(groups={"api", "sanity", "regression"})
    public void createPostAndValidateIdTest(){
        String requestBody="{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"email\": \"john@gmail.com\",\n" +
                "  \"username\": \"johndoe\"\n" +
                "}";
       int id= given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().jsonPath().getInt("id");

        System.out.println("Created User ID: " + id);
    }

    @Test(groups={"api", "sanity", "regression"})
    public void createCustomPostTest(){
        String requestBody="{\n" +
                "  \"title\": \"Murali\",\n" +
                "  \"body\": \"javvadi.murali@gmail.com\",\n" +
                "  \"userId\": 5\n" +
                "}";

        int id=given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getInt("id");

        System.out.println(id);

    }
    @Test(dataProvider = "postData", dataProviderClass = DataProviders.class,
            groups = {"api", "regression"})
    public void createPostDataDrivenTest(String title, String body, int userId){

        String requestBody = "{\n" +
                "\"title\": \"" + title + "\",\n" +
                "\"body\": \"" + body + "\",\n" +
                "\"userId\": " + userId + "\n" +
                "}";

        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("userId", equalTo(userId))
                .log().all();
    }

    @Test(dataProvider = "postJsonData", dataProviderClass = DataProviders.class, groups = {"api","regression"})
    public void createPostJsonDriverTest(Map<String,Object> testData){
        String requestBody = "{\n" +
                "\"title\": \"" + testData.get("title") + "\",\n" +
                "\"body\": \"" + testData.get("body") + "\",\n" +
                "\"userId\": " + testData.get("userId") + "\n" +
                "}";
        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title",equalTo(testData.get("title").toString()))
                .log().all();
    }


    @Test(dataProvider = "excelPostData", dataProviderClass = DataProviders.class, groups={"api","regression"})
    public void createPostExcelDrivenTest(String title, String body, String userId ){
        String requestBody = "{\n" +
                "\"title\": \"" + title + "\",\n" +
                "\"body\": \"" + body + "\",\n" +
                "\"userId\": " + userId + "\n" +
                "}";

        given().spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .log().all();
    }
}
