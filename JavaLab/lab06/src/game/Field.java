package game;

/**
 * Created by pas114 on 2016-11-03.
 */
public class Field {

    private int x;
    private int y;
    private FieldEnum fieldEnum;

    public Field(int x, int y, FieldEnum fieldEnum) {
        this.x = x;
        this.y = y;
        this.fieldEnum = fieldEnum;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public FieldEnum getFieldEnum() {
        return fieldEnum;
    }

    public void setFieldEnum(FieldEnum fieldEnum) {
        this.fieldEnum = fieldEnum;
    }

    @Override
    public String toString() {
        return "game.Field{" +
                "x=" + x +
                ", y=" + y +
                ", fieldEnum=" + fieldEnum +
                '}';
    }
}
