@PCF-82 @ui
Feature: PACFS Header and Footer Compliance

  As a PACFS user
  I want a consistent MoJ-standard header and footer across the application
  So that the service is familiar, compliant and easy to navigate.

  @Regression
  Scenario: 1- Display MoJ standard header across PACFS
    Given I have logged into PACFS
    When I navigate to any PACFS page
    Then the MoJ standard header should be displayed
    And the header should remain visible throughout the user journey
    And the header layout should remain consistent across all pages

  @Regression
  Scenario: 2 Display MoJ standard footer across PACFS
    Given I have logged into PACFS
    When I navigate to any PACFS page
    Then the MoJ standard footer should be displayed
    And the footer layout should remain consistent across all pages

#  Scenario: 3 Header persists when navigating between PACFS pages
#    Given I am on the PACFS Home page
#    When I navigate to different pages within PACFS
#    Then the header should remain visible
#    And the header should not change unexpectedly

  Scenario: 3 Navigate to PACFS Home page using the Probation Digital Services header
    Given I am on any PACFS page
    When I click the "Probation Digital Services" link in the header
    Then I should be redirected to the PACFS Home page

  @Regression
  Scenario: 4- Display logged in user details
    Given I am logged into PACFS
    When the page loads
    Then my user name should be displayed in the header

  @Regression
  Scenario: 5- Cookies link opens the new Cookies page
    Given I am on any PACFS page
    When I click the Cookies link in the footer
    Then the latest Cookies policy page should be displayed

  @Regression
  Scenario: 6- Verify the What's New link is no longer displayed
    Given I am on any PACFS page
    When the footer is displayed
    Then the "What's New" link should not be visible

  @Regression
  Scenario Outline: 7- Verify footer links
    Given I am on any PACFS page
    When I select "<Footer Link>" from My Courts
    Then the corresponding page should be displayed
    Examples:
      | Footer Link        |
      | Cookies policy     |
      | Accessibility      |
      | Privacy policy     |

#  Scenario: 9- Verify PACFS UI components after MoJ package upgrade
#    Given I have logged into PACFS
#    When I navigate through the PACFS application
#    Then all pages should render correctly
#    And buttons should be displayed correctly
#    And links should function correctly
#    And forms should display correctly
#    And page layouts should remain consistent

#  Scenario: 10- Verify application navigation after header implementation
#    Given I have logged into PACFS
#    When I navigate through the PACFS application
#    Then each page should load successfully
#    And no unexpected errors should be displayed

  @Regression
  Scenario: 8- Verify header and footer after refreshing the page
    Given I am on any PACFS page
    When I refresh the page
    Then the header should still be displayed
    And the footer should still be displayed

#  Scenario: 12- Verify header remains consistent when using browser navigation
#    Given I have navigated through multiple PACFS pages
#    When I use the browser Back and Forward buttons
#    Then the header should remain consistent
#    And the footer should remain consistent