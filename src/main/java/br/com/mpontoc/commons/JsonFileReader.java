package br.com.mpontoc.commons;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

public class JsonFileReader {

	@Test
	public void receiveSingleJson() {

		JSONParser parser = new JSONParser();
		try {

			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("./src/test/resources/json/gmail.json"));
			System.out.println(jsonObject);

			Object obj = new Object();
			obj = jsonObject;

			JSONObject indexJson = (JSONObject) obj;

			JSONObject getValueField = (JSONObject) indexJson.get("gmail");

			String firstName = (String) getValueField.get("user");
			System.out.println(firstName);

			String lastName = (String) getValueField.get("pass");
			System.out.println(lastName);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String getJsonSingleField(String nameArchiveJson, String nameObjectJson, String field) {
		
		JSONParser parser = new JSONParser();
		
		String fieldReturn = "";
		
		try {

			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("./src/test/resources/json/" + nameArchiveJson));
			System.out.println(jsonObject);

			Object obj = new Object();
			obj = jsonObject;

			JSONObject indexJson = (JSONObject) obj;
			JSONObject getFields = (JSONObject) indexJson.get(nameObjectJson);
			fieldReturn = (String) getFields.get(field);

			return fieldReturn;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testGetSingleJson() {
		
		String user = getJsonSingleField("gmail.json", "gmail", "user");
		System.out.println("O usuário é " + user);
		
	}

	@Test
	public void receiveArrayJson() {

		JSONParser parser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/json/employees.json"));

			for (Object obj : jsonArray) {

				JSONObject indexJsonArray = (JSONObject) obj;
				System.out.println(indexJsonArray);

				JSONObject getValueFieldArray = (JSONObject) indexJsonArray.get("employee");

				String firstName = (String) getValueFieldArray.get("firstName");
				System.out.println(firstName);

				String lastName = (String) getValueFieldArray.get("lastName");
				System.out.println(lastName);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
