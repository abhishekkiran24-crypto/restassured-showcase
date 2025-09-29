# RestAssured Framework

This repository demonstrates a **Rest Assured test automation framework**, built step by step across 3 phases of the learning roadmap.

---

## Phase 1 (Foundations)

- Basic **GET and POST** tests (ReqRes public API)  
- Reusable `RequestSpecification`  
- Config-driven base URL  
- JSON Schema validation example  

**How to run:**
mvn test

## Phase 2 (Enhancements)

- Centralized Request & Response Specifications
- Data-driven tests using TestNG DataProviders
- Test grouping (smoke, regression)
- Multi-environment config management (-Denv=qa, -Denv=prod)
- TokenManager utility (dummy for ReqRes, structured for real APIs)
- RetryAnalyzer for flaky test handling
- Global request/response logging
- Parallel execution with TestNG & Surefire

**How to run with env:**
mvn -Denv=prod test

## Phase 3 (Advanced Features)

- Allure Reporting with request/response attachments
- POJO-based payload builders (Jackson serialization/deserialization)
- GitHub API tests with real token management & TTL handling
- API Key & Header management
- Enhanced framework utilities & segregation (common, reqres, github packages)
- CI/CD integration via GitHub Actions
- View Allure report after run:
allure serve target/allure-results

## Phase 4
- CI + Allure report published to GitHub Pages
## Run tests locally
- Add your PAT to env (do NOT store in repo):
```bash
export RESTASSURED_GH_PAT=ghp_XXXXXXXXXXXXXXXXXXXX

## Tech Stack

- Java 11
- Maven
- Rest Assured 5.x
- TestNG
- Jackson
- Allure Reports
- GitHub Actions
