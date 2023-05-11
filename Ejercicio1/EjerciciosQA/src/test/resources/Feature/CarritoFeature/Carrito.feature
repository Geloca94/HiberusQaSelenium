@InventorySuite
Feature: Iventory Test Suite

  Background: Do Login
    Given the user is on home page
    And the user provide the username "standar_user" and password "secret_sauce"
    And the user clicks the login button

 @RemoveProductCart
    Given the user is on the home page https://www.saucedemo.com/inventory.html
    And the user locates  product
    And the user clicks on the add to cart button of x product
    And the user clicks on the cart button
    When the user clicks on the Remove button of x products
    Then the user verifies that the products no longer appear in the cart