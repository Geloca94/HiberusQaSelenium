@InventorySuite
Feature: Iventory Test Suite

  Background: Do Login
    Given the user is on home page
    And the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button

  @CheckOutFinalPriceVerification
  Scenario Outline:: Check Out Final Price Verification
    Given the user is in page inventory
    And the user clicks on the add to cart button of product
    And the user clicks on the cart button
    And the user clicks on the CheckOut button
    And the user provides the "<FirstName>" , "<LastName>" ..., "<ZipCode>" ..
    When the user clicks on the Continue button
    Then the user verifies that the total price of x products equals the sum of their prices

    Examples:
      | FirstName | LastName | ZipCode |
      | 1         | 1        | 1       |
      | 2         | 2        | 2       |
      | 3         | 3        | 3       |

  @PlaceOrder
  Scenario Outline::Place Order
    Given the user is in page inventory
    And the user clicks on the add to cart button of product
    And the user clicks on the cart button
    And the user clicks on the CheckOut button
    And the user provides the "<FirstName>" , "<LastName>" ..., "<ZipCode>" ..
    When the user clicks on the Continue button
    When the user clicks on the Finish button
    Then the user verifies that a text Thank you for your order! and a BackHome button are displayed

    Examples:
      | FirstName | LastName | ZipCode |
      | 1         | 1        | 1       |
      | 2         | 2        | 2       |
      | 3         | 3        | 3       |