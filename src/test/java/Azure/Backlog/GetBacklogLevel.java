package Azure.Backlog;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetBacklogLevel {

	String Project="Automate";
	String team="Automate Team";
	
	@Test
	public void getBacklog() throws IOException {
		
		RestAssured.baseURI=getDevopsDetails.getEnvVarible("BaseURI");
		
		Response response=given()
				.header("Authorization", getDevopsDetails.getAccessToken())
//				.param("id", "PRODUCT BACKLOG ITEM 1")
//				.param("organization", getDevopsDetails.getOrgName())
//				.param("project", Project)
//				.param("team",team)
				.queryParam("api-version", "7.1-preview.1")
				.when()
				.get(getDevopsDetails.getOrgName()+"/"+Project+"/"+team+"/_apis/work/backlogs/"+"Microsoft.RequirementCategory")
				.then().extract().response();
		
//		System.out.println(response.asPrettyString());
		
		JsonPath json=response.jsonPath();

//		ArrayList<String> listLevel=new ArrayList<>();
		
//		listLevel=json.get("addPanelFields");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(json.getString("name"), "Backlog items");
		Assert.assertEquals(json.getString("addPanelFields[0].name"),"Title" );
	}
}
