Feature: Login page features

  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Login - My Shop"

  Scenario: Forget password link
    Given user is on login page
    When user gets the title of the page
    Then forget your password link should be displayed

  Scenario: Login with correct credentials
    Given user is on login page
    When  user enter username "dharma.555dt@gmail.com"
    When  enter password "Teja@1993."
    And   user clicks on login button
    Then user gets the title of the page
    And  page title should be "My account - My Shop"