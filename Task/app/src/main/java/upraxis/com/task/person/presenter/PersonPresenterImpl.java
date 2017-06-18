package upraxis.com.task.person.presenter;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import upraxis.com.task.base.BasePresenterImpl;
import upraxis.com.task.commons.AppAction;
import upraxis.com.task.listener.OnApiRequestListener;
import upraxis.com.task.person.contract.PersonContract;
import upraxis.com.task.person.model.Person;
import upraxis.com.task.room.AppDatabase;

/**
 * Created by rsbulanon on 6/18/17.
 */

public class PersonPresenterImpl extends BasePresenterImpl implements PersonContract.Presenter,
                                                                        OnApiRequestListener {

    @Inject
    AppDatabase appDatabase;

    private PersonContract.View view;

    @Inject
    public PersonPresenterImpl(PersonContract.View view, Retrofit retrofit) {
        this.view = view;
    }

    @Override
    public void onFetchPersons() {
        if (isNetworkAvailable()) {

        } else {
            view.onNoConnectionError();
        }
    }

    @Override
    public void onLoadCachedPersons() {
        /**
         * load cached person records from local db
         * */
        Observable.just(appDatabase.personModel().getAll())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.onSubscribed(d);
                    }

                    @Override
                    public void onNext(Object o) {
                        view.onLoadPersons((ArrayList<Person>) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onShowDialog(null, "Load Cache",
                                "Unable to load cached records " + e.getMessage(),
                                "Close", null, true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onCheckCachedPersonsCount() {
        Single.fromCallable(() -> appDatabase.personModel().count())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.onSubscribed(d);
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        view.onCachedPersonCount(integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onShowDialog(null, "Load Cache",
                                "Unable to load cached record count " + e.getMessage(),
                                "Close", null, true);
                    }
                });
    }

    @Override
    public void onApiRequestStart(String action) {
        if (action.equals(AppAction.ON_FETCH_PERSONS)) {
            view.onShowLoading("Persons", "Fetching records, Please wait...");
        }
    }

    @Override
    public void onApiRequestFailed(String action, Throwable t) {
        view.onDismissLoading();
        /**
         *
         * client unable to establish connection with server
         *
         * * server connection/hand shake time out was configured during the retrofit initialization
         * */
        if (t.getMessage().contains("failed to connect") || t.getMessage().contains("Failed to connect")) {
            view.onFailedToConnect();
        } else if (t.getMessage().contains("timeout")) {
            /**
             * client unable to receive response from server
             *
             * server read time out was configured during the retrofit initialization
             * */
            view.onSocketTimeout();
        } else if (t.getMessage().contains("No address associated with hostname") ||
                t.getMessage().contains("Unable to resolve host")) {
            // client lost connection during the request
            view.onNoConnectionError();
        } else {
            /**
             * check if t (Throwable) is instance of HttpException
            * */
            if (t instanceof HttpException) {
                final HttpException ex = ((retrofit2.HttpException) t);
                try {
                    /**
                     * One-shot consuming of error from response body
                     * */
                    final String responseError = ex.response().errorBody().string();

                    if (ex.code() >= 500) {
                        view.onServerRelatedError();
                    } else if (ex.code() == 401) {
                        view.onUnauthorized();
                    } else {
                        //TODO do mapping of response body to RequestError POJO
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                view.onServerRelatedError();
            }
        }
    }

    @Override
    public void onApiRequestSuccess(String action, Object result) {
        view.onDismissLoading();

        if (action.equals(AppAction.ON_FETCH_PERSONS)) {
            /** save fetched person records to local db */
            final ArrayList<Person> persons = (ArrayList<Person>) result;
            appDatabase.personModel().insertAll(persons);

            /** display fetched data */
            view.onLoadPersons(persons);
        }
    }
}
