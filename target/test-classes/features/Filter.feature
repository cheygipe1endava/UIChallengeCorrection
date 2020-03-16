Feature: Filter

  Background: The user signs in Falabella's web page
    Given the user searches for "crema" in search bar

  Scenario: The user needs to filter by object price
    Given the user is in "crema" product page
    When the user clicks price button
    And types prices between "5000" to "20000" in price fields and applies the price filter
    Then the user should only see products with prices "5000" and "20000" in this range