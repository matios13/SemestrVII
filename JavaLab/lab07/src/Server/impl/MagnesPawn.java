package Server.impl;

import Client.Field;
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
        System.out.println("Step 1" + counter);
        counter = (int) Math.sqrt(counter);
        System.out.println("Step 2" + counter);
        counter++;
        System.out.println("Step 3" + counter);
        counter/=2;
        System.out.println("Step 4" + counter);
        createWall(fields,x,y);
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
    private void createWall (int[][] column,int x,int y){
        for (int i = 0; i < column.length; i++) {
            for (int j = 0; j < column[i].length; j++) {
                if(i>x-counter&&i<x+counter&&
                        j>y-counter&&j<y+counter){
                    column[i][j]= FieldEnum.BLOCKED.getNumber();
                }
            }
        }

    }
}
