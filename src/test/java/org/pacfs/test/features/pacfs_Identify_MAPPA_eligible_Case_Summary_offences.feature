@api @ui @pcfs-375
Feature: Display Possible MAPPA badge on case details page

  As a CA in court
  I want to see the Possible MAPPA badge on the case details page
  So that I can confirm I am viewing a case that could be MAPPA

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Display Possible MAPPA badge on the case details page
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234667999     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B223499        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |

    When I open the hearing in PACFS
    And I select the defendant "Ibtt Automated"
    Then the "POSSIBLE MAPPA" badge should be displayed on the case details page
    And the "POSSIBLE MAPPA" badge on the case details page should be displayed in red
    Then the "POSSIBLE MAPPA" badge should be displayed before the "POSSIBLE SFO" badge