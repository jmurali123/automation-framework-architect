package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("User Management")
@Feature("Get User")

public class GetRequestTest extends BaseTest {
    @Test(groups={"smoke","regression","api"})
    @Story("Get single user by ID")
    @Description("Verify GET /users/1 returns correct user details")
    @Severity(SeverityLevel.CRITICAL)
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
