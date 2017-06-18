package upraxis.com.task.listener;

/**
 * Created by rsbulanon on 6/18/17.
 */
public interface OnApiRequestListener {

    void onApiRequestStart(final String action);

    void onApiRequestFailed(final String action, final Throwable t);

    void onApiRequestSuccess(final String action, final Object result);
}
