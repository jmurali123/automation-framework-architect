import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest {
    @Test
    public void deletePostTest() {

        given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteAndVerifyTest(){
        given().when()
                .delete("https://jsonplaceholder.typicode.com/posts/5")
                .then()
                .statusCode(200);
    }
}
