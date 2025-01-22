Feature: Admin can add an employee to the HRMS system

  Background:
    Given User is on the HRM login page
    When User enters valid credentials
    Then User should be logged in successfully

    @First
 Scenario: Admin adds an employee without an employee ID
    Given The admin is on the Add Employee page
    When The admin enters valid details without an employee ID
    Then The system generates a unique employee ID
    And The employee is successfully added

    @Second
  Scenario: Admin adds an employee with a unique employee ID
    Given The admin is on the Add Employee page
    When The admin enters valid details with an employee ID
    Then The employee is successfully added with the provided employee ID

    @Third
  Scenario: Admin submits incomplete or invalid employee details
    Given The admin is on the Add Employee page
    When The admin enters invalid or incomplete details
    Then The system shows appropriate error messages near the input fields