import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestTest {
    @Test
    public void getUserTest(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
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
