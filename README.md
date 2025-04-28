## UI Playground Test automation

This repository contains a test automation framework for the UI Playground project. The framework is designed to automate the testing of the UI components and ensure their functionality.


### Table of Contents
  - [Short Questions](#short-questions)
  - [Features](#features)
  - [Pre-requisites](#pre-requisites)
  - [How to set up locally](#how-to-set-up-locally)
  - [Running Tests](#running-tests)
  - [Contributing](#contributing)
  - [License](#license)

### Short Questions
1. How would you integrate this test suite into a CI/CD pipeline (e.g., GitHub Actions,
   Jenkins)?
Answer: 
   - Under .github/workflows a workflow file template is already created which contains the minimal definition to run the whole suite using the TAF features.
2. What would be your approach to scaling this framework for a large application?
Answer:
   - The framework is partially designed to be scalable. It uses the Page Object Model (POM) design pattern, which allows for easy maintenance and scalability. 
   - The framework can be easily extended to support additional browsers or devices by adding new capabilities to the Playwright configuration.
   - The test suite can be divided into smaller test suites based on functionality or components, allowing for parallel execution and faster feedback.
   - The framework can be integrated with a test management tool to manage test cases and track test results. Using Jira or TestRail, for example, can help in managing test cases and tracking defects.
3. What quality metrics would you track and report on as a QA Leader?
Answer:
   - Test Execution Metrics: Track the number of test cases executed, passed, failed, and skipped.
   - Defect Density: Measure the number of defects found per test case executed.
   - Test Coverage: Measure the percentage of code covered by automated tests.
   - Test Automation Rate: Measure the percentage of test cases automated compared to the total number of test cases.

### Features
- **TestNG**: A testing framework that provides a flexible and powerful way to write and run tests.
- **Playwright**: A library to automate Chromium, Firefox, and WebKit with a single API.
- **Gradle**: A build automation tool that is used to manage dependencies and run tests.
- **POM**: Page Object Model design pattern to create an object repository for web UI elements.

### Pre-requisites
- Java 17 or higher
- Git
- IDE or command line


### How to set up locally

1. Clone the repository
   ```bash
   git clone 
    ```
2. ```bash
   ./gradlew build
   ./gradlew test
   ```

