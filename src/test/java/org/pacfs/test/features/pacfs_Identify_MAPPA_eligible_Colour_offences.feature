@api @ui @pcfs-374
Feature: Identify Possible MAPPA hearings in the PACFS case list

  As a CA in court
  I want to easily identify in the case list hearings that are linked to a possible MAPPA case
  So that I can carry out the necessary checks and transfer full information about the case as it progresses through court

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Identify a hearing as a Possible MAPPA case
    Given I create a PACFS case with the following details:
      | clean             | true          |
      | caseNo            | 1234567888    |
      | defendantName     | IbTT Automated|
      | sex               | MALE          |
      | title             | Mr            |
      | forename1         | IbTT          |
      | surname           | Automated     |
      | line1             | 90 Example St |
      | dateOfBirth       | 1980-04-11    |
      | crn               | B123477       |
      | probationStatus   | CURRENT       |
      | awaitingPsr       | true          |
      | breach            | false         |
      | offenceCode       | AT01002       |
      | day               | TODAY         |
    When I open the hearing in PACFS
    Then the hearing should have a light yellow background
    And the defendant should have a "POSSIBLE MAPPA" badge displayed in the Defendant column
    And the "POSSIBLE MAPPA" badge should be displayed in red
