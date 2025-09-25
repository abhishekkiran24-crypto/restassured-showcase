package com.example.tests.github;
import org.testng.annotations.BeforeClass;

import github.GitHubRequestSpecFactory;
import io.restassured.RestAssured;

public class GithubBaseTest {
		    @BeforeClass
		    public void setup() {
		        RestAssured.requestSpecification = GitHubRequestSpecFactory.getRequestSpec();
		    }
		}

