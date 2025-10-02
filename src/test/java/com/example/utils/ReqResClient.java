package com.example.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReqResClient {
    private static final int MAX_ATTEMPTS = 3;

    /**
     * Post JSON body to a path (e.g. "/users"). Retries when server returns 429.
     * Returns the final Response.
     */
    public static Response postWithRetry(String path, String jsonBody) {
        int attempt = 1;

        while (attempt <= MAX_ATTEMPTS) {
            Response r = given()
                    .spec(reqres.ReqResRequestSpecFactory.getRequestSpec())
                    .contentType(io.restassured.http.ContentType.JSON)
                    .body(jsonBody)
                .when()
                    .post(path);

            System.out.println("ReqResClient: attempt=" + attempt + " status=" + r.getStatusCode());
            // if not rate-limited, return (201 or 4xx, etc.)
            if (r.getStatusCode() != 429) {
                return r;
            }

            // got 429 -> respect Retry-After header where possible
            String retryAfter = r.getHeader("Retry-After");
            int waitSec = 5;
            try {
                if (retryAfter != null && !retryAfter.isEmpty()) {
                    waitSec = Integer.parseInt(retryAfter);
                }
            } catch (NumberFormatException ignored) {}

            System.out.println("ReqResClient: got 429, waiting " + waitSec + "s before retry (attempt " + attempt + ")");
            try {
                Thread.sleep(waitSec * 1000L);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
            attempt++;
        }

        // final attempt: just do the call again and return whatever we get
        return given()
                .spec(reqres.ReqResRequestSpecFactory.getRequestSpec())
                .contentType(io.restassured.http.ContentType.JSON)
                .body(jsonBody)
            .when()
                .post(path);
    }
}
