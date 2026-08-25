@Regression
Feature: Sort Cases in Cases to Result table under Outcomes flow

  As a Case Admin
  I want to sort cases in the Cases to Result table
  So that I can find cases of interest more quickly and perform my duties efficiently.


  Background:
    Given I am logged in as a Case Admin
    And I navigate to the Cases to Result page under the Outcomes flow
    And the Cases to Result table has loaded successfully


  @Positive @DefaultSorting
  Scenario: 1- Verify default sorting of Cases to Result page by oldest hearing date
    Given the Cases to Result page has loaded
    When the results are displayed
    Then the cases should be sorted by Hearing date with the oldest hearing first
#    And the Hearing date sorting indicator should point down
    And the column heading should display "Hearing date (oldest)"


  @Positive @HearingDateSorting
  Scenario: 2- Sort cases by most recent hearing date
    Given the Cases to Result page has loaded
    And cases are sorted by Hearing date oldest first
    When I click the sorting arrow on the Hearing date column
    Then the cases should be sorted by the most recent hearing date first
    And the oldest hearings should appear at the bottom of the list
#    And the Hearing date sorting indicator should point up
    And the column heading should display "Hearing date (newest)"


  @Positive @DefendantSorting
  Scenario: 3- Sort cases by Defendant last name from A to Z
    Given the Cases to Result page has loaded
    When I click the sorting arrow on the Defendant column
    Then the cases should be sorted alphabetically from A to Z using the defendant last name
#    And the Defendant sorting indicator should point up
    And the column heading should display "Defendant (A-Z)"


  @Positive @DefendantSorting
  Scenario: 4- Sort cases by Defendant last name from Z to A
    Given cases are currently sorted by Defendant last name from A to Z
    When I click the sorting arrow on the Defendant column
    Then the cases should be sorted alphabetically from Z to A using the defendant last name
#    And the Defendant sorting indicator should point down
    And the column heading should display "Defendant (Z-A)"


  @Positive @ProbationStatusSorting
  Scenario: 5- Sort cases by Probation status using preferred order
    Given the Cases to Result page has loaded
    When I click the sorting arrow on the Probation Status column
    Then the cases should be sorted in the following order:
      | Probation Status |
      | Current          |
      | Pre-sentence record |
      | No record        |
#    And the Probation Status sorting indicator should point up
    And the column heading should display "Probation status"


  @Positive @ProbationStatusSorting
  Scenario: 6- Sort cases by Probation status in reverse preferred order
    Given cases are currently sorted by Probation Status in the following order:
      | Probation Status |
      | Current          |
      | Pre-sentence record |
      | No record        |
    When I click the sorting arrow on the Probation Status column
    Then the cases should be sorted in the following reverse order:
#      | Probation Status |
      | No record        |
      | Pre-sentence record |
      | Current          |
#    And the Probation Status sorting indicator should point down
    And the column heading should display "Probation status"


  @Positive @Pagination
  Scenario: 7- Verify sorting persists across paginated results
    Given I have sorted the cases by Defendant from A to Z
    When I navigate back to the previous page
#    When I navigate to the next page of results
    Then the Defendant sorting order should remain A to Z
    When I navigate back to the previous page
    When I navigate to the next page of results
    Then the Defendant sorting order should remain A to Z


  @Positive @NavigationPersistence
  Scenario: 8- Verify sorting persists when opening a case and returning to results
    Given I have sorted the cases by Hearing date newest first
    When I open a case from the results list
    And I return to the Cases to Result page
    Then the Hearing date sorting should remain as newest first


  @Positive @AssignmentPersistence
  Scenario: 9- Verify sorting is retained when assigning a case from within a case
    Given I have sorted the cases by Defendant A-Z
    When I open a case from the results list
#    And I assign the case to myself using the "Assign to me" button
    And I return to the Cases to Result page
    Then the Defendant sorting should remain A-Z


#  @Positive @AssignmentPersistence
#  Scenario: 10- Verify sorting is retained when assigning a case using checkbox action
#    Given I have sorted the cases by Probation Status
#    When I select a case using the checkbox
#    And I assign the selected case to myself using the Action button
#    Then I should remain on the Cases to Result page
#    And the Probation Status sorting should remain applied


#  @Negative @SortingReset
#  Scenario: Verify sorting does not apply multiple column sorting
#    Given the cases are sorted by Defendant A-Z
#    When I click the sorting arrow on the Hearing date column
#    Then the cases should only be sorted by Hearing date
#    And the Defendant sorting should no longer be applied


#  @Negative @SortingReset
#  Scenario: Verify previous column sorting is cleared when another column is selected
#    Given the cases are sorted by Hearing date newest first
#    When I sort the cases by Defendant A-Z
#    Then only the Defendant column should show an active sorting indicator
#    And the Hearing date sorting indicator should return to the default state


#  @Negative @DataValidation
#  Scenario: Verify sorting does not modify case data
#    Given the Cases to Result page contains cases
#    When I apply sorting on any available column
#    Then no case information should be changed
#    And no cases should be added or removed


#  @EdgeCase @DuplicateValues
#  Scenario: Verify sorting handles cases with duplicate defendant names
#    Given multiple cases exist with the same defendant last name
#    When I sort the cases by Defendant A-Z
#    Then all matching defendant names should be displayed together
#    And the sorting should complete successfully


#  @EdgeCase @EmptyValues
#  Scenario: Verify sorting handles cases with missing probation status
#    Given cases exist with no probation status value
#    When I sort the cases by Probation Status
#    Then cases without probation status should appear according to the defined sorting rule
#    And no error message should be displayed


#  @EdgeCase @LargeDataSet
#  Scenario: Verify sorting performance with a large number of cases
#    Given the Cases to Result table contains a large number of cases
#    When I apply sorting on Hearing date
#    Then the results should be sorted successfully
#    And the page should remain responsive


#  @Regression @ExistingFunctionality
#  Scenario: Verify existing hearing date sorting functionality is not impacted
#    Given the Cases to Result page is loaded
#    When I toggle the Hearing date sorting option
#    Then the sorting should alternate between oldest and newest hearing dates
#    And the correct sorting indicator should be displayed