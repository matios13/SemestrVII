package Model;

/**
 * Created by pas113 on 2016-11-24.
 */
public class Call implements Comparable<Call>{
    private int time;

    public Call(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Call o) {
        return Integer.compare(this.time,o.getTime());
    }
}
