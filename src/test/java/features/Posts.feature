Feature: Validating Posts API

  Scenario: Get a list of posts
    Given the JSONPlaceholder API is available
    When I send a GET request to "/posts"
    Then the response status code should be 200
    And the response should contain a list of posts
    And "[0].title" in response body is "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"


  Scenario: Get a list of posts and verify the title of 1st item
    Given the JSONPlaceholder API is available
    When I send a GET request to "/posts"
    Then the response status code should be 200
    And the response should contain a list of posts
    And "[1].title" in response body is "qui est esse"


  Scenario: Get a post by ID and verify the title
    When I send a GET request to "/posts/3"
    Then the response status code should be 200
    And the response should contain post details for ID 3
    And "title" in response body is "ea molestias quasi exercitationem repellat qui ipsa sit aut"



  Scenario: Get a non-existent post
    When I send a GET request to "/posts/120"
    Then the response status code should be 404
    And the response should contain empty data


  Scenario: Create a new post
    When I send a POST request to "/posts" with JSON body:
    """
    {
      "userId": 1,
      "title":"test post",
      "body":"testing test post"
    }
    """
    Then the response status code should be 201
    And the response should contain the created post details
    #When I send a GET request to "/posts/11"
    #And the response should contain post details for ID 11


  Scenario: Create a new post with incorrect data and verify the response
    When I send a POST request to "/posts" with JSON body:
    """
    {
      "userId": 1,
      "title":"test post",
      "body""testing test post"
    }
    """
    Then the response status code should be 500


  Scenario: Update an existing post
    When I send a PUT request to "/posts/2" with JSON:
    """
    {
      "title": "Updating title"
    }
    """
    Then the response status code should be 200
    And the response should contain the updated post details

  Scenario: Update a non-existent post
    When I send a PUT request to "/posts/102" with JSON:
    """
    {
      "userId":1,
      "title": "Updating title"
    }
    """
    Then the response status code should be 404
    And the response should contain empty data

  Scenario: Delete a post
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200
    And the response should indicate successful deletion

  Scenario: Delete a non-existent post
    When I send a DELETE request to "/posts/1000"
    Then the response status code should be 404
    And the response should contain empty data



