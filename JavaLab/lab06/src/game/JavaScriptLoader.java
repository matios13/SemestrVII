package game;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pas114 on 2016-11-03.
 */
public class JavaScriptLoader {
    private Map<String, String> loadedScripts;

    public List<String> getLoadedScriptsNames() {
        Set<String> keys = loadedScripts.keySet();
        return new ArrayList<>(keys);
    }

    public JavaScriptLoader() {
        loadScripts();
    }

    private void loadScripts() {
        loadedScripts = new HashMap<>();
        loadedScripts.put("mor","game_Pawn.dll");
        loadedScripts.put("kwadrat","squarePawn.dll");
    }


    public void placePawn(List<Field> fields, Field field, String script) {
        //URL url = new File(this.getClass().getResource("").getPath()+ "../resources/game_Pawn.dll").toURL();
        //System.load(this.getClass().getResource("").getPath()+ "../resources/game_Pawn.dll");
        /*try {
            CustomClassLoader loader = new CustomClassLoader();
            Class ca = loader.findClass(Pawn.class.getName());
            Pawn pawn = (Pawn)ca.newInstance();
            pawn.loadPawn(this.getClass().getResource("").getPath() + "../resources/" + loadedScripts.get(script));
            listFromListField(pawn.placePawn(10, tableFromListField(fields, 10), field.getX(), field.getY()), fields, 10);
            ca=null;
            pawn=null;
            loader=null;
            System.gc();
        }catch (Exception e){

        }*/
        Pawn pawn = new Pawn();
        pawn.loadPawn(this.getClass().getResource("").getPath()+ "../resources/"+loadedScripts.get(script));
        listFromListField(pawn.placePawn(10,tableFromListField(fields,10),field.getX(),field.getY()),fields,10);
    }
    public int[][] tableFromListField(List<Field> fields, int size) {
        int[][] grid = new int[size][size];
        fields.stream().forEach(f->grid[f.getX()][f.getY()]=f.getFieldEnum().number);
        return grid;
    }

    public void listFromListField(int[][]grid,List<Field> fieldsList, int size) {
        fieldsList.stream().forEach(f->f.setFieldEnum(FieldEnum.getById( grid[f.getX()][f.getY()])));
    }



}
