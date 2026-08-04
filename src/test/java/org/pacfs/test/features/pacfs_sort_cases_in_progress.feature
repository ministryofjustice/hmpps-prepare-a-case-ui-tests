#Feature: Sort Cases in the In Progress Outcomes Table
#
#  As a Case Admin
#  I want to sort cases in the In Progress table
#  So that I can quickly locate cases of interest and perform my duties efficiently.
#
#  Background:
#    Given the Case Admin is logged into the application
#    And the Case Admin has navigated to the Outcomes flow
#    And the In Progress tab is displayed
#    And the In Progress table contains multiple cases
#
#    # -------------------------------------------------------------
#  # Default Behaviour
#  # -------------------------------------------------------------
#
#  Scenario: Verify default sorting is by oldest hearing date
#    Given the In Progress page has loaded
#    When the In Progress table is displayed
#    Then the cases should be sorted by Hearing Date from oldest to newest
#    And the Hearing Date sort indicator should display the default state
#
#
#  # -------------------------------------------------------------
#  # Defendant Sorting
#  # -------------------------------------------------------------
#
#  Scenario: Sort Defendant Last Name from A to Z
#    Given the In Progress page has loaded
#    When the Case Admin clicks the Defendant sorting arrow
#    Then the cases should be sorted alphabetically by Defendant Last Name from A to Z
#    And the Defendant sort arrow should point down
#    And the Hearing Date sorting should be reset
#
#
#  Scenario: Sort Defendant Last Name from Z to A
#    Given the Defendant column is sorted from A to Z
#    When the Case Admin clicks the Defendant sorting arrow again
#    Then the cases should be sorted alphabetically by Defendant Last Name from Z to A
#    And the Defendant sort arrow should point up
#
#
#  Scenario: Switching from Defendant sorting to Probation Status resets Defendant sorting
#    Given the Defendant column is sorted from A to Z
#    When the Case Admin clicks the Probation Status sorting arrow
#    Then the Defendant sorting should be reset
#    And the Probation Status sorting should become active
#
#
#  Scenario: Defendant sorting handles duplicate surnames
#    Given multiple defendants have the same last name
#    When the Case Admin sorts by Defendant Last Name
#    Then all duplicate surnames should be grouped correctly
#    And no cases should be missing
#
#
#  Scenario: Defendant sorting handles special characters
#    Given defendant names contain apostrophes, hyphens or accented characters
#    When the Case Admin sorts the Defendant column
#    Then the sorting should follow the agreed alphabetical ordering
#
#
#  Scenario: Defendant sorting handles mixed case letters
#    Given defendant surnames contain upper and lower case letters
#    When the Case Admin sorts by Defendant Last Name
#    Then the sorting should be case insensitive
#
#
#  # -------------------------------------------------------------
#  # Probation Status Sorting
#  # -------------------------------------------------------------
#
#  Scenario: Sort Probation Status ascending
#    Given the In Progress page has loaded
#    When the Case Admin clicks the Probation Status sorting arrow
#    Then the cases should be sorted in the following order
#      | Current |
#      | Previously known |
#      | No record |
#    And the Probation Status sorting arrow should point down
#    And the Hearing Date sorting should be reset
#
#
#  Scenario: Sort Probation Status descending
#    Given the Probation Status column is sorted ascending
#    When the Case Admin clicks the Probation Status sorting arrow again
#    Then the cases should be sorted in the following order
#      | No record |
#      | Previously known |
#      | Current |
#    And the Probation Status sorting arrow should point up
#
#
#  Scenario: Switching from Probation Status sorting to Defendant sorting resets previous sorting
#    Given the Probation Status column is sorted
#    When the Case Admin sorts by Defendant Last Name
#    Then the Probation Status sorting should be reset
#    And the Defendant sorting should become active
#
#
#  # -------------------------------------------------------------
#  # Pagination
#  # -------------------------------------------------------------
#
#  Scenario: Defendant sorting persists across pagination
#    Given the Defendant column is sorted from A to Z
#    When the Case Admin navigates to the next page
#    Then the Defendant sorting should remain applied
#    And all pages should remain sorted correctly
#
#
#  Scenario: Probation Status sorting persists across pagination
#    Given the Probation Status column is sorted ascending
#    When the Case Admin navigates through multiple pages
#    Then the Probation Status sorting should remain applied
#
#
#  Scenario: Sorting remains correct after returning to a previous page
#    Given the Defendant column is sorted
#    When the Case Admin navigates forward and then back through pagination
#    Then the sorting order should remain unchanged
#
#
#  # -------------------------------------------------------------
#  # Existing Behaviour Validation
#  # -------------------------------------------------------------
#
#  Scenario: Sorting resets after resulting a case
#    Given the Hearing Date is sorted
#    When the Case Admin results a case using the green Result button
#    Then the Hearing Date sorting should reset
#
#
#  Scenario: Sorting resets after resulting from Case Details
#    Given the Hearing Date is sorted
#    When the Case Admin opens a case and completes Result actions
#    Then the Hearing Date sorting should reset
#
#
#  Scenario: Sorting remains after opening and returning without assignment
#    Given the Hearing Date is sorted
#    When the Case Admin opens a case
#    And returns without assigning the case
#    Then the Hearing Date sorting should remain
#
#
#  Scenario: Sorting remains after assigning case to self
#    Given the Hearing Date is sorted
#    When the Case Admin assigns the case to themselves
#    Then the Hearing Date sorting should remain
#
#
#  # -------------------------------------------------------------
#  # Negative Scenarios
#  # -------------------------------------------------------------
#
#  Scenario: Sorting when only one case exists
#    Given only one case exists in the In Progress table
#    When the Case Admin selects any sorting option
#    Then no errors should occur
#    And the single case should remain displayed
#
#
#  Scenario: Sorting when no cases exist
#    Given there are no cases in the In Progress table
#    When the Case Admin selects a sorting option
#    Then no application error should occur
#    And the empty state should remain displayed
#
#
#  Scenario: Sorting when all values are identical
#    Given every case has the same Defendant surname
#    When the Case Admin sorts by Defendant
#    Then the table should remain stable
#    And no records should disappear
#
#
#  Scenario: Sorting after system refresh
#    Given the Defendant column has been sorted
#    When the page is refreshed
#    Then the default sorting behaviour should be applied
#
#
#  # -------------------------------------------------------------
#  # Accessibility
#  # -------------------------------------------------------------
#
#  Scenario: Sorting can be activated using the keyboard
#    Given the In Progress table is displayed
#    When the Case Admin tabs to a sorting control
#    And presses Enter
#    Then the selected column should be sorted
#
#
#  Scenario: Sorting controls expose accessible labels
#    Given the In Progress table is displayed
#    Then each sortable column should expose an accessible label
#    And the current sorting direction should be announced to assistive technology
#
#
#  # -------------------------------------------------------------
#  # Regression
#  # -------------------------------------------------------------
#
#  Scenario: Sorting does not affect filtering
#    Given filters have been applied
#    When the Case Admin sorts by Defendant
#    Then the applied filters should remain unchanged
#
#
#  Scenario: Sorting does not affect case actions
#    Given the table is sorted
#    When the Case Admin opens a case
#    Then all case actions should continue working correctly
#
#
#  Scenario: Sorting does not affect pagination controls
#    Given the table contains multiple pages
#    When sorting is applied
#    Then pagination controls should continue working correctly
#
#
#  # -------------------------------------------------------------
#  # Cross Browser
#  # -------------------------------------------------------------
#
#  Scenario Outline: Sorting works across supported browsers
#    Given the Case Admin accesses the application using "<browser>"
#    When the Case Admin sorts by Defendant Last Name
#    Then the cases should be sorted correctly
#    And the sort arrow should display the correct direction
#
#    Examples:
#      | browser |
#      | Chrome  |
#      | Edge    |
#      | Firefox |
#      | Safari  |
#
