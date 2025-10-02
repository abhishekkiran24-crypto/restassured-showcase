package com.example.tests.github;

import org.testng.annotations.Test;

import github.GitHubRequestSpecFactory;
import io.qameta.allure.restassured.AllureRestAssured;
import static io.restassured.RestAssured.given;


public class GitHubNegativeTests {
	
	@Test
	public void getUser_withoutToken_shouldReturn401() {
		given()
		  .filter(new AllureRestAssured())
		  .spec(GitHubRequestSpecFactory.getRequestSpecWithoutAuth())
		 .when()
		  .get("/user")
		 .then()
		  .statusCode(401);
	}

}
