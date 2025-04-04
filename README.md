# Appium Automation Test Project

This project automates testing for a mobile application using Appium and Java. It follows the Page Object Model (POM) design pattern and leverages TestNG as the testing framework.

## Project Structure

The project is structured as follows:

- **src/main/java**: Contains the core utilities and helper classes.
- **src/test/java**: Contains the test cases written using TestNG and the page objects.
- **src/test/resources/**: A folder for storing apk files and screenshots for comparing with other ones.

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
4. **Node.js (18 or later)**
5. **Android SDK** (for Android automation)
6. **Android AVD** (for Android emulators)

## Setup Appium in order to run the automation tests from this project

### 1️⃣ Install Appium

```sh
npm install -g appium
```
### 2️⃣ Install Required Plugins and Drivers
```sh
appium setup
```
For advanced setup, refer to the official documentation: https://appium.io/docs/en/latest/intro/