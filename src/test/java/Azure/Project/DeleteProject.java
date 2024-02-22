package Azure.Project;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteProject {

	static String projectName;
	static String deleteProjectId;

	@Test(dependsOnMethods = { "Azure.Project.CreateProject.createProject" })
	public void getProjectIdFromName() throws IOException {
		projectName = new CreateProject().createdprojectName;

		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().param("api-version", "6.0")
				.header("Authorization", getDevopsDetails.getAccessToken())
				.when()
				.get(getDevopsDetails.getOrgName() + "/_apis/projects")
				.then().extract().response();

		ArrayList<LinkedHashMap> list = new ArrayList<>();

//		System.out.println(response.asPrettyString());
		for (LinkedHashMap name : list) {
			if (!name.get("name").equals(projectName)) {
				deleteProjectId = (String) name.get("id");
			}
		}

	}

	@Test(dependsOnMethods = { "Azure.Project.DeleteProject.getProjectIdFromName" }, priority = 3)
	public static void deleteProject() throws IOException, InterruptedException {

		System.out.println("delete is ============ : " + deleteProjectId);
		if(deleteProjectId!=null) {
			RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
			Response response = given().queryParam("api-version", "7.1-preview.4")
					.header("Authorization", getDevopsDetails.getAccessToken())
					.header("Content-Type", "application/json")
					.when().delete(getDevopsDetails.getOrgName() + "/_apis/projects/" + deleteProjectId)
					.then().extract()
					.response();

			JsonPath json = response.jsonPath();
//			System.out.println(response.asString());
			Assert.assertEquals(response.statusCode(), 202);
		}
	}
}
