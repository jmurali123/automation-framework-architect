package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ArrayValidationTest extends BaseTest {
    @Test
    public void getAllUsersTest() {

        given().spec(reqSpec)
                .when()
                .get("/users")
                .then()
                .spec(resSpec)
                .body("$", hasSize(10))
                .body("name", hasItem("Ervin Howell"))
                .body("name", hasItem("Leanne Graham"))
                .body("$",hasSize(greaterThan(5)))
                .body("email",hasItem("Sincere@april.biz"))
                .body("id",hasItem(1));

    }

    @Test
    public void getSingleUserTest(){
        given().spec(reqSpec).when().get("/users/1").then()
                .spec(resSpec)
                .body("id",equalTo(1))
                .body("name",notNullValue())
                .body("address.zipcode",equalTo("92998-3874"));
    }

    @Test
    public void getPostTest(){
        given().spec(reqSpec).when()
                .get("/posts/1")
                .then()
                .spec(resSpec)
                .body("userId",equalTo(1))
                .body("id",equalTo(1))
                .body("title",containsString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("body",containsString("quia et suscipit\nsuscipit recusandae consequuntur expedita"));

    }

    @Test
    public void validateSpecificUsersTest(){
        given().spec(reqSpec).when()
                .get("/users")
                .then()
                .spec(resSpec)
                .body("[0].email",equalTo("Sincere@april.biz"))
                .body("[1].name",equalTo("Ervin Howell"))
                .body("[2].username",equalTo("Samantha"));

    }

    @Test
    public void validateNestedFieldsTest(){
        given().spec(reqSpec).when().
                get("/users")
                .then()
                .spec(resSpec)
                .body("[0].company.name",equalTo("Romaguera-Crona"))
                .body("[1].address.city",equalTo("Wisokyburgh"))
                .body("[2].address.geo.lat",equalTo("-68.6102"));
    }

    @Test
    public void validateAllUsersTest(){
        given().spec(reqSpec).when()
                .get("/users")
                .then()
                .spec(resSpec)
                .body("email",everyItem(containsString("@")))
                .body("[4].company.catchPhrase",notNullValue());
    }

    @Test
    public void validateAllNamesTest(){
        given().spec(reqSpec).when().get("/users")
                .then()
                .spec(resSpec)
                .body("name",everyItem(notNullValue()))
                .body("name",everyItem(instanceOf(String.class)))
                .body("name",hasItem(containsString("Graham")));
    }
}
