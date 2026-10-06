@api @ui @pcfs-377
Feature: Display Possible SFO badges

  As a CA or CDO
  I want to know when an offence qualifies a case as a Possible SFO
  So that I can carry out the necessary checks and raise it with the SFO team

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Display Possible SFO badge in the case list
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234567991     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123491        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |

    When I open the hearing in PACFS
    Then the hearing should have a light yellow background
    And the defendant should have a "POSSIBLE SFO" badge displayed in the Defendant column
    And the "POSSIBLE SFO" badge should be displayed in purple

  Scenario: 2 - Display Possible SFO badge on the case details page
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234567992     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123492        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |

    When I open the hearing in PACFS
    And I select the defendant "Ibtt Automated"
    Then the "POSSIBLE SFO" badge should be displayed on the case details page
    And the "POSSIBLE SFO" badge on the case details page should be displayed in purple