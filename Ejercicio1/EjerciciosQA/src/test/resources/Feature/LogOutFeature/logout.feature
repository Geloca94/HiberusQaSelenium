@InventorySuite
Feature: Iventory Test Suite

  Background: Do Login
    Given the user is on home page
    And the user provide the username "standar_user" and password "secret_sauce"
    And the user clicks the login button

  @Logout
    Given the user is on the homepage https://www.saucedemo.com/inventory.html
    And the user clicks on the react-burger-menu-btn
    When the user clicks on the Logout button
    Then the user verifies that they are on the page https://www.saucedemo.com