package com.example.tests.reqres;
import org.testng.annotations.BeforeClass;

import github.GitHubRequestSpecFactory;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import reqres.ReqResRequestSpecFactory;
import io.qameta.allure.restassured.AllureRestAssured;


public class ReqresBaseTest {
	
	protected RequestSpecification reqSpec;
	
	//@BeforeClass
	public void setup()
	{
		reqSpec=ReqResRequestSpecFactory.getRequestSpec();
		// register the Allure filter so it runs for all requests during tests:
        RestAssured.filters(new AllureRestAssured());
		
	}
}
