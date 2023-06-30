package testCases;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.API;

import java.util.List;
import java.util.Map;

public class APITest extends API {
    @Test
    public void apiTestExample(){
        Response response = sendRequest(Method.GET, null, null, "https://demoqa.com", "/BookStore/v1/Books");
        assert response.statusCode() == 200;
        JsonPath body = response.getBody().jsonPath();
        List<Map<String,String>> books = body.get("books");
        assert books.get(0).get("title").equalsIgnoreCase("Git Pocket Guide");
    }
}
