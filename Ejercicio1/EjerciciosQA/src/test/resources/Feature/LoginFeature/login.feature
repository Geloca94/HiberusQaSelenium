@LoginSuite
Feature: Login test suite

  @LoginOk
  Scenario Outline: Verify valid user can login
    Given the user is on home page
    And  the user provide the username "username" and password "password"
    When the user clicks the login button
    Then the user is logged successfully and is into the inventory page

    Examples:
      | username     | paswword     |
      | standar_user | secret_sauce |

  @LoginKO
  Scenario Outline: Verify invalid user cannot login
    Given the user is on home page
    And the user provide the username "username" and password "password"
    When the user clicks the login button
    Then the user is logged successfully and is into the inventory page

    Examples:
      | username     | paswword     |
      | bad_user     | secret_sauce |
      | xxx          | xxx          |