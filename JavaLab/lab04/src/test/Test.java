package test;

import addnotations.Form;

/**
 * Created by pas113 on 2016-10-27.
 */
public class Test {
    @Form(min=1,max = 2,name = "test1" ,defaultValue = "1")
    public int test;
    @Form(min=1,name = "test2")
    public int test2;
    @Form(min=-4,max = 573,name = "test3" ,defaultValue = "1")
    public int test3;
}
