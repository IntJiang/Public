package utility;

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
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
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
}
