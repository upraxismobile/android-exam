package upraxis.com.task.person.view;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import upraxis.com.task.R;
import upraxis.com.task.base.BaseActivity;
import upraxis.com.task.base.BaseApplication;
import upraxis.com.task.person.model.Person;
import upraxis.com.task.person.mvp.DaggerPersonComponent;
import upraxis.com.task.person.contract.PersonContract;
import upraxis.com.task.person.module.PersonModule;
import upraxis.com.task.person.presenter.PersonPresenterImpl;
import upraxis.com.task.room.AppDatabase;
import upraxis.com.task.utils.LogHelper;

public class PersonActivity extends BaseActivity implements PersonContract.View {

    @Inject
    AppDatabase appDatabase;

    @Inject
    PersonPresenterImpl presenter;

    private Unbinder unbinder;
    private Disposable disposable = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** initialize view injections */
        unbinder = ButterKnife.bind(this);

        /**
         * initialize dagger person component
         * */
        DaggerPersonComponent.builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                .personModule(new PersonModule(this))
                .build()
                .inject(this);

        /** fetch persons from remote source */
        presenter.onFetchPersons();
    }

    @Override
    public void onSubscribed(Disposable disposable) {
        /** reference on current subscription */
        presenter.onDispose(this.disposable);
        this.disposable = null;
        this.disposable = disposable;
    }

    @Override
    public void onShowDialog(String action, String header, String message,
                             String positiveText, String negativeText, boolean isCancelable) {
        /** check current instance of progress dialog if not null */
        if (progressDialog != null) {
            /** dismiss current instance of progress bar if it's currently shown */
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }

    }

    @Override
    public void onLoadPersons(ArrayList<Person> persons) {

    }

    @Override
    public void onCachedPersonCount(Integer integer) {

    }

    @Override
    public void onShowLoading(String header, String message) {

    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onDismissLoading() {

    }

    @Override
    public void onFailedToConnect() {

    }

    @Override
    public void onSocketTimeout() {

    }

    @Override
    public void onServerRelatedError() {

    }

    @Override
    public void onGenericError() {

    }

    @Override
    public void onNoConnectionError() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDispose(disposable);
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
