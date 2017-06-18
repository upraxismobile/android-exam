package upraxis.com.task.person.component;

import dagger.Component;
import upraxis.com.task.di.component.AppComponent;
import upraxis.com.task.person.view.PersonActivity;
import upraxis.com.task.person.module.PersonModule;
import upraxis.com.task.di.scope.ForActivity;

/**
 * Created by rsbulanon on 6/14/17.
 */
@ForActivity
@Component(dependencies = {AppComponent.class},
    modules = PersonModule.class)
public interface PersonComponent {
    void inject(PersonActivity personActivity);
}
