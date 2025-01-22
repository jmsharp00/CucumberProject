Feature: HRM Login Validation
  @Login1
  Scenario: User tries to log in with an empty username
    Given User is on the HRM login page
    When User enters an empty username
    Then User should see the error message "Username cannot be empty"

  @Login2
  Scenario: User tries to log in with an empty password
    Given User is on the HRM login page
    When User enters an empty password
    Then User should see the error message "Password is Empty"

  @Login3
  Scenario: User enters incorrect credentials
    Given User is on the HRM login page
    When User enters incorrect credentials
    Then User should see the error message "Invalid credentials"

  @Login4
  Scenario: User logs in with valid credentials
    Given User is on the HRM login page
    When User enters valid credentials
    Then User should be logged in successfully

