package Azure.Backlog;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;




public class GetBacklog {

	String Project="Automate";
	String team="Automate Team";
	
	@Test
	public void getBacklog() throws IOException {
		
		RestAssured.baseURI=getDevopsDetails.getEnvVarible("BaseURI");
		
		Response response=given()
				.header("Authorization", getDevopsDetails.getAccessToken())
				.queryParam("api-version", "7.1-preview.1")
				.when()
				.get(getDevopsDetails.getOrgName()+"/"+Project+"/"+team+"/_apis/work/backlogs/"+"Microsoft.RequirementCategory"+"/workItems")
				.then().extract().response();
		
//		System.out.println(response.asPrettyString());
		
		JsonPath json=response.jsonPath();

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(json.get("workItems[0].target.id").getClass(), Integer.class);
	}

}
