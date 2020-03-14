Feature: Logout

  Background: The uer is logged in
    Given The user is in Falabella's home page
    And Opens login form overlay
    When The user types in valid credentials
    And Clicks login button
    Then User should be signed in

  Scenario: The user successfully signs out of Falabella's web page
    Given The user is currently logged in Falabella's home page
    When The user hover on account div
    And Clicks logout option
    Then The user should be signed out