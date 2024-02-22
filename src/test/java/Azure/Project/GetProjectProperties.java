package Azure.Project;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetProjectProperties {

	String deleteProjectId;
	
	@Test(dependsOnMethods = { "Azure.Project.GetListOfProject.getProjectList" })
	public void GetProjectProperties() throws IOException {
		
		deleteProjectId = new GetListOfProject().getProjectId;
		
		RestAssured.baseURI=getDevopsDetails.getEnvVarible("BaseURI");
		
		Response response=given()
				.param("organization", getDevopsDetails.getOrgName())
				.queryParam("api-version", "7.1-preview.1")
				.header("Authorization", getDevopsDetails.getAccessToken())
				.when()
				.get(getDevopsDetails.getOrgName() +"/_apis/projects/"+deleteProjectId+"/properties")
				.then().extract().response();
		
		JsonPath json=response.jsonPath();
//		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
