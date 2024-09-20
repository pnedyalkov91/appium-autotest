# Appium Automation Test Project

This project automates testing for a mobile application using Appium and Java. It follows the Page Object Model (POM) design pattern and leverages TestNG as the testing framework.

## Project Structure

The project is structured as follows:

- **src/main/java**: Contains the core utilities and helper classes.
- **src/test/java**: Contains the test cases written using TestNG and the page objects.
- **src/test/resources/screenshots**: A folder for storing actual and expected screenshots used for visual comparisons.

## Features

- **Appium for Mobile Testing**: Automated UI tests for mobile applications using Appium's Java client.
- **Page Object Model (POM)**: Simplifies the maintenance of test scripts by separating test logic from UI element definitions.
- **Screenshot Comparison**: Verifies UI elements by taking screenshots and comparing them to expected results.
- **TestNG Integration**: Used for organizing and running test cases.

## Prerequisites

Before running the project, ensure the following are installed:

1. **Java JDK (Version 21 or later)**
2. **Maven (Version 3.9 or later)**
3. **Appium (Version 2.11 or later)**
4. **Node.js** (for running Appium server)
5. **Android SDK** (for Android automation)
6. **Android AVD** (for Android emulators)