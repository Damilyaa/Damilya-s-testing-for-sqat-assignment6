# DemoQA Selenium Test Framework with 

## Overview
This project is a Selenium WebDriver automation framework for [DemoQA](https://demoqa.com/) using **Java**, **TestNG**, and **Page Object Model (POM)**.  
It is designed to be maintainable, extensible, and easy to run in CI/CD pipelines.

### Features
- ChromeDriver WebDriver setup
- Page Object Model for reusable UI interactions
- Test lifecycle management using TestNG annotations (`@BeforeClass`, `@BeforeMethod`, `@Test` , `@AfterMethod`, `@AfterClass`)
- Automatic screenshot capture on test failure
- Logging using Log4j2
- ExtentReports for HTML reporting
- Clean separation of test logic, page objects, and utilities

## Prerequisites
- Java JDK 11+
- Maven 3+
- Chrome browser installed
- ChromeDriver on system PATH (or managed automatically via WebDriverManager)

## How to Run Tests
```bash
mvn test -DrunMode=cloud 
```

**Test reports:**

   ExtentReports HTML: test-output/ExtentReports/index.html
   Screenshots for failed tests: test-output/screenshots/

## Test Lifecycle

| Annotation        | Purpose |
|------------------|---------|
| `@BeforeClass`    | Initialize WebDriver once per test class |
| `@BeforeMethod`   | Navigate to the homepage, initialize page objects |
| `@AfterMethod`    | Clean cookies to ensure test independence |
| `@AfterClass`     | Quit the browser after all tests in the class |
| Listener          | Capture screenshots on test failure and attach to ExtentReports |

## **Amangeldykyzy Damilya SE-2327**