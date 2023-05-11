@InventorySuite
Feature: Iventory Test Suite

  Background: Do Login
    Given the user is on home page
    And  the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button

  @VerifyProducts
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    When the inventory page is displayed
    Then the user checks that 6 products are displayed in the inventory.


  @Productexists
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    And the user locates the product "Sauce Labs Bolt T-shirt"
    Then the user checks that the product is in the inventory.

  @addProduc
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    And the user locates the product "Sauce Labs Bolt T-shirt"
    When the user clicks on the "add to cart" button
    Then the user checks that the product is in the cart.

  @RemoveProduct
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    And the user locates the product "Sauce Labs Bolt T-shirt"
    And the user clicks on the "add to cart" button
    And the user checks that the product is in the cart
    When the user clicks on the "remove" button
    Then the user checks that the product is no longer in the cart.

  @Add'x'Products
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    And the user locates 'x' product
    When the user clicks on the "add to cart" button of 'x' product
    Then the user checks that the cart icon displays a number equivalent to the 'x' selected products.

  @SortZ-AProducts
  Scenario Outline:: Sort Products
    Given the user is on the home page "https://www.saucedemo.com/inventory.html"
    And the user locates the 'product_sort_container'
    When the user selects the 'Z-A' 'lohi' 'hilo' 'A-Z' option

    Examples:
      | sort |
      | A-Z  |
      | Z-A  |
      | lohi |
      | hilo |