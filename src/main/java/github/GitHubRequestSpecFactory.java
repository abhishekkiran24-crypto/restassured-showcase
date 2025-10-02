package github;

import common.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GitHubRequestSpecFactory {

    private static RequestSpecification authspec;
    private static RequestSpecification unauthspec;
    
 // prevents Authorization header from being printed in logs / Allure attachments
    private static RestAssuredConfig safeLogConfig() {
        return RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig().blacklistHeader("Authorization"));
    }


    public static RequestSpecification getRequestSpec() {
        if (authspec == null) {
            String baseUrl = ConfigReader.get("base.url.github");
            String token = GitHubTokenManager.getToken();

            authspec = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .addHeader("Authorization", "Bearer " + token)
                    .setConfig(safeLogConfig())
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return authspec;
    }
    public static RequestSpecification getRequestSpecWithoutAuth() {
        if (unauthspec == null) {
            String baseUrl = ConfigReader.get("base.url.github");
        

            unauthspec = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    .setConfig(safeLogConfig())
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return unauthspec;
    }
}
