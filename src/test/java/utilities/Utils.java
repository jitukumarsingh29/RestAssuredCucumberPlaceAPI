package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

    public RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public String getJsonPath(Response response, String key) {
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.getString(key);
    }

    public String getResource(String resourceName) {
        switch (resourceName) {
            case "AddPlaceAPI":
                return "/maps/api/place/add/json";
            case "UpdatePlaceAPI":
                return "/maps/api/place/update/json";
            case "GetPlaceAPI":
                return "/maps/api/place/get/json";
            case "DeletePlaceAPI":
                return "/maps/api/place/delete/json";
            default:
                throw new RuntimeException("Invalid API resource name: " + resourceName);
        }
    }
}
