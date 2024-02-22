package Azure.Project;

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

public class GetProject {
	String projectId;
	@Test(dependsOnMethods = { "Azure.Project.GetListOfProject.getProjectList" })
//	@Test(dependsOnMethods = { "Azure.Project.CreateProject.createProject" })
	public void getProject() throws IOException {
		projectId=new GetListOfProject().getProjectId;
		System.out.println(projectId);
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().param("api-version", "6.0")
				.header("Authorization", getDevopsDetails.getAccessToken())
				.when()
				.get(getDevopsDetails.getOrgName() + "/_apis/projects/"+projectId)
				.then().extract().response();

		JsonPath json = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 200);
//		System.out.println(response.asString());
	   Assert.assertEquals(json.getString("name"),"Automate");
	   
		

	}

}
