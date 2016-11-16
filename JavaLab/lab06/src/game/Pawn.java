package game;

/**
 * Created by pas114 on 2016-11-03.
 */
public class Pawn {

    public void loadPawn(String dll){
        System.load(dll);
    }
    public native int[][] placePawn(int size, int[][] grid,int x,int y);
    public native String describe();

    public static void main(String[] args) {
    }
}
