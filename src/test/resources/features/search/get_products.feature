Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

  Background:
    Given Taras can call the API

  Scenario Outline: positive

    When he searches for an <keyword>
    Then he sees the results displayed for an <keyword>

    Examples:
      | keyword |
      | "orange"|
      | "apple" |

  Scenario Outline: negative

    When he searches for an <keyword>
    Then he does not see the results

    Examples:
      | keyword |
      | "car"   |
      | "bike"  |