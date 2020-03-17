Feature: Login

  Scenario: The user unsuccessfully signs in Falabella's web page
    Given the user opens login form overlay from Falabella's home page
    When the user types in its credentials for username and password
      | email    | testdummy@gmail.com |
      | password | 1234                |
    And clicks login button
    Then the page shows error with email/password to the user

  Scenario: The user successfully signs in Falabella's web page
    Given the user opens login form overlay from Falabella's home page
    When the user types in its credentials for username and password
      | email    | testdummy4785692@gmail.com |
      | password | sD4eMa9TKtsFdJGs           |
    And clicks login button
    Then the user should be signed in
    And the user closes the session

