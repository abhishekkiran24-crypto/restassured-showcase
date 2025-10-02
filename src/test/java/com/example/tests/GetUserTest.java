package com.example.tests;

import org.testng.annotations.Test;

import com.example.tests.reqres.ReqresBaseTest;

import github.GitHubRequestSpecFactory;
import github.GitHubTokenManager;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import reqres.ReqResRequestSpecFactory;
import reqres.TokenManager;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Create User")
@Feature("Get User")
public class GetUserTest extends ReqresBaseTest {
	
	//@Test//(retryAnalyzer=RetryAnalyzer.class)
	@Story("Get single user by id")
	@Severity(SeverityLevel.NORMAL)
	public void getSingleUser()
	{
		Allure.step("Get user id=2");
		RestAssured.given()
		          .spec(ReqResRequestSpecFactory.getRequestSpec())
		          .header("Authorization","Bearer "+TokenManager.getToken())
		          .when()
		          .get("/api/users/2")
		          .then()
		          .spec(ReqResRequestSpecFactory.getResponseSpec())
		         
		          .statusCode(200)
		          .body(matchesJsonSchemaInClasspath("schemas/getUserSchema.json"))
		          .body("data.id",equalTo(2));
	}
	//@Test
	public void getUser_withBearerToken_shouldReturn200() {
	    given()
	        .spec(ReqResRequestSpecFactory.getRequestSpec())
	    .when()
	        .get("/users/2")
	    .then()
	        .statusCode(200);
	}
}



