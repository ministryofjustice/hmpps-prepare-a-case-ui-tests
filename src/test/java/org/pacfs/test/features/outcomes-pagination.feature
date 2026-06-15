Feature: Validate Outcomes page pagination, sorting and data consistency

  Background:
    Given I am logged into the application
    And I am on the "My courts" page
#    When I select the "Guildford Magistrates' Court" court
    When I select the "Oxford and Southern Oxfordshire Magistrates' Court" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default

  @Regression @Outcomes @test
  Scenario: Outcomes page does not display duplicate cases
    Given I navigate to the "Outcomes" tab
    Then I verify that defendant names across all pagination pages in the Outcomes tab are unique and consistently ordered

  @Regression @Outcomes
  Scenario: Assign a defendant case and move it to resulted cases
    Given I navigate to the "Outcomes" tab
    When I select the defendant name from the case results
    And I assign the case to myself using the "Actions" button
    Then I should see the success message "You are assigned to result <selectedDefendantName>. Their case has moved to the in progress tab."
    And the defendant name should no longer appear in the Outcomes case results list

    When I navigate to the "In progress" tab
    Then I should see the defendant name in the in progress cases list

    When I select the "Move to result" action for defendant
    Then I should see the success message "You have moved <selectedDefendantName> case to resulted cases."

    When I navigate to the "Resulted cases" tab
    Then I should see the defendant name in the resulted cases list



#  @Regression @Outcomes
#  Scenario: Outcomes page displays consistent results across pagination
#    When I capture the list of cases on page 1
#    And I navigate to page 2 in the Outcomes tab
#    Then no case from page 1 should be repeated on page 2
#    And the order of cases should remain consistent across pages
#
#  @Regression @Outcomes
#  Scenario: Outcomes page does not miss any cases
#    Given the auto-outcome batch job has completed in pre-prod
#    And I am on the "Cases" tab
#    When I capture all available cases from the Cases tab
#    And I navigate to the "Outcomes" tab
#    Then all corresponding cases from the Cases tab should exist in the Outcomes tab
#    And no cases should be missing
#
#  @Regression @Outcomes
#  Scenario: Outcomes results are consistently sorted using a unique tie-breaker
#    Given I navigate to the "Outcomes" tab
#    And multiple cases have the same hearing date
#    When I capture the displayed outcomes records
#    Then the cases should be sorted by hearing date
#    And a unique identifier should be used as a secondary sort
#    And the order of cases should remain consistent after multiple page refreshes
#
#  @Regression @Outcomes
#  Scenario: Outcomes results remain stable after page refresh
#    Given I navigate to the "Outcomes" tab
#    When I refresh the page multiple times
#    Then the same cases should appear in the same order
#    And no duplicates or missing cases should occur
