package upraxis.com.task.person.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import upraxis.com.task.person.model.Person;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Dao
public interface PersonDao {

    /**
     * get all record
     * */
    @Query("SELECT * FROM Person")
    Observable<List<Person>> getAll();

    /**
     * create new record
     * */
    @Insert
    void insert(Person Person);

    /**
     * create multiple {@link Person} record
     * */
    @Insert
    void insertAll(List<Person> Person);


    /**
     * delete record {@link Person}
     * */
    @Delete
    void delete(Person Person);

    /**
     * return total count of person record
     * */
    @Query("SELECT COUNT(ID) FROM Person")
    int count();
}
