package Azure.Profiles;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetProfile {

	@Test
	public void getProfile() {
		
		RestAssured.baseURI="https://app.vssps.visualstudio.com/";
		
		Response response=given().param("id", "me")
				.queryParam("api-version", "7.1-preview.3")
				.header("Authorization",getDevopsDetails.getAccessToken())
				.when()
				.get("_apis/profile/profiles/")
				.then().extract().response();
		
		System.out.println(response.asPrettyString());
	}
}
