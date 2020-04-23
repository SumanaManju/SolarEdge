Feature: As a user I want to fill in employee details in SolarEdge Application

  @AddEmployee
  Scenario Outline: I am on SolarEdge application and I want to add new employee details

  Given I open the application "<url>" of "<Testcasename>" a
   When I fill in the details
   And I calculate the number and insert right number
   And I click on submit button
   Then I check the success message

  Examples:
  | Testcasename             |url    |
  | TC001_SolarEdge_AddDetails|SiteUrl|
