/**
 * Created by pas114 on 2016-10-20.
 */
public class Test2 {
    private int test;
    private String wynik;
    private int a;
    public double b;

    public String calculate(int a,int b){
        int w=a*b;
        wynik=""+w;
        return wynik;
    }
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getWynik() {
        return wynik;
    }

    public void setWynik(String wynik) {
        this.wynik = wynik;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
