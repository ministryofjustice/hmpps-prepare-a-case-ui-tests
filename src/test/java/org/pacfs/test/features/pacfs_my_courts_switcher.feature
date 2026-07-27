@PCFS-98 @Regression
Feature: My Courts switcher

  As a PACFS user
  I want to access My Courts from its new location
  So that I can switch courts without losing functionality.

  Scenario: 1- Switch court from the Cases page
    Given I am on the Cases page
    When I open the "My courts" switcher
    Then the My Courts component should be displayed in the new location
    And I should be able to select another court
    And the selected court should become my active court

  Scenario: 2- Selected court is applied across the service
    Given I have switched to another court using My Courts
    When I navigate to another PACFS page
    Then the selected court should remain active

  Scenario: 3- Switch court from the Outcomes page
    Given I am on the Outcomes page
    Then the My Courts component should be displayed in the outcome location
    And I navigate to Cases tab
    And I should be able to select another new court
    And the selected new court should become my active court

#  Scenario: 4- Switch court from the Search Results page
#    Given I am on the Search Results page
#    When I open the "My Courts" switcher
#    Then the My Courts component should be displayed in the new location
#    And the design should match the approved UCD design
#    And I should be able to select another court
#    And the selected court should become my active court

  Scenario: 5- Switch court from the Case Summary page
    Given I am on the Cases page
    Given I am on the Case Summary page
    Then the My Courts component should be displayed in the new location
    And the selected court in outcome page should become my active court

#  Scenario: 6- Navigate to My Courts
#    Given I am using PACFS
#    When I select "My Courts"
#    Then the My Courts page should open
#    And my available courts should be displayed

#  Scenario: 7- Display assigned courts only
#    Given I have access to multiple courts
#    When I open the My Courts switcher
#    Then only the courts assigned to me should be displayed

#  Scenario Outline: 8- Switch between available courts
#    Given I am using PACFS
#    When I switch to "<Court>"
#    Then "<Court>" should become the active court
#    Examples:
#      | Court |
#      | Court A |
#      | Court B |
#      | Court C |

#  Scenario: 9- Selected court persists across pages
#    Given I have selected another court
#    When I navigate through PACFS
#    Then the selected court should remain active
#    And all pages should display information for the selected court

#  Scenario: 10- Verify PACFS navigation after relocating My Courts
#    Given I am logged into PACFS
#    When I navigate through the application
#    Then all pages should load successfully
#    And My Courts should remain accessible from its new location
#    And no existing functionality should be affected