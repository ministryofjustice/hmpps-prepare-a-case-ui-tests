@api @ui @pcfs-375
Feature: Identify potential MAPPA cases based on CJS offence codes

  As a CA in court
  I want PACFS to identify offences that could potentially lead to a case being MAPPA
  So that I can be alerted when a possible MAPPA case is scheduled for a hearing

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1- Highlight a hearing when an offence matches a MAPPA CJS offence code
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
    Then the hearing should be identified as a potential MAPPA case


#  Scenario: 2- Highlight a hearing when an offence matches a MAPPA CJS offence code
#  Given I create PACFS cases for all MAPPA CJS offence codes
#
#  Scenario: 3- Do not highlight a hearing when none of the offences match a MAPPA CJS offence code
#    Given the MAPPA CJS offence code list is configured in the PACFS backend
#    And the hearing contains offences with CJS codes that are not included in the MAPPA offence code list
#    Then the hearing should not be identified as a potential MAPPA case