@PCFS-105 @Regression @ui
Feature: User Guide accessibility from PACFS

  As a PACFS user
  I want the User Guide to be accessible from a dedicated location
  So that I can easily find guidance when using PACFS.

  Scenario: 1- User Guide is displayed in the new location on Cases page
    Given I am viewing the PACFS Cases page
    When the page is rendered
    Then the User Guide link should be displayed in the approved location outside the footer

  Scenario: 2- User Guide is displayed in the new location on Outcomes page
    Given I am viewing the PACFS Outcomes page
    When the Outcomes page is rendered
    Then the User Guide link should be displayed in the approved location outside the footer

  Scenario: 3- Open User Guide in a new browser tab
    Given I am viewing a PACFS page containing the User Guide link
    When I select the User Guide link
    Then the User Guide should open in a new browser tab
    And the original PACFS page should remain open in the previous tab

  Scenario: 4- Navigate to the correct User Guide location
    Given I am viewing the User Guide link in PACFS
    When I select the User Guide link
    Then I should be redirected to the approved User Guide location
    And the User Guide content should be displayed successfully "https://justiceuk.sharepoint.com/sites/HMPPS_Group_CSA/SitePages/HomeFeed.aspx"

  Scenario: 5- User Guide is not available from PACFS footer
    Given I am viewing the PACFS Cases page
    When I scroll to the footer
    Then the User Guide link should not be displayed in the footer

#  Scenario: 6- User Guide remains accessible across PACFS pages
#    Given I am navigating through PACFS
#    When I move between supported pages
#    Then the User Guide link should remain available from its new location
#    And the User Guide should not appear in the footer