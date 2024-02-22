package Azure.Project;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateProject {

	static String createdprojectName;

	@Test(priority = 1)
	public void createProject() throws IOException {
//		System.out.println("creating project");
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		String projectName = getDevopsDetails.generateRandomString(10);
		Response response = given().queryParam("api-version", "7.1-preview.4")
				.header("Authorization", getDevopsDetails.getAccessToken())
				.header("Content-Type", "application/json")
				.body("{\n" + "  \"name\": \"" + projectName + "\",\n"
						+ "  \"description\": \"Frabrikam travel app for Windows Phone\",\n" + "  \"capabilities\": {\n"
						+ "    \"versioncontrol\": {\n" + "      \"sourceControlType\": \"Git\"\n" + "    },\n"
						+ "    \"processTemplate\": {\n"
						+ "      \"templateTypeId\": \"6b724908-ef14-45cf-84f8-768b5384da45\"\n" + "    }\n" + "  }\n"
						+ "}")
				.when().post(getDevopsDetails.getOrgName() + "/_apis/projects")
				.then().extract().response();

		JsonPath json = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 202);
		createdprojectName = projectName;
//		System.out.println(response.asString());
		System.out.println("Created project id is : -----" + createdprojectName);
	}

}

//Testiiiiting
//307acce7-6578-4059-ba56-415d1e2ea385
//Tester
//63eb51ca-47de-4626-aff1-f27da090f9cf
//Automtyuate
//f2980448-c9d7-4f47-9397-adb4493cb40d
//Test Project
//0797d4db-f102-4ea2-9265-707b94e16fd0
//Creating New Project
//61cd130b-113c-4ad5-bb72-beea6177a17f
//list of ids are :[307acce7-6578-4059-ba56-415d1e2ea385, 63eb51ca-47de-4626-aff1-f27da090f9cf, f2980448-c9d7-4f47-9397-adb4493cb40d, 0797d4db-f102-4ea2-9265-707b94e16fd0, 61cd130b-113c-4ad5-bb72-beea6177a17f]
//4f844a9f-20de-40db-8565-3b8f427ff1a7
