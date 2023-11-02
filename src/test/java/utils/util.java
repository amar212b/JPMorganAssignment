package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepdefinitions.CommentApiSteps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class util {
    private static final Logger logger = LoggerFactory.getLogger(CommentApiSteps.class);

    public static String getBaseURLValue(String key) throws IOException {
        logger.info("Get BaseURL from properties file");
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
