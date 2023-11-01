Feature: Validating comments API

  @abc
  Scenario: Get a list of comments
    When I send a GET request to "/comments"
    Then the response status code should be 200
    And the response should contain a list of comments
    And "[0].name" in response body is "id labore ex et quam laborum"

  @abc1
  Scenario: Get a list of comments and verify the email
    When I send a GET request to "/comments"
    Then the response status code should be 200
    And the response should contain a list of comments
    And "[0].email" in response body is "Eliseo@gardner.biz"

  @abc2
  Scenario: Get a comment by ID
    When I send a GET request to "/comments/3"
    Then the response status code should be 200
    And the response should contain comment details for ID 3

  @abc3
  Scenario: Get a non-existent comment
    When I send a GET request to "/comments/510"
    Then the response status code should be 404
    And the response should contain empty data

  @abc4
  Scenario: Create a new comment
    When I send a POST request to "/comments" with JSON body:
    """
    {
      "postId": 510,
      "name": "test",
      "email": "test@gardner.biz",
      "body": "testing test"
    }
    """
    Then the response status code should be 201
    And the response should contain the created comment details
    #When I send a GET request to "/comments/11"
    #And the response should contain comment details for ID 11

  @abc99
  Scenario: send incorrect body in post and verify the status code
    When I send a POST request to "/comments" with JSON body:
    """
    {
      "commentId": 510,
      "name" "test",
      "email": "test@gardner.biz",
      "body": "testing test"
    }
    """
    Then the response status code should be 500


  @abc5
  Scenario: Update an existing comment
    When I send a PUT request to "/comments/2" with JSON:
    """
    {
      "title": "Updating title"
    }
    """
    Then the response status code should be 200
    And the response should contain the updated comment details

  @abc8
  Scenario: Update a non-existent comment
    When I send a PUT request to "/comments/600" with JSON:
    """
    {
     "postId": 100,
     "id": 600,
     "name": "test",
    }
    """
    Then the response status code should be 500
    And the response should contain empty data

  Scenario: Delete a comment
    When I send a DELETE request to "/comments/1"
    Then the response status code should be 200
    And the response should indicate successful deletion

  @abc41
  Scenario Outline: Create a new multiple comments
    When I send a POST request to "/comments" with multiple JSON body:
      | postId   | name   | email   | body   |
      | <postId> | <name> | <email> | <body> |
    Then the response status code should be 201
    And the response should contain the comment details
      | postId   | name   | email   | body   | id   |
      | <postId> | <name> | <email> | <body> | <id> |

    Examples:
      | postId | name | email            | body         | id  |
      | 510    | test | test@gardner.biz | testing test | 501 |




