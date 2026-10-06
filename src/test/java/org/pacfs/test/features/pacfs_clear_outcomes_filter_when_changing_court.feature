@ui
Feature: Clear Outcomes filters when changing court

#  Background:
#    Given I am logged into PACFS
#    And I have access to multiple courts
#    And I am on the Outcomes page
#
#  Scenario: Clear an applied filter when changing court
#    Given I have selected "Court A"
#    And I have applied a filter on the Outcomes list
#    And the Outcomes list is displaying filtered results
#    When I change the selected court from "Court A" to "Court B"
#    Then the previously applied filter should be cleared
#    And the Outcomes list should display the relevant results for "Court B" without the previous filter being applied
#
#  Scenario: Verify that a filter is not applied in the background after changing court
#    Given I have selected "Court A"
#    And I have applied a filter on the Outcomes list
#    And the filter reduces the number of results displayed
#    When I change the selected court from "Court A" to "Court B"
#    Then the previous filter should no longer affect the Outcomes results
#    And all relevant Outcomes for "Court B" should be displayed
#
#  Scenario: Clear a status or outcome filter when changing court
#    Given I have selected "Court A"
#    And I have applied a status or outcome filter
#    When I change the selected court from "Court A" to "Court B"
#    Then the status or outcome filter should be reset to its default state
#    And the Outcomes list should display the relevant results for "Court B"
#
#  Scenario: Clear a date filter when changing court
#    Given I have selected "Court A"
#    And I have applied a date filter on the Outcomes list
#    When I change the selected court from "Court A" to "Court B"
#    Then the date filter should be cleared
#    And the Outcomes list should display the relevant results for "Court B" without the previous date filter
#
#  Scenario: Changing back to the original court does not retain previous filters
#    Given I have selected "Court A"
#    And I have applied a filter on the Outcomes list
#    When I change the selected court from "Court A" to "Court B"
#    And I change the selected court back to "Court A"
#    Then the previously applied filter should remain cleared
#    And the Outcomes list should display the default results for "Court A"
#
#  Scenario: Clear multiple filters when changing court
#    Given I have selected "Court A"
#    And I have applied multiple filters on the Outcomes list
#    When I change the selected court from "Court A" to "Court B"
#    Then all previously applied filters should be cleared
#    And the Outcomes list should display the relevant unfiltered results for "Court B"