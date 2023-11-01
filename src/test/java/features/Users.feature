Feature: Validating User API

  @abc
  Scenario: Get a list of users
    Given the JSONPlaceholder API is available
    When I send a GET request to "/users"
    Then the response status code should be 200
    And the response should contain a list of users
    And "[0].name" in response body is "Leanne Graham"

  @abc1
  Scenario: Get a list of users and verify the name
    Given the JSONPlaceholder API is available
    When I send a GET request to "/users"
    Then the response status code should be 200
    And the response should contain a list of users
    And "[0].name" in response body is "Leanne Graham"

  @abc2
  Scenario: Get a user by ID
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain user details for ID 1

  @abc3
  Scenario: Get a non-existent user
    When I send a GET request to "/users/1000"
    Then the response status code should be 404
    And the response should contain empty data

  @abc4
  Scenario: Create a new user
    When I send a POST request to "/users" with JSON body:
    """
    {
      "name": "John Doe",
      "email": "johndoe@example.com",
      "username": "johndoe123",
      "id":"11"
    }
    """
    Then the response status code should be 201
    And the response should contain the created user details
    #When I send a GET request to "/users/11"
    #And the response should contain user details for ID 11

  @abc5
  Scenario: Update an existing user
    When I send a PUT request to "/users/2" with JSON:
    """
    {
      "name": "Updating Name"
    }
    """
    Then the response status code should be 200
    And the response should contain the updated user details

  Scenario: Update a non-existent user
    When I send a PUT request to "/users/1000" with JSON:
    """
    {
      "name": "Updating Name"
    }
    """
    Then the response status code should be 404
    And the response should contain empty data

  Scenario: Delete a user
    When I send a DELETE request to "/users/1"
    Then the response status code should be 200
    And the response should indicate successful deletion

  Scenario: Delete a non-existent user
    When I send a DELETE request to "/users/1000"
    Then the response status code should be 404
    And the response should contain empty data



