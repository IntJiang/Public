package utility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class API {

    public Response sendRequest(Method method, Map<String, String> headers, String body, String URI, String path, Object... pathParams) {
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        if(headers != null) {
            for (String key : headers.keySet()) {
                httpRequest.header(key, headers.get(key));
            }
        }
        httpRequest.body(body);
        return httpRequest.request(method, path, pathParams);
    }

}
