package test;

import addnotations.Form;

import java.io.Serializable;

/**
 * Created by pas113 on 2016-10-27.
 */
public class Test2 implements Serializable {

    @Form(min = 1, max = 2, name = "test1", defaultValue = "1")
    public int test;

    @Form(min = 1, name = "test2")
    public boolean test2;

    @Form(min = 0, max = 2, name = "test3", defaultValue = "1")
    public byte test3;

    @Form(max = 5, name = "test4")
    public short test4;

    @Form(min = 0, max = 573, name = "test5", defaultValue = "999")
    public long test5;

    @Form(min = 0, max = 573, name = "test6", defaultValue = "Czesc")
    public String test6;

    @Form(min = 0, max = 1000, name = "test7", defaultValue = "2f")
    public float test7;

    @Form(min = -50, max = 0, name = "test8", defaultValue = "2.5d")
    public double test8;
}
