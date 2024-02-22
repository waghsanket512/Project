package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

public class getDevopsDetails {

//	String token = "synv7lcaodb2f54ygy6ov7ea45i5vmddzme5q7jzfhnspcbot6fq";
	String token="rwiwh7wuuz5aotlgaim7a2os2eqqqgs573copqwmtpxd7cusklmq";

	public static String getEnvVarible(String variableName) throws IOException {
		FileInputStream stream = new FileInputStream(
				"C:\\Users\\Admin\\eclipse-workspace\\Rest Assured\\RestAssured\\src\\test\\java\\env_prop\\azure.poperties");

		Properties prop = new Properties();
		prop.load(stream);
		return prop.getProperty(variableName);

	}

	public static String getAccessToken() {
		String accessToken = "Basic "
				+ Base64.getEncoder().encodeToString((":" + new getDevopsDetails().token).getBytes());
//		System.out.println(accessToken);
		return accessToken;
	}

	public static String getOrgName() throws IOException {
		String orgName = getEnvVarible("OrgName");
//		System.out.println("org name is ----------------" + orgName);
		return orgName;
	}

	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
