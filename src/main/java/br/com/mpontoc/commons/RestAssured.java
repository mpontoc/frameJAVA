package br.com.mpontoc.commons;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssured {
	
	public String responseString (String endpoint, String method, int statusCode) {
		
		String response = 
				
				given()
				.when()
				.get(endpoint)
				.then()
				.log().all()
				.statusCode(statusCode)
				.extract()
				.body().asString()
		;
		return response;
	}
	
	@Test
	public void validaBody(){
		
		String validaBody = responseString("https://pokeapi.co/api/v2/pokemon-form/25/", "get", 200);
		System.out.println("Este é o body da requisição " + validaBody);
		//Assert.assertEquals("pikachu", validaBody);
		if(validaBody.contains("pikachu"))
			Log.log("Teste OK");
		else
			Assert.assertTrue(false);
			
		
	}

}
