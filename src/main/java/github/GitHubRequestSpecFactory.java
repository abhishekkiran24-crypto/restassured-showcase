package github;

import common.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class GitHubRequestSpecFactory {

    private static RequestSpecification spec;

    public static RequestSpecification getRequestSpec() {
        if (spec == null) {
            String baseUrl = ConfigReader.get("base.url.github");
            String token = GitHubTokenManager.getToken();

            spec = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .addHeader("Authorization", "Bearer " + token)
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return spec;
    }
}
