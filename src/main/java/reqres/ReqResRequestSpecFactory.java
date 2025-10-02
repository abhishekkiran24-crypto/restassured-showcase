package reqres;

import common.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResRequestSpecFactory {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    
   

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {////lazy initialization pattern. It behaves like a singleton. Ensures it's created only once ( the first time)
        	 // read api key if present
            String apiKey = ConfigReader.get("reqres.api.key");
        	RequestSpecBuilder builder = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.get("base.url.reqres"))
                    .addHeader("Accept", "application/json")
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .addFilter(new AllureRestAssured()) // keep your Allure reporting
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL);


       // only add x-api-key if config contains it
            if (apiKey != null && !apiKey.isEmpty()) {
                builder.addHeader("x-api-key", apiKey);
            }

            requestSpec = builder.build();
        }
        return requestSpec;
    }

    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) {
            responseSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();
        }
        return responseSpec;
    }
}
