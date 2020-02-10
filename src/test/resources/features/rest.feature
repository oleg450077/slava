@rest
Feature: CRUD operations for Careers

  @rest1
  Scenario: REST API Positions CRUD
    Given I login via REST as "recruiter"
    When I create via REST "automation" position
    Then I verify via REST new position is in the list
    When I update via REST "automation" position
    Then I verify via REST new position is updated
    When I delete via REST new position
    Then I verify via REST new position is deleted

  @rest2
  Scenario: REST API Candidates CRUD
    Given I login via REST as "recruiter"
    When I create via REST "sdet" candidate
    And I add via REST "pdf" resume to a new candidate
    Then I verify via REST that "pdf" resume has been added
    Then I verify via REST new candidate is in the list
    When I update via REST "sdet" candidate
    Then I verify via REST new candidate is updated
    When I delete via REST new candidate
    Then I verify via REST new candidate is deleted

  @rest3
  Scenario: REST API Applications
    Given I login via REST as "recruiter"
    When I create via REST "automation" position
    And I create via REST "sdet" candidate
    And I add via REST "pdf" resume to a new candidate
    Then I verify via REST that "pdf" resume has been added


    @rest4
    Scenario: New Candidate applies for first position with REST
      Given I navigate via REST to positions
      And I apply via REST the first position
      And I fill out via REST "candidate" form and submit
      Then I verify via REST login
      Then I verify via REST position in the in my jobs
      When I delete via REST new application in my jobs
      Then I verify via REST new application is deleted from my jobs
