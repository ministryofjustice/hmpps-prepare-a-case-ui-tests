@api @ui @376
Feature: Identify Possible MAPPA cases in the Outcomes flow

  As a CA in court
  I want to easily identify in the Outcomes tables hearings that are linked to a Possible MAPPA case
  So that I can take the appropriate actions as the case progresses through court

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Display Possible MAPPA in the Cases to Result tab
    Given I create a PACFS case with the following details:
      | clean           | true          |
      | caseNo          | 1234512946    |
      | defendantName   | TOM Automated |
      | sex             | MALE          |
      | title           | Mr            |
      | forename1       | TOM           |
      | surname         | Automated     |
      | line1           | 90 Example St |
      | dateOfBirth     | 1980-04-11    |
      | crn             | B123048       |
      | probationStatus | CURRENT       |
      | awaitingPsr     | true          |
      | breach          | false         |
      | offenceCode     | AT01002       |
      | day             | TODAY         |
    When I open the hearing in PACFS
    And I select the defendant "Tom Automated"
    And I expand the hearing note section
    And I add the hearing note "testing"
    And I send the outcome to admin
    And I choose "PROBATION_SENTENCE" from the outcome type list
    And I send the selected outcome to admin
    Then the outcome should be sent successfully
    And I navigate to the Outcomes page
    And I assign the hearing for defendant "Tom Automated" to myself
    Then I should see a successful assignment message for defendant "Tom Automated"
    And I open the In Progress tab
    And I move the hearing for defendant "Tom Automated" to Resulted
    And I open the Resulted Cases tab
    Then the hearing should have a light yellow background in the Cases to Result tab
    And the defendant should have a "POSSIBLE MAPPA" badge displayed in the Defendant column in the Cases to Result tab
    And the "POSSIBLE MAPPA" badge should be displayed in red in the Cases to Result tab


#    And I move the hearing for defendant "Ibtt Automated" to Hearing Outcome Not Required
#    And I open the Outcomes tab

  Scenario: 2 - Carry Possible MAPPA into the In Progress tab
    Given I create a PACFS case with the following details:
      | clean           | true          |
      | caseNo          | 1234512946    |
      | defendantName   | TOM Automated |
      | sex             | MALE          |
      | title           | Mr            |
      | forename1       | TOM           |
      | surname         | Automated     |
      | line1           | 90 Example St |
      | dateOfBirth     | 1980-04-11    |
      | crn             | B123048       |
      | probationStatus | CURRENT       |
      | awaitingPsr     | true          |
      | breach          | false         |
      | offenceCode     | AT01002       |
      | day             | TODAY         |
    When I open the hearing in PACFS
    And I select the defendant "Tom Automated"
    And I expand the hearing note section
    And I add the hearing note "testing"
    And I send the outcome to admin
    And I choose "PROBATION_SENTENCE" from the outcome type list
    And I send the selected outcome to admin
    Then the outcome should be sent successfully
    And I navigate to the Outcomes page
    And I assign the hearing for defendant "Tom Automated" to myself
    Then I should see a successful assignment message for defendant "Tom Automated"
    And I open the In Progress tab
    Then the hearing should be displayed in the In Progress tab
    And the hearing should continue to display the "POSSIBLE MAPPA" badge
    And the hearing should continue to have a light yellow background
