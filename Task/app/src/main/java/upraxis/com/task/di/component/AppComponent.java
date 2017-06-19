package upraxis.com.task.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import upraxis.com.task.di.module.ApplicationModule;
import upraxis.com.task.di.module.RoomPersistenceModule;
import upraxis.com.task.di.module.RetrofitModule;
import upraxis.com.task.room.AppDatabase;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        RetrofitModule.class,
        RoomPersistenceModule.class
})
public interface AppComponent {
    Application application();
    Retrofit retrofit();
    AppDatabase appDatabase();
}
