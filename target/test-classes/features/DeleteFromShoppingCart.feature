Feature: Delete from the shopping cart

  Background: The user signs in Falabella's web page and searches for a product
    Given the user logs in Falabella's web page
      | email    | testdummy4785692@gmail.com |
      | password | sD4eMa9TKtsFdJGs           |
    And the user searches for "historia" in search bar and selects first match

  Scenario: The user needs to delete a product from the shopping cart
  Given the user has a product on its shopping cart
  When the user tries to delete the product
  Then the web page displays message to user saying the cart is empty