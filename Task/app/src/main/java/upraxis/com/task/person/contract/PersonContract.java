package upraxis.com.task.person.contract;

import java.util.ArrayList;

import upraxis.com.task.base.BasePresenter;
import upraxis.com.task.base.BaseView;
import upraxis.com.task.person.model.Person;

/**
 * Created by rsbulanon on 6/14/17.
 */

public interface PersonContract {

    interface View extends BaseView {
        void onLoadPersons(final ArrayList<Person> persons);

        void onCachedPersonCount(final Integer integer);
    }

    interface Presenter extends BasePresenter {
        void onCheckCachedPersonsCount();

        void onFetchPersons();

        void onLoadCachedPersons();
    }
}
