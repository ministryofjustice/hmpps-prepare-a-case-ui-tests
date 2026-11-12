@futureHearing @ui @PCFS-11
Feature: Future hearing identification and restrictions

  As a Court Duty Officer (CDO)
  I want to easily identify future hearings when viewing case progress
  So that I do not mistakenly add notes or outcome a hearing that has not yet taken place

  Background:
    Given I am logged into the application
    And I am on the "My courts" page
    When I select the "Bath Law Courts" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default

    Given I create a future PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234000299     |
      | defendantName   | Alex Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | Alex           |
      | surname         | Automated      |
      | line1           | 56 Example St  |
      | dateOfBirth     | 1976-09-19     |
      | crn             | B295348        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |


  Scenario: 1- Viewing a case with a future hearing
    Given I open a defendant with a hearing scheduled in the future
    And I navigate to the Case Summary page
    When the hearing details are displayed
    Then the "Future hearing" tag should be displayed in the upper right corner
    And the tag should be displayed as a blue label

  Scenario: 2- Future hearing tag remains visible when notes are added
    Given I open a defendant with a hearing scheduled in the future
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When the hearing details are displayed
    Then the "Future hearing" tag should be displayed in the upper right corner
    And the tag should be displayed as a blue label

  Scenario: 3- Future hearing notes section
    Given I open a defendant with a hearing scheduled in the future
    And I navigate to the Case Summary page
    When the hearing details are displayed
    Then the hearing note prompt should display "Add a note for this future hearing"

  Scenario: 4- Future hearing cannot be outcomed
    Given I open a defendant with a hearing scheduled in the future
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When the hearing details are displayed
    Then the "Send outcome to admin" button should not be displayed
    And I should not be able to submit an outcome for the hearing