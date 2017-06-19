package upraxis.com.task.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import javax.inject.Inject;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class BasePresenterImpl {

    @Inject
    Application application;

    public boolean isNetworkAvailable() {
        final ConnectivityManager cm = (ConnectivityManager) application
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                    || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            return false;
        }
    }

    /**
     * check if user email is in valid format
     */
    public boolean isEmailValid(final String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
