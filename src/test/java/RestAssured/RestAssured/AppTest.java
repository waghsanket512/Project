package RestAssured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.*;
import static io.restassured.RestAssured.given;
/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
        Response response = given()
                .contentType(ContentType.JSON)
                .param("postId", "2")
                .when()
                .get("/comments")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));


	}
}
