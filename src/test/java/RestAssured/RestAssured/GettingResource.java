package RestAssured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GettingResource {
  @Test
  public void getResource() {
	  RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
	  
	  Response response= given().when().get("posts/1").then().extract().response();
	  
	  System.out.println("Response found"+response.asString());
	  Assert.assertEquals(response.getStatusCode(), 200);
	  Assert.assertEquals(response.jsonPath().getInt("id"), 1);
	  System.out.println(response.jsonPath());
  }
}
