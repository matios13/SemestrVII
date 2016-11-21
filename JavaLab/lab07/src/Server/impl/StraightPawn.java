package Server.impl;

import Client.FieldEnum;
import Intefrace.PawnMXBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matio_000 on 21.11.2016.
 */
public class StraightPawn implements PawnMXBean {
    @Override
    public String describe() {
        return "tworzy Linię";
    }

    @Override
    public int[][] placePawn(int[][] fields, int x, int y) {
        List<Integer> fieldsToCover = new ArrayList<>();
        List<Integer> fieldsToCover2 = new ArrayList<>();

        for (int j = 0; j < fields[x].length; j++) {
            if(j>y){
                if(fields[x][j]==FieldEnum.BLOCKED.getNumber())
                    break;
                else
                    fieldsToCover2.add(j);
            }else{
                fieldsToCover.add(j);
                if (fields[x][j]==FieldEnum.BLOCKED.getNumber())
                    fieldsToCover.clear();
            }
        }
        fieldsToCover.stream().forEach(i->fields[x][i]=FieldEnum.COVERED.getNumber());
        fieldsToCover2.stream().forEach(i->fields[x][i]=FieldEnum.COVERED.getNumber());
        return fields;
    }
}
