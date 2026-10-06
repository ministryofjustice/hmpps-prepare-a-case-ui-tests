@api @ui @pcfs-378
Feature: Display updated tags in the PACFS case list

  As a PACFS user
  I want to clearly distinguish between the various flags shown in the case lists
  So that I can take the necessary actions for any of them

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Display PSR as a blue tag
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234568001     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123501        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | day             | TODAY          |

    When I open the hearing in PACFS
    Then the PSR flag should be displayed as a blue tag

  Scenario: 2 - Display Breach as a purple tag
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234568002     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123502        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | true           |
      | day             | TODAY          |

    When I open the hearing in PACFS
    Then the Breach flag should be displayed as a purple tag

#  Scenario: 3 - Display Possible nDelius Record as a red tag
#    Given I create a PACFS case with the following details:
#      | clean           | true           |
#      | caseNo          | 1034568003     |
#      | defendantName   | IbTT Automated |
#      | sex             | MALE           |
#      | title           | Mr             |
#      | forename1       | IbTT           |
#      | surname         | Automated      |
#      | line1           | 90 Example St  |
#      | dateOfBirth     | 1980-04-11     |
#      | crn             | B123509        |
#      | probationStatus | Possible nDelius Record|
#      | awaitingPsr     | false          |
#      | breach          | false          |
#      | day             | TODAY          |
#
#    When I open the hearing in PACFS
#    Then the Possible nDelius Record flag should be displayed as a red tag