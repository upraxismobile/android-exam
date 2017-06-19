package upraxis.com.task.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Module
public class ApplicationModule {

    private Application baseApplication;

    public ApplicationModule(Application baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return baseApplication;
    }
}
