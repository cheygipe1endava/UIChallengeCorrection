Feature: Registration

  Scenario: The user unsuccessfully register in Falabella's web page
    Given the user is in Falabella's home page
    And goes to the registration page
    When the user fills all fields excepting cellphone
    And tries to save its data
    Then web page displays error message to the user

