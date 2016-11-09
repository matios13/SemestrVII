package game;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by pas114 on 2016-11-03.
 */
public class JavaScriptLoader {
    private Map<String,Invocable> loadedScripts;

    public List<String> getLoadedScriptsNames(){
        Set<String> keys = loadedScripts.keySet();
        return new ArrayList<>(keys);
    }

    public JavaScriptLoader() {
        loadScripts();
    }

    private void loadScripts(){
        loadedScripts = new HashMap<>();
        loadedScripts.put("plus",createInvocableFromScript("plus"));
        loadedScripts.put("topSquare",createInvocableFromScript("topSquare"));
    }
    private Invocable createInvocableFromScript(String name){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader(new File(this.getClass().getResource("").getPath()+ "/../resources/"+name+".js")));

            Invocable invocable = (Invocable) engine;
            return invocable;
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void placePawn(List<Field> fields, Field field,String script){
        try {

            Invocable invocable = loadedScripts.get(script);
            Object result = invocable.invokeFunction("placePawn",fields,field);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
