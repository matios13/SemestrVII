package Intefrace;

import java.util.List;

/**
 * Created by matio_000 on 21.11.2016.
 */
public interface PawnMXBean {
    public static final String magnes = "example:type=MagnesPawn";
    public static String square = "example:type=SquarePawn";
    public static String straight = "example:type=StraightPawn";
    public String describe();
    public int[][] placePawn(int[][] fields, int x, int y);
}
