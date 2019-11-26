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

