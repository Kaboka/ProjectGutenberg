Feature: Get the authors and books that mentions a city
  Return the authors and books

  Scenario: Invalid string US1, AC2
    Given A a city name is "@ustralia"
    When I type in the city name
    Then I should get 0 results
    And I should get error message "Could not find any results"

  Scenario: Correct city name, without mentions US1, AC4
    Given A a city name is "Karlslunde"
    When I type in the city name
    Then I should get 0 results
    And I should get error message "Could not find any results"

