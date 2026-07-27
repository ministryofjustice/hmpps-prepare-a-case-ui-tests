@PCFS-97 @Regression
Feature: Selected court visibility across PACFS

  As a PACFS user
  I want to clearly see which court I am working in
  So that I do not accidentally work in the wrong court context.

  Scenario: 1- Display selected court clearly on Cases page
    Given I am working within the PACFS service
    When the Cases page loads
    Then the currently selected court should be clearly visible above the Cases heading
    And the court name should be displayed within the user's main view area

  Scenario: 2- Display selected court clearly on Outcomes page
    Given I am working within the PACFS service
    When the Outcomes page loads
    Then the currently selected court should be clearly visible above the Outcomes heading
    And the court name should remain visible on the page

#  Scenario: 3- Maintain selected court visibility when navigating between screens
#    Given I am working within a PACFS journey
#    And a court has been selected
#    When I navigate between PACFS screens
#    Then the selected court should remain visible
#    And the displayed court should remain unchanged

#  Scenario: 4- Display selected court according to the approved design
#    Given I am viewing a PACFS page
#    When the page loads
#    Then the selected court should be positioned above the Cases and Outcomes sections
#    And the court placement should follow the approved UCD design

#  Scenario: 5- Display newly selected court after switching court
#    Given I am working within a PACFS journey
#    And a court is currently selected
#    When I switch to a different court
#    Then the newly selected court should be displayed
#    And the updated court should remain visible across the PACFS journey

#  Scenario: 6- Prevent incorrect court context during navigation
#    Given I have selected a court within PACFS
#    When I navigate through different PACFS pages
#    Then all displayed information should relate to the selected court
#    And the selected court name should remain visible