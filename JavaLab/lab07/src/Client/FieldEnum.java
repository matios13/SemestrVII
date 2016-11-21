package Client;

/**
 * Created by pas114 on 2016-11-03.
 */
public enum FieldEnum {
    FREE(0), BLOCKED(1), TAKEN(2), COVERED(3);

    int number;
    public int getNumber(){
        return number;
    }
    FieldEnum(int number) {
        this.number = number;
    }
    public static FieldEnum getById(int id) {
        for(FieldEnum e : values()) {
            if(e.number == id) return e;
        }
        return null;
    }

}
