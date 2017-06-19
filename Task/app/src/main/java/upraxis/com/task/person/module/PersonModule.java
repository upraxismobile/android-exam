package upraxis.com.task.person.module;

import dagger.Module;
import dagger.Provides;
import upraxis.com.task.di.scope.ForActivity;
import upraxis.com.task.person.contract.PersonContract;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Module
public class PersonModule {

    PersonContract.View view;

    public PersonModule(PersonContract.View view) {
        this.view = view;
    }

    @Provides
    @ForActivity
    PersonContract.View provideView() {
        return view;
    }
}
