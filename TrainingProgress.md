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