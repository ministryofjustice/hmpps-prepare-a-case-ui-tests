@api @ui @pcfs-383
Feature: Identify Possible MAPPA hearings in PACFS search results

  As a CA in court
  I want to easily identify cases that could be MAPPA in search results
  So that I can monitor the case and its progress through court

  Background:
    Given I have a valid court case service authentication token

  Scenario: 1 - Display a Possible MAPPA badge in search results
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234568111     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123000        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |
    When I open the hearing in PACFS
    When I search for the defendant "IbTT Automated"
    Then the search result should have a light yellow background
    And the defendant should have a "POSSIBLE MAPPA" badge displayed in the Defendant column
    And the "POSSIBLE MAPPA" badge should be displayed in red


  Scenario: 2 - Display Possible MAPPA badge below the CRN
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234568111     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123000        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |
    When I open the hearing in PACFS
    When I search for the defendant "IbTT Automated"
    Then the "POSSIBLE MAPPA" badge should be displayed below the CRN


  Scenario: 3 - Display MAPPA and SFO badges together
    Given I create a PACFS case with the following details:
      | clean           | true           |
      | caseNo          | 1234568111     |
      | defendantName   | IbTT Automated |
      | sex             | MALE           |
      | title           | Mr             |
      | forename1       | IbTT           |
      | surname         | Automated      |
      | line1           | 90 Example St  |
      | dateOfBirth     | 1980-04-11     |
      | crn             | B123000        |
      | probationStatus | CURRENT        |
      | awaitingPsr     | true           |
      | breach          | false          |
      | offenceCode     | AT01002        |
      | day             | TODAY          |
    When I open the hearing in PACFS
    When I search for the defendant "IbTT Automated"
    Then the "POSSIBLE MAPPA" badge should be displayed
    And the "POSSIBLE SFO" badge should be displayed
    And the "POSSIBLE MAPPA" badge should appear above the "POSSIBLE SFO" badge