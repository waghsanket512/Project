package Azure.Project;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteAllProject {

	static ArrayList<String> projectIdList;

	@Test(dependsOnMethods = { "Azure.Project.GetListOfProject.getProjectList" })
	public static void deleteAllProject() throws IOException {
		projectIdList = new GetListOfProject().projectIdList;
//		System.out.println("delete all ============" + projectIdList);
		if (!projectIdList.isEmpty()) {
			for (String id : projectIdList) {
				RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
				Response response = given().queryParam("api-version", "7.1-preview.4")
						.header("Authorization", getDevopsDetails.getAccessToken())
						.header("Content-Type", "application/json")
						.when()
						.delete(getDevopsDetails.getOrgName() + "/_apis/projects/" + id)
						.then().extract().response();

				JsonPath json = response.jsonPath();
				System.out.println(response.asString());
				Assert.assertEquals(response.statusCode(), 202);

			}
		}
	}
}
