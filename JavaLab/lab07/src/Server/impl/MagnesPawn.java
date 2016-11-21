package Server.impl;

import Client.FieldEnum;
import Intefrace.PawnMXBean;

import java.util.Arrays;

/**
 * Created by matio_000 on 21.11.2016.
 */
public class MagnesPawn implements PawnMXBean {
    private int counter = 0;
    @Override
    public String describe() {
        return "Przyciaga do siebie wszystkie zblokowane i tworzy kwadrat";
    }

    @Override
    public int[][] placePawn(int[][] fields, int x, int y) {
        Arrays.stream(fields).forEach(c->replaceBlocked(c));

        return fields;
    }
    private void replaceBlocked(int[] column){
        for (int i = 0; i < column.length; i++) {
            if(column[i]==FieldEnum.BLOCKED.getNumber()){
                counter++;
                column[i]=FieldEnum.COVERED.getNumber();
            }
        }
    }
}
