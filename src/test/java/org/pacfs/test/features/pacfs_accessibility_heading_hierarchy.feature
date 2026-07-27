@PCFS-91 @Regression
Feature: PACFS page heading hierarchy

  As a PACFS user relying on accessible content
  I want pages to use the correct heading hierarchy and captions
  So that the application complies with accessibility standards and provides a consistent user experience.

  Scenario: 1- Display date and contextual information as H2 on the Cases page
    Given I am viewing the Cases page
    When the page is rendered
    Then the page heading "Cases" should be displayed as the H1
    And the service name "Prepare a case for sentence" should be displayed as the page caption
    And any date or contextual information should be displayed as an H2
    And the page should contain only one H1

  Scenario: 2- Verify heading hierarchy on the Cases page
    Given I am viewing the Cases page
    When the page is rendered
    Then the page should follow the heading hierarchy:
      | Heading                               | Level |
      | Cases                                 | H1    |
      | Cookies on Prepare a case for sentence|H2     |
      |Search                                 |H2     |
      |Filter the case list                   |H2     |
      |(Today)                                |H2     |

  Scenario: 3- Display Hearing Outcomes heading and caption
    Given I am viewing the Hearing Outcomes page
    When the Outcomes page is rendered
    Then the service name "Prepare a case for sentence" should be displayed as the Outcomes page caption
    And "Hearing outcomes" should be displayed as the H1
    And the Cases to Result section should be displayed as an H2
    And any remaining tab headings should be displayed as H2
    And the page should contain only one H1 in Hearing outcomes page

  Scenario: 4- Verify Hearing Outcomes page title
    Given I am viewing the Hearing Outcomes page
    When the Outcomes page is rendered
    Then the browser page title should be "Hearing outcomes - Cases to result - Prepare a case for sentence"

  Scenario: 5- Display Search Results heading and caption
    Given I am viewing the Search Results for all courts page
    When the My courts page is rendered
    Then the service name "Prepare a case for sentence" should be displayed as the My courts page caption
    And "My courts" should be displayed as the H1
    And the My courts page should contain only one H1

  Scenario: 6- Verify Search Results page title
    Given I am viewing the Search Results for all courts page
    When the My courts page is rendered
    Then the browser My courts page title should be "My courts - Prepare a case for sentence"
#  "Search results for all courts - Prepare a case for sentence"

  Scenario: 7- Verify page complies with WCAG heading requirements
    Given I am viewing the Search Results for all courts page
    When the My courts page is rendered
    Then the My courts page should contain only one H1
    And headings should follow the correct hierarchy

#    Additional Regression Scenarios (Based on Ticket Comments)
  Scenario: 8- Verify heading hierarchy for Link an nDelius record page opened from Cases
    Given I navigate to the "Link an nDelius record to the defendant" page from Cases
    When the Adult Custody page is rendered
    Then the browser Adult Custody page title should be " - Adult Custody < 12m (6 Months) - Prepare a case for sentence"

  Scenario: 9- Verify heading hierarchy for Link an nDelius record page opened from Case Summary
    Given I navigate to the "Link an nDelius record to the defendant" page from Case Summary
    When the Case summary page is rendered
    Then the browser Case summary page title should be " - Case summary - Prepare a case for sentence"

  Scenario: 10- Verify Probation Record page title
    Given I am viewing a Probation Record
    When I select "View record"
    Then the browser Adult Custody page title should be " - Adult Custody < 12m (6 Months) - Prepare a case for sentence"
#  "{Defendant name} - {Type of order} - Prepare a case for sentence"