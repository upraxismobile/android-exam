package upraxis.com.task.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by rsbulanon on 6/18/17.
 */

public interface BasePresenter {

    void onDispose(final Disposable disposable);
}
