package utility;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
}
