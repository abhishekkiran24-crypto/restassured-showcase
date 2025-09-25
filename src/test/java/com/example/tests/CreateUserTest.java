package com.example.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.example.tests.reqres.ReqresBaseTest;
import common.ConfigReader;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import reqres.ReqResRequestSpecFactory;
import reqres.TokenManager;



import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.example.utils.DataGenerator;
import com.example.utils.TestDataProviders;

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
	             given()
                    .spec(ReqResRequestSpecFactory.getRequestSpec())
                    .header("Authorization","Bearer "+TokenManager.getToken())
			        .contentType(ContentType.JSON)
			        .body("{\"name\":\"" + name + "\", 	\"job\":\"" + job + "\"}")
			        .when()
			        .post("/api/users")
			        .then()
			        .spec(ReqResRequestSpecFactory.getResponseSpec())
			        .statusCode(201)
			        .body("id",notNullValue())
			        .body("createdAt",notNullValue());
	               
	//String id=response.jsonPath().getString("id");
	//String createdAt=response.jsonPath().getString("createdAt");
	//System.out.println("Created id: " + id + ", createdAt: " + createdAt);
	
	

	}

}

