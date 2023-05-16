@InventorySuite
Feature: Inventory Test Suite

  Background: Do Login
    Given the user is on home page
    And the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button

  @Logout
  Scenario: Logout
    Given the user is in page inventory
    When the user clicks on the react-burger-menu-btn and clicks on the Logout button
    Then the user verifies that they are on the home page