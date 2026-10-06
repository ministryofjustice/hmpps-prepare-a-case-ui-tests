@PCFS-83 @Regression @ui
Feature: PACFS page heading and title structure

  As a PACFS user using assistive technology
  I want the service name to be presented as a caption and page heading as the H1
  So that the page structure follows the approved design pattern and accessibility standards.

  Scenario: 1- Display service name as caption and Cases as page heading
    Given I am viewing the PACFS Case List page
    When the page content is rendered
    Then the service name "Prepare a case for sentence" should be displayed as a caption
    And the page heading "Cases" should be displayed as the H1

  Scenario: 2- Verify browser page title contains H1 followed by service name
    Given I am viewing the PACFS Case List page
    When the page content is rendered
    Then the page title should be "Cases - Hearing outcome still to be added - Prepare a case for sentence"

  Scenario: 3- Verify Case List tab navigation does not change the page heading structure
    Given I am viewing the PACFS Case List page
    When I navigate between Case List tabs
    Then the service name "Probation Digital Services" should remain displayed as the caption
    And the page heading "Cases" should remain displayed as the H1

  Scenario: 4- Display defendant name and service name correctly on Case Summary page
    Given I am viewing a defendant Case Summary page
    When the Case summary page is rendered
    Then the service name "Probation Digital Services" should be displayed as the caption
        And the defendant name should be displayed as the H1
    Then the browser Case summary page title should be " - Prepare a case for sentence"
#  "{Defendant name} - Prepare a case for sentence"

  Scenario: 5- Verify Probation Record page title format
    Given I am viewing a defendant Probation Record page
    When the Probation Record page content is rendered
    Then the service name "Probation Digital Services" should be displayed as the caption in Probation Record page
    And the defendant name should be displayed as the H1 in Probation Record page
    Then the browser Probation Record page title should be " - Probation record - Prepare a case for sentence"
#  "{Defendant name} - Probation record - Prepare a case for sentence"

  Scenario: 6- Verify Risk Register page title format
    Given I am viewing a defendant Risk Register page
    When the Risk Register page content is rendered
    Then the service name "Probation Digital Services" should be displayed as the caption in Risk Register page
    And the defendant name should be displayed as the H1 in Risk Register page
    Then the browser PRisk Register page title should be " - Risk register - Prepare a case for sentence"
#  "{Defendant name} - Risk register - Prepare a case for sentence"

#  Scenario: 7- Verify Review possible nDelius records page heading structure
#    Given I am viewing the Review possible nDelius records page
#    When the page content is rendered
#    Then the service name "Prepare a case for sentence" should be displayed as the caption
#    And the defendant name should be displayed as the H1
#    And "Review possible nDelius records" should be displayed as the H2
#    And the page title should follow the format:
#  "{Defendant name} - Review possible nDelius records - Prepare a case for sentence"

#  Scenario: 8- Verify confirmation pages display the correct page title
#    Given I am viewing a PACFS confirmation page
#    When the page content is rendered
#    Then the page should display the correct H1 heading
#    And the browser title should contain the correct page heading and service name

  Scenario: 7- Verify PACFS pages follow correct heading hierarchy
    Given I am on the My courts page
    When the My courts page is rendered
    Then the service name should be displayed as a caption
    And only one H1 heading should exist on the page
    And headings should follow the correct hierarchy