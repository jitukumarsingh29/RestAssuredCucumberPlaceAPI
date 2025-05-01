Feature: Validate Place API

  Scenario: Add, Update, Verify and Delete a place
    Given Add Place Payload
    When User calls "AddPlaceAPI" with "POST" http request
    Then API call is successful with status code 200
    And "place_id" is extracted from response

    When User calls "UpdatePlaceAPI" with "PUT" http request
    Then API call is successful with status code 200

    When User calls "GetPlaceAPI" with "GET" http request
    Then "address" in response is "New Walk, Africa"

    When User calls "DeletePlaceAPI" with "DELETE" http request
    Then API call is successful with status code 200
