package com.example.tests.github;

import org.testng.annotations.Test;

import github.GitHubRequestSpecFactory;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;

@Epic("Github API")
@Feature("Authentication")
public class GitHubAuthTest {

    @Test
    @Story("Get authenticated user details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an authenticated user can retrieve their profile information")
    public void getAuthenticatedUser_shouldReturn200() {
        given()
            .filter(new AllureRestAssured()) //attach logs to Allure
            .spec(GitHubRequestSpecFactory.getRequestSpec())
        .when()
            .get("/user")   // GET authenticated user
        .then()
            .statusCode(200);
    }

    @Test
    @Story("List repositories of authenticated user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that an authenticated user can list their repositories")
    public void listRepos_shouldReturn200() {
        given()
            .filter(new AllureRestAssured())
            .spec(GitHubRequestSpecFactory.getRequestSpec())
        .when()
            .get("/user/repos")   // GET repos of authenticated user
        .then()
            .statusCode(200);
    }
}
