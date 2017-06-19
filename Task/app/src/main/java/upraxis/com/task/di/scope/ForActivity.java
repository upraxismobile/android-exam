package upraxis.com.task.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {
}
