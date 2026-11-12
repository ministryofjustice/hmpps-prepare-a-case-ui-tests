@ui @HearingNote @PCFS-14
Feature: Send Outcome To Admin button visibility

  Background:
    Given I am logged into the application
    And I am on the "My courts" page
#    When I select the "Oxford and Southern Oxfordshire Magistrates' Court" court
#    Then I should be taken to the selected court page
#    And the "Cases" tab should be selected by default

  @Regression
  Scenario: 1 - Display Send outcome to admin button for today's hearing
    When I select the "Bath Law Courts" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default

    When I open a defendant with a hearing scheduled for today
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    Then the "Send outcome to admin" button should be displayed
    And the button should be displayed below the hearing details
    And the button should be displayed above the hearing notes

  @Regression
  Scenario: 2 - Display Send outcome to admin button for a past hearing
    When I select the "Oxford and Southern Oxfordshire Magistrates' Court" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default

    When I open a defendant with a hearing scheduled in the past
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    Then the "Send outcome to admin" button should be displayed
    And the button should be displayed below the hearing details
#    And the button should be displayed above the hearing notes
    And the previous date button should be displayed above the hearing notes

  @Regression
  Scenario: 3 - Hide Send outcome to admin button for a future hearing
    When I select the "Bath Law Courts" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default

    Given I create a future PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234000299     |
      | defendantName   | John Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | John           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-19     |
      | crn             | B290499        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |
    When I open a defendant with a hearing scheduled in the future
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    Then the "Send outcome to admin" button should not be displayed
    And I should not be able to submit an outcome for the hearing



