@usps
  Feature: Usps feature

    @usps1
    Scenario Outline: Usps Stamps & Boxes
      Given I navigate to "usps" page
      When I go to usps "<store>" store
      And I sort usps results by "Price (Low-High)"
      Then I verify that usps "<item>" is cheapest
      Examples:
        | store  | item          |
        | stamps | Tiffany Lamp  |
        | boxes  | Priority Mail |

    @usps2
    Scenario: Click-N-Ship
      Given I navigate to "usps" page
      When I go to "Click-N-Ship" under "Mail & Ship" menu
      Then I verify that "Sign In" is required
      Then I verify that "Sign Up Now" is possible

      @usps3
      Scenario: Schedule PickUp
        Given I navigate to "usps" page
        When I mouseover "Mail & Ship"
        And I go to "Schedule a Pickup"
        And I fill out the form "step 1"
        And I verify Service Availability
        And I click on "No" radio-button
        And I select "Front Door"
        And I enter "text" in additional instructions
        And I choose regular time
        And I choose a day
        And I choose "1" number of packages
        And I enter "45" pounds of total weight
        And I check terms and Conditions
        Then I verify that pickup is scheduled
        Then I verify that all data entered is correct

