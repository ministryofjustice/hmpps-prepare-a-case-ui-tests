@ui
Feature: Sort Cases in the Resulted Cases Outcomes Table

  As a Case Admin
  I want to sort cases in the Resulted Cases table
  So that I can quickly identify cases of interest and perform my duties efficiently.


  Background:
    Given the Case Admin is logged into the application
    And the Case Admin has navigated to the Outcomes flow
    And the Resulted Cases tab is displayed
    And the Resulted Cases table contains multiple cases


  # -------------------------------------------------------------
  # Default Sorting Behaviour
  # -------------------------------------------------------------

  Scenario: Verify default Resulted Cases ordering is by oldest hearing date
    Given the Resulted Cases page has loaded
    When the Resulted Cases tab is displayed
    Then the cases should be sorted by Hearing Date from oldest to newest
#    And the default Hearing Date sorting indicator should be displayed


  # -------------------------------------------------------------
  # Defendant Sorting
  # -------------------------------------------------------------

  Scenario: Sort Defendant Last Name from A to Z
    Given the Resulted Cases page has loaded
    When the Case Admin clicks the Defendant sorting arrow
    Then the cases should be sorted alphabetically by Defendant Last Name from A to Z
#    And the Defendant sorting arrow should point down
    And the Hearing Date sorting should be reset


  Scenario: Sort Defendant Last Name from Z to A
    Given the Defendant column is sorted from A to Z
#    And the Defendant sorting arrow is pointing down
    When the Case Admin clicks the Defendant sorting arrow again
    Then cases should be sorted alphabetically by Defendant Last Name from Z to A
     Then the cases should be sorted alphabetically from Z to A using the defendant last name
#    And the Defendant sorting arrow should point up


#  Scenario: Switching from Defendant sorting to Probation Status resets Defendant sorting
#    Given the Defendant column is sorted
#    When the Case Admin selects Probation Status sorting
#    Then Defendant sorting should no longer be applied
#    And Probation Status sorting should become active
#
#
#  Scenario: Defendant sorting handles duplicate surnames
#    Given multiple cases have defendants with the same surname
#    When the Case Admin sorts by Defendant Last Name
#    Then duplicate surnames should appear together
#    And no cases should be removed
#
#
#  Scenario: Defendant sorting handles special characters
#    Given defendant names contain apostrophes, hyphens or accented characters
#    When the Case Admin sorts by Defendant Last Name
#    Then sorting should follow the agreed alphabetical ordering
#    And special characters should not incorrectly affect the sort order
#
#
#  Scenario: Defendant sorting handles missing defendant names
#    Given a case exists without a defendant surname
#    When the Case Admin sorts by Defendant Last Name
#    Then the application should handle the missing value correctly
#    And no error should occur
#
#
#  # -------------------------------------------------------------
#  # Probation Status Sorting
#  # -------------------------------------------------------------
#
#  Scenario: Sort Probation Status from Current to No Record
#    Given the Resulted Cases page has loaded
#    When the Case Admin clicks the Probation Status sorting arrow
#    Then cases should be sorted in the following order:
#      | Probation Status |
#      | Current |
#      | Previously known |
#      | No record |
#    And the Probation Status sorting arrow should point down
#    And Hearing Date sorting should be reset
#
#
#  Scenario: Sort Probation Status from No Record to Current
#    Given the Probation Status column is sorted in ascending order
#    And the sorting arrow is pointing down
#    When the Case Admin clicks the Probation Status sorting arrow again
#    Then cases should be sorted in the following order:
#      | Probation Status |
#      | No record |
#      | Previously known |
#      | Current |
#    And the Probation Status sorting arrow should point up
#
#
#  Scenario: Switching from Probation Status sorting to Defendant sorting resets Probation Status sorting
#    Given Probation Status sorting is active
#    When the Case Admin sorts by Defendant Last Name
#    Then Probation Status sorting should be removed
#    And Defendant sorting should become active
#
#
#  Scenario: Probation Status sorting handles cases with missing values
#    Given some cases do not contain a Probation Status value
#    When the Case Admin sorts by Probation Status
#    Then cases without a value should be handled correctly
#    And no sorting errors should occur
#
#
#  # -------------------------------------------------------------
#  # Pagination Behaviour
#  # -------------------------------------------------------------
#
#  Scenario: Defendant sorting persists across all paginated results
#    Given the Defendant column is sorted from A to Z
#    When the Case Admin navigates to the next page
#    Then Defendant sorting should remain applied
#    And all pages should continue displaying cases in A to Z order
#
#
#  Scenario: Probation Status sorting persists across all paginated results
#    Given the Probation Status column is sorted from Current to No Record
#    When the Case Admin navigates through multiple pages
#    Then Probation Status sorting should remain applied
#    And all pages should maintain the selected sorting order
#
#
#  Scenario: Sorting remains after returning to previous pagination page
#    Given the Resulted Cases table has been sorted
#    When the Case Admin navigates to another page and returns
#    Then the selected sorting order should remain unchanged
#
#
#  # -------------------------------------------------------------
#  # Navigation and Persistence Behaviour
#  # -------------------------------------------------------------
#
#  Scenario: Hearing Date sorting resets after navigating through case actions
#    Given the Hearing Date sorting is active
#    When the Case Admin opens a case
#    And assigns the case to themselves
#    And returns to the Outcomes tab
#    Then the Hearing Date sorting should reset according to expected behaviour
#
#
#  Scenario: Sorting remains when returning without completing case actions
#    Given the Resulted Cases table has been sorted
#    When the Case Admin opens a case
#    And returns without making changes
#    Then the previous sorting behaviour should be maintained
#
#
#  Scenario: Sorting resets when selecting a different sorting option
#    Given the Defendant column is sorted
#    When the Case Admin selects Probation Status sorting
#    Then only Probation Status sorting should be active
#    And Defendant sorting should be cleared
#
#
#  # -------------------------------------------------------------
#  # Negative Scenarios
#  # -------------------------------------------------------------
#
#  Scenario: Sorting when no resulted cases exist
#    Given there are no cases in the Resulted Cases table
#    When the Case Admin selects any sorting option
#    Then no application error should occur
#    And the empty state should remain displayed
#
#
#  Scenario: Sorting with only one resulted case
#    Given the Resulted Cases table contains only one case
#    When the Case Admin selects a sorting option
#    Then the case should remain displayed
#    And no error should occur
#
#
#  Scenario: Sorting does not duplicate or remove cases
#    Given the Resulted Cases table contains multiple cases
#    When the Case Admin applies sorting
#    Then the number of displayed cases should remain unchanged
#
#
#  Scenario: Sorting does not impact case details access
#    Given the Resulted Cases table has been sorted
#    When the Case Admin opens a case
#    Then the case details page should load successfully
#
#
#  # -------------------------------------------------------------
#  # Accessibility
#  # -------------------------------------------------------------
#
#  Scenario: Sorting controls are accessible using keyboard navigation
#    Given the Resulted Cases table is displayed
#    When the Case Admin navigates using the keyboard
#    Then sorting controls should receive focus
#    And the Case Admin should be able to activate sorting
#
#
#  Scenario: Sorting direction is available to assistive technology
#    Given the Resulted Cases table is sorted
#    Then the current sorting direction should be announced correctly
#
#
#  # -------------------------------------------------------------
#  # Regression
#  # -------------------------------------------------------------
#
#  Scenario: Existing case actions continue to work after sorting
#    Given the Resulted Cases table has been sorted
#    When the Case Admin performs available case actions
#    Then all actions should continue to work correctly
#
#
#  Scenario: Sorting does not impact pagination controls
#    Given multiple pages of resulted cases exist
#    When sorting is applied
#    Then pagination controls should continue functioning correctly
#
#
#  Scenario Outline: Sorting works across supported browsers
#    Given the Case Admin accesses the application using "<browser>"
#    When the Case Admin sorts the Resulted Cases table
#    Then the sorting order should display correctly
#    And the sort indicator should display correctly
#
#    Examples:
#      | browser |
#      | Chrome  |
#      | Edge    |
#      | Firefox |
#      | Safari  |