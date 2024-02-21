package Azure.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.getDevopsDetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class GetListOfProject {

	Properties prop = new Properties();
	static String createProjectId;

	@BeforeClass
	public void getData() throws IOException {

		FileInputStream stream = new FileInputStream(
				"C:\\Users\\Admin\\eclipse-workspace\\Rest Assured\\RestAssured\\src\\test\\java\\env_prop\\azure.poperties");
		prop.load(stream);
//	prop.get("BaseURI");

	}

	public RequestSpecification res() {
		RequestSpecBuilder resp=new RequestSpecBuilder();
		resp.addHeader("Authorization", getDevopsDetails.getAccessToken());
		RequestSpecification request=resp.build();
		return request;
	
	}
	@Test
	public void getProjectList() throws IOException {
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().param("api-version", "6.0")
//				.header("Authorization", getDevopsDetails.getAccessToken())
				.spec(res())
				.when()
				.get(getDevopsDetails.getOrgName() + "/_apis/projects").then().extract().response();

		JsonPath json = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotEquals(json.getInt("count"), 0);
		System.out.println(response.asString());

	}

	@Test(enabled = false)
	public void createProject() throws IOException {
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().queryParam("api-version", "7.1-preview.4")
				.header("Authorization", getDevopsDetails.getAccessToken()).header("Content-Type", "application/json")
				.body("{\n" + "  \"name\": \"Testiiiiting\",\n"
						+ "  \"description\": \"Frabrikam travel app for Windows Phone\",\n" + "  \"capabilities\": {\n"
						+ "    \"versioncontrol\": {\n" + "      \"sourceControlType\": \"Git\"\n" + "    },\n"
						+ "    \"processTemplate\": {\n"
						+ "      \"templateTypeId\": \"6b724908-ef14-45cf-84f8-768b5384da45\"\n" + "    }\n" + "  }\n"
						+ "}")
				.when().post(getDevopsDetails.getOrgName() + "/_apis/projects").then().extract().response();

		JsonPath json = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 202);
		createProjectId = json.getString("id");
//		System.out.println(createProjectId);

	}

	@Test(enabled = false)
	public static void deleteProject() throws IOException {
		System.out.println("delete is ============" + createProjectId);
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().queryParam("api-version", "7.1-preview.4")
				.header("Authorization", getDevopsDetails.getAccessToken()).header("Content-Type", "application/json")
				.when()
				.delete(getDevopsDetails.getOrgName() + "/_apis/projects/" + "46c39a99-458d-4137-92dc-7aabb5502f84")
				.then().extract().response();

		JsonPath json = response.jsonPath();
		System.out.println(response.asString());
//		Assert.assertEquals(response.statusCode(), 202);
	}

}
