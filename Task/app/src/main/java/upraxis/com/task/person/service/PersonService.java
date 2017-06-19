package upraxis.com.task.person.service;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import upraxis.com.task.person.model.Person;

/**
 * Created by rsbulanon on 6/19/17.
 */
public interface PersonService {

    @GET("/api/people")
    Observable<ArrayList<Person>> getPeople();
}
