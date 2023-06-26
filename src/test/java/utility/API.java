package utility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;

public class API {

    public static Response sendRequest(Method method, Map<String, String> headers, String body, String URI, String path, Object... pathParams) {
        RestAssured.baseURI = URI;
        RequestSpecification httpRequest = RestAssured.given();
        if(headers != null) {
            for (String key : headers.keySet()) {
                httpRequest.header(key, headers.get(key));
            }
        }
        if(body != null) {
            httpRequest.body(body);
        }
        return httpRequest.request(method, path, pathParams);
    }

    public static boolean sendAndDownload(String URI, String path, String downloadPath){
        InputStream inputStream = sendRequest(Method.GET, null, null, URI, path).body().asInputStream();
        try {
            FileOutputStream outputStream = new FileOutputStream(downloadPath);
            IOUtils.copy(inputStream, outputStream);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
