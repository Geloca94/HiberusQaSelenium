@InventorySuite
Feature: Inventory Test Suite

  Background: Do Login
    Given the user is on home page
    And the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button


  @RemoveProductCart
  Scenario: Remove Product to cart
    Given the user is on the home page
    And the user clicks on the add to cart
    And the user see if the number cart are correct
    And the user clicks on the cart button
    When the user clicks on the Remove button of x products
    Then the user verifies that the products no longer appear in the cart

