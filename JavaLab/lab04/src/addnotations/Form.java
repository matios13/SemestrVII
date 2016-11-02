package addnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pas113 on 2016-10-27.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

    String defaultValue() default "";

    String name();

}
