package upraxis.com.task.person.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rsbulanon on 6/14/17.
 */
@Entity(tableName = "person")
public class Person {

    @PrimaryKey
    public String ID;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("birth_day")
    public String birthDay;

    public int age;

    public String email;

    @SerializedName("mobile_number")
    public String mobileNumber;

}

