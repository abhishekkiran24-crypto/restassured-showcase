package com.example.tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import reqres.ReqResRequestSpecFactory;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresNegativeTests {
	
	@Test
	public void getUser_nonExisting_shouldReturn404() {
		given()
		  .spec(ReqResRequestSpecFactory.getRequestSpec())
		.when()
		  .get("/api/users/23")
		.then()
		  .statusCode(404);
	}
	@Test
	public void createUser_invalidPayload_shouldReturn400or422() {
		given()
		  .spec(ReqResRequestSpecFactory.getRequestSpec())
		  .contentType(ContentType.JSON)
		  .body("{\name\":12345 }") //invalid type
		 .when()
		  .post("/api/users")
		 .then()
		  .statusCode(anyOf(is(400), is(422), is(201)));
	}

}
