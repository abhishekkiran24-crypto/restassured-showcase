package reqres;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import common.ConfigReader;

/**
 * Simple TokenManager for ReqRes practice:
 * - posts email+password to /api/login
 * - includes x-api-key header for the login call if api.key is present
 * - caches token and expiry (default TTL from config)
 */
public class TokenManager {

    private static String token;
    private static long expiryTime;

    public static synchronized String getToken() {
        long now = System.currentTimeMillis();
        if (token == null || now >= expiryTime) {
            fetchNewToken();
        }
        return token;
    }

    private static void fetchNewToken() {
        String base = ConfigReader.get("base.url");               // e.g. https://reqres.in
        String loginPath = ConfigReader.get("auth.loginPath");    // e.g. /api/login
        String username = ConfigReader.get("auth.username");      // email for ReqRes
        String password = ConfigReader.get("auth.password");
        int defaultTtl = ConfigReader.getInt("auth.defaultTtlSeconds", 3600);

        String apiKey = ConfigReader.get("api.key");              // may be empty

        // Build request and add x-api-key only for login if present
        io.restassured.specification.RequestSpecification req = given()
                .baseUri(base)
                .contentType("application/json")
                .body("{ \"email\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .log().all(); // temporary: prints request (headers + body)

        if (apiKey != null && !apiKey.trim().isEmpty()) {
            req = req.header("x-api-key", apiKey); // hardcoded header name for simplicity
        }

        Response response = req
                .when()
                .post(loginPath)
                .then()
                .log().all() // temporary: prints response
                .extract().response();

        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed: " + response.statusCode() + " - " + response.asString());
        }

        // Extract token (try common fields)
        String accessToken = response.path("token");
        if (accessToken == null) {
            accessToken = response.path("access_token");
        }
        if (accessToken == null) {
            throw new RuntimeException("Token not found in login response: " + response.asString());
        }

        token = accessToken;
        expiryTime = System.currentTimeMillis() + (defaultTtl * 1000L);
    }

    // Optional helper to force refresh from tests if needed
    public static synchronized void refreshToken() {
        token = null;
        expiryTime = 0;
    }
}
