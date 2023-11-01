package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class util {

    public static String getBaseURLValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\app.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }
    public String getJsonPath(Response response, String key) {
        //String resp = response.asString();
        JsonPath js = new JsonPath(response.asString());
        //String root = js.get("$").toString();
        return js.get(key).toString();
    }
}
