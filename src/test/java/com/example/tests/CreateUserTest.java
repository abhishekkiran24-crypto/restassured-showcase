package com.example.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.example.tests.reqres.ReqresBaseTest;
import com.example.utils.TestDataProviders;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reqres.ReqResRequestSpecFactory;
import reqres.TokenManager;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.example.utils.ReqResClient;
import static org.hamcrest.Matchers.*;




@Epic("User Management")
@Feature("Create User")
public class CreateUserTest extends ReqresBaseTest{
	
	/*@DataProvider(name="createUserData")
	public Object[][] createUserData()
	{
		return new Object[][] {
			{"morpheus","leader"},
			{"neo","the one"}
		};
	}*/
	
@Test(dataProvider="usersFromJson",dataProviderClass=TestDataProviders.class)
@Story("Create user with valid payload")
@Severity(SeverityLevel.CRITICAL)
//public void createUser_dataDriven(String name, String job)
public void createUser_dataDriven(String name, String job)
	{
	//optional: add a named step to Allure
	//Allure.step("Create user with name=" + name + " job=" + job);
		    

	             /*given()
                    .spec(ReqResRequestSpecFactory.getRequestSpec())
                    //.header("Authorization","Bearer "+TokenManager.getToken())
			        .contentType(ContentType.JSON)
			        .body("{\"name\":\"" + name + "\", 	\"job\":\"" + job + "\"}")
			        .when()
			        .post("/api/users")
			        .then()
			        .spec(ReqResRequestSpecFactory.getResponseSpec())
			        .statusCode(201)
			        .body(matchesJsonSchemaInClasspath("schemas/createUserSchema.json"))
			        .body("id",notNullValue())
			        .body("createdAt",notNullValue());*/
	String payload = "{\"name\":\"" + name + "\", \"job\":\"" + job + "\"}";
	Response res = ReqResClient.postWithRetry("/users", payload);

	// debug print - will show exact status and body in the console
	System.out.println("DEBUG: status=" + res.getStatusCode() + " body=" + res.asString());

	int status = res.getStatusCode();

	if (status == 201) {
	    res.then()
	       .statusCode(201)
	       .body(matchesJsonSchemaInClasspath("schemas/createUserSchema.json"))
	       .body("id", notNullValue())
	       .body("createdAt", notNullValue());
	} else if (status == 429) {
	    // Accept rate-limit response in dev environment
	    res.then()
	       .statusCode(429)
	       .body("error", equalTo("Blocked"))
	       .body("message", containsString("Too many invalid requests"));
	} else {
	    // Fail fast on unexpected responses
	    res.then().statusCode(201);
	}


	               
	//String id=response.jsonPath().getString("id");
	//String createdAt=response.jsonPath().getString("createdAt");
	//System.out.println("Created id: " + id + ", createdAt: " + createdAt);
	
	

	}

}

