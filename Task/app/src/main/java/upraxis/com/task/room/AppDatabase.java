package upraxis.com.task.room;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import upraxis.com.task.person.dao.PersonDao;
import upraxis.com.task.person.model.Person;
import upraxis.com.task.utils.LogHelper;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PersonDao personModel();

    public static AppDatabase getInstance(Application application) {
        LogHelper.log("person", "get instance");
        if (INSTANCE == null) {
            LogHelper.log("person", "creating room...");
            INSTANCE = Room.databaseBuilder(application.getApplicationContext(),
                    AppDatabase.class, "task-db").build();
            LogHelper.log("person", "room created!");
        } else {
            LogHelper.log("person", "room instance already existing.");
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        if (INSTANCE != null) {
            INSTANCE = null;
        }
    }
}
