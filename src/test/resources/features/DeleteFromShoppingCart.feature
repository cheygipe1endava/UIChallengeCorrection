Feature: Delete from the shopping cart

  Background: The user signs in Falabella's web page and searches for a product
    Given the user logs in Falabella's web page
      | email    | testdummy4785692@gmail.com |
      | password | sD4eMa9TKtsFdJGs           |

  Scenario: The user needs to delete a product from the shopping cart
  Given the user clicks on shopping bag icon with at least "1" product on it
  When the user click to delete the product from the shopping bag
  Then the web page displays message "tu bolsa de compras está vacía" to user saying the cart is empty