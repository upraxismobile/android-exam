package upraxis.com.task.person.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import upraxis.com.task.R;
import upraxis.com.task.adapter.PersonAdapter;
import upraxis.com.task.base.BaseActivity;
import upraxis.com.task.base.BaseApplication;
import upraxis.com.task.commons.ErrorMessages;
import upraxis.com.task.person.component.DaggerPersonComponent;
import upraxis.com.task.person.contract.PersonContract;
import upraxis.com.task.person.model.Person;
import upraxis.com.task.person.module.PersonModule;
import upraxis.com.task.person.presenter.PersonPresenterImpl;
import upraxis.com.task.room.AppDatabase;

public class PersonActivity extends BaseActivity implements PersonContract.View {

    @Inject
    AppDatabase appDatabase;

    @Inject
    PersonPresenterImpl presenter;

    @BindView(R.id.rv_people)
    RecyclerView rv_people;

    private Unbinder unbinder;
    private Disposable disposable = null;
    private ProgressDialog progressDialog;
    private ArrayList<Person> people = new ArrayList<>();

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

        /** initialize people listing */
        final PersonAdapter adapter = new PersonAdapter(people);
        rv_people.setAdapter(adapter);

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
        showConfirmDialog(action, header, message, positiveText, negativeText, isCancelable,
                null);
    }

    @Override
    public void onLoadPersons(ArrayList<Person> people) {
        this.people.clear();
        this.people.addAll(people);

        rv_people.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCachedPersonCount(Integer count) {
        /** load cached person record */
        if (count > 0) {
            presenter.onLoadCachedPersons();
        } else {
            showConfirmDialog(null, ErrorMessages.ERROR_NO_CONNECTION_HEADER,
                    ErrorMessages.ERROR_NO_CONNECTION_MESSAGE, "Close", null,
                    true, null);
        }
    }

    @Override
    public void onShowLoading(String header, String message) {
        dismissProgressDialog();

        progressDialog = new ProgressDialog(this);

        /** display header/title */
        if (header != null) {
            progressDialog.setTitle(header);
        }

        /** display message */
        if (message != null) {
            progressDialog.setMessage(message);
        }

        /** display progress bar */
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onUnauthorized() {
        showConfirmDialog(null, ErrorMessages.ERROR_UNAUTHORIZED,
                ErrorMessages.ERROR_UNABLE_TO_CONNECT_WITH_SERVER, "Close",
                null, true, null);
    }

    @Override
    public void onDismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onFailedToConnect() {
        showConfirmDialog(null, ErrorMessages.ERROR_NO_CONNECTION_HEADER,
                ErrorMessages.ERROR_UNABLE_TO_CONNECT_WITH_SERVER, "Close",
                null, true, null);
    }

    @Override
    public void onSocketTimeout() {
        showConfirmDialog(null, ErrorMessages.ERROR_NO_CONNECTION_HEADER,
                ErrorMessages.ERROR_SLOW_CONNECTION, "Close", null,
                true, null);
    }

    @Override
    public void onServerRelatedError() {
        showConfirmDialog(null, ErrorMessages.ERROR_NO_CONNECTION_HEADER,
                ErrorMessages.ERROR_UNABLE_TO_COMMUNICATE_WITH_SERVER, "Close",
                null, true, null);
    }

    @Override
    public void onNoConnectionError() {
        /**
         * client has no network connection, try to check if there's cached person data and load it
         * if there's any.
         * */
        presenter.onCheckCachedPersonsCount();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            /** dismiss current instance of progress bar if it's currently shown */
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        /**
         * stop receiving of result of subscription from Observable or Single
         * upon destroying of activity
         * */
        presenter.onDispose(disposable);


        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
