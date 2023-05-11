@LoginSuite
Feature: Login test suite

  @LoginOk
  Scenario Outline: Verify valid user can login
    Given the user is on home page
    And  the user provide the username "<username>" and password "<password>"
    When the user clicks the login button
    Then the user is logged successfully and is into the inventory page

    Examples:
      | username     | password     |
      | standard_user | secret_sauce |

  @LoginKO
  Scenario Outline: Verify invalid user cannot login
    Given the user is on home page
    And the user provide the username "<username>" and password "<password>"
    When the user clicks the login button
    Then the user should id be shown and invalid message

    Examples:
      | username     | password     |
      | bad_user     | secret_sauce |
      | xxx          | xxx          |