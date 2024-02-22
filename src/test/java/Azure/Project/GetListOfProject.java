package Azure.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
//	static String createProjectId;
	static String getProjectId;
	public static ArrayList<String> projectIdList = new ArrayList<>();

	@BeforeClass
	public void getData() throws IOException {

		FileInputStream stream = new FileInputStream(
				"C:\\Users\\Admin\\eclipse-workspace\\Rest Assured\\RestAssured\\src\\test\\java\\env_prop\\azure.poperties");
		prop.load(stream);
	}

//    Add common header "Authorization"
	public RequestSpecification res() {
		RequestSpecBuilder resp = new RequestSpecBuilder();
		resp.addHeader("Authorization", getDevopsDetails.getAccessToken());
		RequestSpecification request = resp.build();
		return request;

	}

	@Test
	public void getProjectList() throws IOException {
		RestAssured.baseURI = getDevopsDetails.getEnvVarible("BaseURI");
		Response response = given().param("api-version", "6.0")
//				.header("Authorization", getDevopsDetails.getAccessToken())
				.spec(res()).when()
				.get(getDevopsDetails.getOrgName() + "/_apis/projects")
				.then().extract().response();

		JsonPath json = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotEquals(json.getInt("count"), 0);
//		System.out.println(response.asString());
		ArrayList<LinkedHashMap> list = new ArrayList<>();
		list = json.get("value");
		for (LinkedHashMap nameList : list) {
			if (!nameList.get("name").equals("Automate")) {
//				System.out.println(nameList.get("name"));
//				System.out.println(nameList.get("id"));
				projectIdList.add((String) nameList.get("id"));

			}else {
				getProjectId=(String) nameList.get("id");
			}
		}
//		System.out.println("list of ids are :" + projectIdList);

	}
}
