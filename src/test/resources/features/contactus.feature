Feature: Contact us feature

  Scenario Outline: contact us scenario with different set data
    Given user navigate to contact us page
    When user fills the form from the given "<SheetName>" and <RowNumber>
    And user clicks on send button
    Then it shows successful message "Your message has been successfully sent to our team."
    Examples:
      | SheetName  | RowNumber |
      | automation | 0         |
      | automation | 1         |
