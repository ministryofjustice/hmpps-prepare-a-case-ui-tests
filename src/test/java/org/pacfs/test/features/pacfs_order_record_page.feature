#@PCFS-332 @Regression
#Feature: Display defendant attendance and absence totals correctly on order record pages
#
#  Scenario: 1- Attendance and absence totals are displayed inline on the current order record page
#    Given I am on the probation record page for a defendant with a current order
#    And the defendant has attendance and absence records
#    When I select "View record" for the defendant's current order
#    Then the total number of attendances should be displayed inline with the attendance label
#    And the total number of absences should be displayed inline with the absence label
#    And the attendance and absence totals should not be right-aligned on the page
#    And the spacing between the attendance and absence information should be consistent
#    And the attendance and absence information should be clearly aligned and readable
#
#
#  Scenario: 2- Attendance and absence totals are not displayed incorrectly on the order record page
#    Given I am on the order record page for a defendant with attendance and absence records
#    When the attendance and absence information is displayed
#    Then the attendance total should not appear separated from its label
#    And the absence total should not appear separated from its label
#    And neither total should appear justified to the far right of the page
#    And there should be no inconsistent or excessive line spacing between the labels and totals