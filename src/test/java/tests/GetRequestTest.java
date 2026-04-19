package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTest extends BaseTest {
    @Test(groups={"smoke","regression","api"})
    public void getUserTest(){
        given().spec(reqSpec)
                .when()
                .get("/users/1")
                .then()
                .spec(resSpec)
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("address.city", equalTo("Gwenborough"))
                .body("username", equalTo("Bret"))
                .body("address.street", equalTo("Kulas Light"))
                .body("name",containsString("Graham"))
                .body("email", notNullValue())
                .body("id",greaterThan(Integer.valueOf(0)))
                .body("phone", notNullValue())
                .body("website", containsString("hildegard"));


    }


}
