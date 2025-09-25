package github;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class GItHubTokenTest {
	//@Test
	public void tokenExpiry_simulation() throws InterruptedException {
	    String t1 = GitHubTokenManager.getToken();
	    System.out.println("t1= "+t1);
	    Thread.sleep(4000); // wait longer than TTL (only for local runs)
	    String t2 = GitHubTokenManager.getToken();
	    System.out.println("t2= "+t2);
	    // For GitHub PAT t1 == t2 (PAT same), but you verified fetch logic ran again.
	 // actually call GitHub API with refreshed token
	    given()
	        .spec(GitHubRequestSpecFactory.getRequestSpec()) // ✅ GitHub spec, not ReqRes
	    .when()
	        .get("/user")
	    .then()
	        .statusCode(200);
	}

	    @Test
	    public void forceRefresh_demo() {
	        String before = GitHubTokenManager.getToken();
	        GitHubTokenManager.refreshToken();
	        String after = GitHubTokenManager.getToken();
	        System.out.println("before=" + before + " after=" + after);
	        // demonstrates refresh flow without sleeping
	     // now actually use the refreshed token in a call
	        given()
	            .spec(GitHubRequestSpecFactory.getRequestSpec())
	        .when()
	            .get("/user")
	        .then()
	            .statusCode(200);
	    }

	}


