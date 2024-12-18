Feature: Calculate Ticket Price

  Scenario: Calculate ticket price for a regular member on a weekday
    Given the user selects "Monday" as the day
    And the user selects "正常時段" as the time
    And the user inputs "30" as the age
    And the user is a member with the ID "IECS-12345"
    When the user clicks the calculate button
    Then the ticket price should be "100.00"

  Scenario: Calculate ticket price for a non-member under age 12 on a weekend
    Given the user selects "Saturday" as the day
    And the user selects "早場：早上七點以前" as the time
    And the user inputs "10" as the age
    And the user is not a member
    When the user clicks the calculate button
    Then the ticket price should be "160.00"

  Scenario: Validate member ID format
    Given the user selects "Monday" as the day
    And the user selects "正常時段" as the time
    And the user inputs "30" as the age
    And the user is a member with the ID "12345"
    When the user clicks the calculate button
    Then an error message "會員編號必須以 IECS- 開頭。" should be displayed
