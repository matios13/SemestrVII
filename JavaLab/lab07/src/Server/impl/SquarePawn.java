package Server.impl;

import Client.FieldEnum;
import Intefrace.PawnMXBean;

/**
 * Created by matio_000 on 21.11.2016.
 */
public class SquarePawn implements PawnMXBean {
    @Override
    public String describe() {
        return "tworzy kwadrat 4x4";
    }

    @Override
    public int[][] placePawn(int[][] fields, int x, int y) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if( ((i >= x && i < x+4) &&
                        j >= y && j < y+4)
                        &&fields[i][j] == FieldEnum.FREE.getNumber()){
                    fields[i][j] = FieldEnum.COVERED.getNumber();
                }
            }
        }
        return fields;
    }

}
