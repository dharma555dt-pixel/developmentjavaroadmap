Feature: Account Page Feature

  Background:
    Given user has already logged in to the application
      | username               | password   |
      | dharma.555dt@gmail.com | Teja@1993. |

  Scenario: Accounts page title
    Given user is on account page
    When user gets the title of the page
    Then page title should be "My account - My Shop"

  Scenario: Accounts section count
    Given user is on account page
    Then user get account section
      | Add my first address      |
      | Order history and details |
      | My credit slips           |
      | My addresses              |
      | My personal information   |
    And account section count should be 5