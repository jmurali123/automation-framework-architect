import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class MultipartAndXMLTest {
   RequestSpecification requestSpecification;
    @BeforeClass
    public void setUp(){
         requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.XML)
                .setBaseUri("https://postman-echo.com")
                .build();
    }
    @Test
    public void xmlRequestTest() {
        String xmlBody = "<user>\n" +
                "<name>murali</name>\n" +
                "<age> 35</age>\n" +
                "</user>";
        given().spec(requestSpecification)
                .body(xmlBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("headers.content-type",containsString("application/xml"))
                .log().all();
    }

    @Test
    public void multipartFileUploadTest(){
        File file = new File("src/test/resources/user.xml");
        given().spec(requestSpecification)
                .contentType(ContentType.MULTIPART)
                .multiPart("file",file)
                .when().post("/post")
                .then().statusCode(200)
                .log().all();
    }


    }

