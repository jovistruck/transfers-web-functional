Feature: Search a Journal for a Manuscript

  Scenario: A relevant journal suggestion is provided for a manuscript
    When I search for a manuscript
    Then the results displayed contain a journal matching the manuscript
#
  Scenario: Journal suggestions have the correct journal link, impact factor, access type and select links
    When I search for a manuscript
    Then the following columns have the correct data
      | journal_link  |
      | impact_factor |
      | open_access   |
    And the select link is correct