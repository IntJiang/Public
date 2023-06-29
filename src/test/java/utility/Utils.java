package utility;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utility.Objects.User;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    /**
     * @param jsCode String type of javaScript code, start with function ***(){...}
     * @param function Name of your javaScript function
     * @param args Parameters which need to be involved into jsCode
     * @return
     */
    public Object executeJS(String jsCode, String function, Object... args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        Object result = null;
        try {
            engine.eval(jsCode);
            Invocable invocable = (Invocable) engine;
            result = invocable.invokeFunction(function, args);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;

    }

    public void unzip(String source, String destination, char... password){
        try {
            ZipFile zipFile = new ZipFile(source);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    public JSONObject readJson(String path){
        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            return (JSONObject) obj;
        }catch (IOException | ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, User> loadUser(){
        JSONObject obj = readJson(System.getProperty("user.dir") + "/src/test/resources/accounts.json");
        Map<String, User> u = new HashMap<>();
        for(Object key : obj.keySet()){
            String name = (String) key;
            JSONObject data = (JSONObject) obj.get(name);
            String email = (String) data.get("email");
            String password = (String) data.get("password");

            User user = new User(name, email, password);
            u.put(name, user);
        }
        return u;
    }
}
