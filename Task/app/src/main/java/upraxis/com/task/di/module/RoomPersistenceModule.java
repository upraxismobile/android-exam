package upraxis.com.task.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import upraxis.com.task.room.AppDatabase;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Module
public class RoomPersistenceModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Application application) {
        return AppDatabase.getInstance(application);
    }

}
