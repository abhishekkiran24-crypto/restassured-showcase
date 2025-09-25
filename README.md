# RestAssured Framework — Phase 1
This repository demonstrates a minimal Rest Assured framework built during
Phase 1 of the learning roadmap. It includes:
- Basic GET and POST tests (ReqRes public API)
- Reusable RequestSpecification
- Config-driven base URL
- JSON Schema validation example
How to run:
1. Clone the repo
2. mvn test



## Features (Phase 2)
- Centralized Request & Response Specifications
- Data-driven tests using TestNG DataProviders
- Test grouping (smoke, regression)
- Multi-environment config management (`-Denv=prod`)
- TokenManager utility (dummy for ReqRes, structured for real APIs)
- RetryAnalyzer for flaky test handling
- Global request/response logging
- Parallel execution with TestNG & Surefire

## Run Tests
```bash
mvn -Denv=prod test
