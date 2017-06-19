package upraxis.com.task.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by rsbulanon on 6/14/17.
 */

public interface BaseView {

    void onShowDialog(final String action, final String header, final String message,
                     final String positiveText, final String negativeText, final boolean isCancelable);

    void onShowLoading(final String header, final String message);

    void onDismissLoading();

    void onFailedToConnect();

    void onSocketTimeout();

    void onServerRelatedError();

    void onNoConnectionError();

    void onSubscribed(final Disposable disposable);

    void onUnauthorized();
}
