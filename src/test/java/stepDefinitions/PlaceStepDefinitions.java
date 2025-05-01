package stepDefinitions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.Utils;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class PlaceStepDefinitions extends Utils {

    RequestSpecification req;
    Response response;
    static String place_id;
    String newAddress = "New Walk, Africa";

    @Given("Add Place Payload")
    public void add_place_payload() {
        req = given().spec(requestSpec()).body(Payload.addPlace());
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_api_with_method(String resource, String method) {
        String endpoint = getResource(resource);
        switch (method.toUpperCase()) {
            case "POST":
                response = req.when().post(endpoint);
                break;
            case "PUT":
                response = req.body("{ \"place_id\":\"" + place_id + "\", \"address\":\"" + newAddress + "\", \"key\":\"qaclick123\" }")
                        .when().put(endpoint);
                break;
            case "GET":
                response = given().spec(requestSpec())
                        .queryParam("place_id", place_id)
                        .when().get(endpoint);
                break;
            case "DELETE":
                response = given().spec(requestSpec())
                        .body("{\"place_id\":\"" + place_id + "\"}")
                        .when().delete(endpoint);
                break;
        }
    }

    @Then("API call is successful with status code {int}")
    public void api_call_successful(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("{string} is extracted from response")
    public void extract_place_id(String key) {
        place_id = getJsonPath(response, key);
    }

    @Then("{string} in response is {string}")
    public void verify_field_in_response(String key, String expected) {
        Assert.assertEquals(getJsonPath(response, key), expected);
    }
}
