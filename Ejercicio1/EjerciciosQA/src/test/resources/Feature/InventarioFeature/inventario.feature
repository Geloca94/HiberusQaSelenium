@InventorySuite
Feature: Inventory Test Suite

  Background: Do Login
    Given the user is on home page
    And  the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button

  @VerifyProducts
  Scenario Outline: VerifyProducts
    Given the user is in page inventory
    When the inventory page is displayed
    Then the user checks that "<item>" products are displayed in the inventory.

    Examples:
    |item|
    |6   |

  @Productexists
  Scenario Outline: Product Exists
    Given the user is in page inventory
    When the inventory page is display
    Then the user checks that the "<product>" is in the inventory.

    Examples:
      | product                   |
      | Sauce Labs Bolt T-Shirt   |


  @addProduct
  Scenario: add Product
    Given the user is in page inventory
    When the user clicks on the add to cart button for the product Sauce Labs Bolt T-shirt
    Then the user checks that the product is in the cart.

  @RemoveProduct
  Scenario: RemoveProduct
    Given the user is in page inventory
    And the user clicks on the add to cart button
    And the user checks thats the product is in the cart
    When the user clicks on the remove button
    Then the user checks that the product is no longer in the cart.

  @AddxProducts
  Scenario: Add x Products
    Given the user is in page inventory
    When the user clicks on the add to cart button of x product
    Then the user checks that the cart icon displays a number equivalent to the x selected products.

  @SortZ-AProducts
  Scenario: Sort Products ZA
    Given the user is in page inventory
    When the user locates the product_sort_container
    Then the user selects the ZA option

