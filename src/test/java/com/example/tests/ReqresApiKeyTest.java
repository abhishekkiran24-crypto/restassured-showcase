package com.example.tests;

import org.testng.annotations.Test;

import reqres.ReqResRequestSpecFactory;

import static io.restassured.RestAssured.given;

public class ReqresApiKeyTest {

    @Test
    public void getUser_shouldReturn200() {
        given()
            .spec(ReqResRequestSpecFactory.getRequestSpec())
        .when()
            .get("/users/2")
        .then()
            .statusCode(200);
    }
}
