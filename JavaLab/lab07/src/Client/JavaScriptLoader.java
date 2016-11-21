package Client;

import java.util.*;

import static Intefrace.PawnMXBean.magnes;
import static Intefrace.PawnMXBean.square;
import static Intefrace.PawnMXBean.straight;

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
        loadedScripts.put("Magnes",magnes);
        loadedScripts.put("Square",square);
        loadedScripts.put("Straight",straight);
    }


    public void placePawn(List<Field> fields, Field field, String script) {

//        Pawn pawn = new Pawn();
//        pawn.loadPawn(this.getClass().getResource("").getPath()+ "../resources/"+loadedScripts.get(script));
//        listFromListField(pawn.placePawn(10,tableFromListField(fields,10),field.getX(),field.getY()),fields,10);
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
