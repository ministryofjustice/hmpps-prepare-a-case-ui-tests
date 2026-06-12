# HMPPS Prepare A Case UI Tests

[![repo standards badge](https://img.shields.io/endpoint?labelColor=231f20&color=005ea5&style=for-the-badge&url=https%3A%2F%2Foperations-engineering-reports.cloud-platform.service.justice.gov.uk%2Fapi%2Fv1%2Fcompliant_public_repositories%2Fendpoint%2Fhmpps-prepare-a-case-ui-tests&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABmJLR0QA/wD/AP+gvaeTAAAHJElEQVRYCe2YessrSRSHOwkJ...)](https://operations-engineering-reports.cloud-platform.service.justice.gov.uk/public-github-repositories.html#hmpps-prepare-a-case-ui-tests)

Automated UI tests for the [HMPPS Prepare a Case](https://github.com/ministryofjustice/prepare-a-case) service, built with **Selenium WebDriver**, **Cucumber**, and **TestNG**.

## Prerequisites

- Java 25 (see `.java-version`)
- Maven 3.9.9 (provided via the Maven Wrapper — no need to install Maven)
- Chrome browser (for local execution)
- ChromeDriver (managed automatically via WebDriverManager)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/ministryofjustice/hmpps-prepare-a-case-ui-tests.git
cd hmpps-prepare-a-case-ui-tests
```

### 2. Create your local configuration

Copy the template config file and fill in your values:

```bash
cp src/main/java/org/pacfs/framework/config/GlobalConfig.properties.template \
   src/main/java/org/pacfs/framework/config/GlobalConfig.properties
```

Edit `GlobalConfig.properties` with your environment-specific values (URLs, credentials, etc.).

> ⚠️ **Never commit `GlobalConfig.properties`** — it is git-ignored because it contains secrets.

### 3. Run the tests

```bash
./mvnw clean test
```

To generate the Cucumber report:

```bash
./mvnw clean verify
```

The report will be available in `target/`.

## Configuration

The framework supports configuration via **environment variables** (preferred for CI/CD) with fallback to the local `GlobalConfig.properties` file.

### Environment Variables

| Environment Variable | Description | Properties File Key |
|---|---|---|
| `PAC_AUT_URL` | Application under test URL | `AUT` |
| `PAC_AUT1_URL` | Secondary application URL | `AUT1` |
| `PAC_USERNAME` | Test user username | `UserName` |
| `PAC_PASSWORD` | Test user password | `Password` |
| `PAC_BROWSER_TYPE` | Browser type (`Chrome`, `Firefox`, `Headless`) | `BrowserType` |
| `PAC_ENVIRONMENT_TYPE` | Environment type (`local`, `grid`, `BrowserStack`) | `environmentType` |
| `PAC_SELENIUM_GRID` | Selenium Grid hub URL | `SeleniumGrid` |
| `PAC_AUT_CONNECTION` | Application DB connection string | `AUTConnectionString` |
| `PAC_REPORTING_CONNECTION` | Reporting DB connection string | `ReportingConnectionString` |
| `PAC_DRIVER_TYPE` | JDBC driver class name | `DriverType` |
| `PAC_LOG_PATH` | Path for log output | `LogPath` |
| `PAC_EXCEL_PATH` | Path to test data Excel file | `ExcelSheetPath` |
| `PAC_EXCEL_SHEET` | Excel sheet name | `ExcelSheetName` |
| `PAC_AAD_URL` | Azure AD URL | `AADURL` |

### Running with environment variables

```bash
PAC_AUT_URL="https://your-env.example.com" \
PAC_USERNAME="testuser" \
PAC_PASSWORD="testpass" \
PAC_BROWSER_TYPE="Headless" \
./mvnw clean test
```

## Running from GitHub Actions

This repo is designed to be triggered from another repository's GitHub Actions pipeline. Configure the following **repository secrets** in the calling repo:

| Secret | Description |
|---|---|
| `PAC_AUT_URL` | Target environment URL |
| `PAC_USERNAME` | Test user credentials |
| `PAC_PASSWORD` | Test user credentials |
| `PAC_SELENIUM_GRID` | Selenium Grid URL (if using grid) |

Example workflow step:

```yaml
- name: Set up JDK 25
  uses: actions/setup-java@v4
  with:
    java-version: '25'
    distribution: 'temurin'

- name: Run UI Tests
  env:
    PAC_AUT_URL: ${{ secrets.PAC_AUT_URL }}
    PAC_USERNAME: ${{ secrets.PAC_USERNAME }}
    PAC_PASSWORD: ${{ secrets.PAC_PASSWORD }}
    PAC_BROWSER_TYPE: "Headless"
    PAC_ENVIRONMENT_TYPE: "local"
  run: |
    ./mvnw clean verify
```

## Project Structure

```
src/
├── main/java/org/pacfs/framework/
│   ├── base/           # WebDriver setup and browser management
│   ├── config/         # Configuration reader and settings
│   ├── controls/       # UI control wrappers
│   └── utilities/      # Logging and helper utilities
├── main/resources/
│   └── logs/           # Test execution logs (git-ignored)
└── test/java/org/pacfs/test/
    ├── features/       # Cucumber feature files
    ├── stepdefs/       # Step definitions
    ├── pages/          # Page Object Model classes
    ├── listeners/      # TestNG listeners
    └── testRunners/    # TestNG test runner
```

## Licence

[MIT License](LICENCE)

