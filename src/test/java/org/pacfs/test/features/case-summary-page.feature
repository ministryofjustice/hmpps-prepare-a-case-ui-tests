Feature: Case Summary Page - Defendant details, case actions and updates

  Background:
    Given I am logged into the application
    And I am on the "My courts" page
    When I select the "Oxford and Southern Oxfordshire Magistrates' Court" court
    Then I should be taken to the selected court page
    And the "Cases" tab should be selected by default
    And I navigate to "Hearing outcome still to be added" tab

  Scenario: View and validate defendant details on Case Summary page
    When I select a defendant from the case list
    Then I should be navigated to the Case Summary page
    And the defendant name should match the value from the cases page
#    And the PNC number should match the value from the cases page
    And the probation status should match the value from the cases page

  Scenario: Upload documents to a case from Case Summary page
    Given I am on the Case Summary page
#    When I upload a document to the case
#    Then the document should be successfully attached to the case
#
#  Scenario: Add hearing note under Case Progress
#    Given I am on the Case Summary page
#    When I expand the Case Progress section
#    And I add a hearing note with case-specific information
#    Then the hearing note should be saved successfully
#
#  Scenario: Add comments under Case Summary page
#    Given I am on the Case Summary page
#    When I add a comment with notes and observations about the case
#    Then the comment should be saved and visible to colleagues
#    And the comment should not be saved to NDelius
