Feature: Session stability and navigation reliability

  @SessionTimeout
  Scenario: Verify session stability when navigating to Outcomes flow
    Given the user is logged into the application via "Guildford Magistrates' Court"
    When the user navigates to the Outcomes flow multiple times
    Then the user should remain logged in
    And the system should not display "There is a problem with this service"
    And the session should remain active without unexpected logout

  @SessionTimeout
  Scenario: Verify court change functionality via My Courts
    Given I am logged into the application
    When the user navigates to "My courts"
    And the user changes the court multiple times consecutively
    Then the court should update successfully
    And no service error should be displayed
