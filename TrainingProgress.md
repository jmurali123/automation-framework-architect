# API Testing Framework Training — 40 Days Plan

## ✅ Completed

### Day 1-11
- GET, POST, PUT, DELETE Requests
- Request Specification
- Response Specification
- Log All
- Schema Validation
- POJO Classes
- Query Parameters

### Day 12 ✅
- pathParam() — single param
- pathParams() — Map approach with HashMap
- Map<String, Object> — autoboxing concept
- Always verify API endpoints from documentation!

---

## 🔄 Next


### Day 13 ✅
- Basic Auth — preemptive vs challenge response
- Bearer Token — oauth2() method
- Dynamic token generation — @BeforeClass
- ThreadLocal concept for parallel tests

### Day 14 ✅
- XML request with JSON response validation
- Multipart file upload
- Filters — filters.LoggingFilter
- Separation of concerns concept
- Cross cutting concerns

### Day 15 ✅
- Base Class — common setup
- Constants — centralized URLs
- Package structure — base, constants, filters, models, tests
- Refactored all test classes to extend BaseTest
- Protected access modifier — encapsulation

### Day 16 ✅
- ConfigReader — reads config.properties
- JSONUtils — readJsonFile() utility
- UserValidator — Hard + Soft assertions
- Validators package created
- Static block — loads once into RAM
- ClassLoader — finds resources automatically
- Hard vs Soft assertions

### Day 20 ✅
- Lombok @Data annotation
- Removed boilerplate getters/setters
- @JsonIgnoreProperties(ignoreUnknown=true)
- Maven scope=provided concept

### Day 21 ✅
- End to End CRUD test
- POST → GET → PUT → DELETE flow
- Allure @Step annotations
- JSONPlaceholder limitation understanding
### Day 22 ✅
- Selenium Setup + WebDriverManager
- BaseUITest — browser lifecycle
- DriverManager — ThreadLocal WebDriver
- tests.api and tests.ui package structure

### Day 23 ✅
- Page Object Model
- Custom HTML Components
- @FindByLocator annotation
- CustomFieldDecorator
- LocatorReader — external locators
- Multi-environment config
- LoginPage + LoginTest

### Day 24 ✅
- Screenshot on failure in TestListener
- ProductPage created
- NavigationBar custom component
- BaseComponent with wait methods
- All HTML components extended BaseComponent
- DriverManager ThreadLocal
- LocatorReader dynamic params
- Multi-environment config
- Chrome options — password popup disabled
- deleteAllCookies — clean state per test

### Day 26 ✅
- Maven Profiles — smoke, api, ui, regression, staging
- Separate testng XML files per profile
- Surefire plugin with ${suiteXmlFile}
- systemPropertyVariables for env
- parallel="tests" for smoke and regression
- parallel="methods" for API
- Thread name verification

### Day 28 ✅
- Git Branching Strategy — GitFlow
- main branch — production ready
- develop branch — integration
- feature branches — new features
- release branches — release preparation
- hotfix branches — urgent fixes
- .gitattributes — line ending fix

### Day 29 ✅
- GitHub Actions CI Pipeline
- ci.yml workflow created
- Smoke tests run on PR and push
- Headless Chrome for CI
- Allure report uploaded as artifact
- Screenshots on failure
- GitFlow with PRs working
- Pipeline runs in ~57 seconds!