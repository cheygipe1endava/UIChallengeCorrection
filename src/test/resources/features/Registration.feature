Feature: Registration

  Scenario: The user unsuccessfully register in Falabella's web page
    Given the user goes to the registration page from Falabella's home page
    When the user fills all fields with corresponding data excepting cellphone
      | name            | MARIA                |
      | firstLastName   | ANDRADE              |
      | secondLastName  | PEREZ                |
      | email           | testdummy@gmail.com  |
      | password        | sD4eMa9TKtsFdJGs     |
      | confirmPassword | sD4eMa9TKtsFdJGs     |
      | country         | Colombia             |
      | documentType    | CEDULA_DE_CIUDADANIA |
      | documentNum     | 1016948235           |
      | day             | 5                    |
      | month           | May                  |
      | year            | 1990                 |
      | cellphone       |                      |
    And tries to save its data
    Then web page displays error message to the user




