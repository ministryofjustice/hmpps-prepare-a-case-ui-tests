@ui @hearingNotes @hearingOutcome @todaysHearing @PCFS-12
Feature: Today's hearing identification and outcoming

  As a Court Duty Officer (CDO)
  I want to easily identify the hearing taking place today
  So that I can capture hearing notes and outcomes against the correct hearing


  Background:
    Given I am logged into the application
    And I am on the "My courts" page
    When I select the "Oxford and Southern Oxfordshire Magistrates' Court" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default


  Scenario: 1- Terminology updated for today's hearing
    Given I open a defendant with a hearing scheduled for today
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When the hearing details are displayed
    Then the "Today's hearing" tag should be displayed in the upper right corner
    And the tag should be displayed as a green label

  Scenario: 2- Outcoming a hearing that takes place today
    Given I open a defendant with a hearing scheduled for today
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When the hearing details are displayed
    Then the "Today's hearing" tag should be displayed in the upper right corner
    And the "Send outcome to admin" button should be displayed
    And the button should be displayed below the hearing details
    And the button should be displayed above the hearing notes

  Scenario: 3- Outcoming a hearing that took place in the past
    Given I open a defendant with a hearing scheduled in the past
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When the hearing details are displayed
    Then the "Send outcome to admin" button should be displayed
    And the button should be displayed below the hearing details
#    And the button should be displayed above the hearing notes
    And the previous date button should be displayed above the hearing notes

  Scenario: 4- Hearing note prompt updated for today's hearing
    Given I open a defendant with a hearing scheduled for today
    And I navigate to the Case Summary page
    Then the hearing note prompt should display "Add a note for today's hearing"

  Scenario: 5- Today's hearing tag remains visible after sending an outcome
    Given I open a defendant with a hearing scheduled for today
    And I navigate to the Case Summary page
    And a hearing note exists for the hearing
    When I send an outcome to admin
    Then the outcome should be displayed on a green background
    And the "Today's hearing" tag should be displayed in the upper right corner