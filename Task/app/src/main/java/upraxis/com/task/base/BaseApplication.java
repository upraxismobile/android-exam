package upraxis.com.task.base;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import upraxis.com.task.di.component.AppComponent;
import upraxis.com.task.di.component.DaggerAppComponent;
import upraxis.com.task.di.module.ApplicationModule;
import upraxis.com.task.di.module.RetrofitModule;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class BaseApplication extends Application {

    private static BaseApplication baseApplication = new BaseApplication();
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        getAppComponent();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .retrofitModule(new RetrofitModule())
                    .build();
        }
        return appComponent;
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }
}
