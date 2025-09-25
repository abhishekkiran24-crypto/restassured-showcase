package com.example.tests.github;

import org.testng.annotations.Test;

import github.GitHubRequestSpecFactory;

import static io.restassured.RestAssured.given;

public class GitHubAuthTest {

    @Test
    public void getAuthenticatedUser_shouldReturn200() {
        given()
            .spec(GitHubRequestSpecFactory.getRequestSpec())
        .when()
            .get("/user")   // GET authenticated user
        .then()
            .statusCode(200);
    }

    @Test
    public void listRepos_shouldReturn200() {
        given()
            .spec(GitHubRequestSpecFactory.getRequestSpec())
        .when()
            .get("/user/repos")   // GET repos of authenticated user
        .then()
            .statusCode(200);
    }
}
