@PCFS-104 @Regression
Feature: Display environment label in PACFS header

  As a PACFS user
  I want the current environment to be clearly displayed in the header
  So that I can identify which environment I am working in and avoid confusion or errors.

  Scenario: 1- Display DEV environment label in the header
    Given I am using the PACFS "DEV" environment
    When the My courts page is rendered
    Then the environment label "DEV" should be displayed next to "Probation Digital Services" in the header
    And the environment label should be visually distinct from the service name

#  @ignore
#  Scenario: 2- Display PRE-PRODUCTION environment label in the header
#    Given I am using the PACFS "PRE-PRODUCTION" environment
#    When the My courts page is rendered
#    Then the environment label "PRE-PRODUCTION" should be displayed next to "Probation Digital Services" in the header
#    And the environment label should be visually distinct from the service name

  @ignore
#  Scenario: 3- Environment label is not displayed in the Production environment
#    Given I am using the PACFS "Production" environment
#    When the My courts page is rendered
#    Then the "DEV or PRE-PRODUCTION" environment label should not be displayed in the header
#    And the service name "Probation Digital Services" should remain displayed

  Scenario: 2- Maintain environment label across PACFS pages
    Given I am using the PACFS "DEV" environment
    And the environment label is displayed in the header
    When I navigate between PACFS pages
    Then the same environment label should remain displayed in the header
    And the label should remain next to "Probation Digital Services"

  Scenario: 3- Environment label remains visible when navigating through the service
    Given I am using the PACFS "DEV" environment
    When I navigate to different areas of the PACFS service
    Then the environment label should remain visible
    And the environment label should not change unexpectedly