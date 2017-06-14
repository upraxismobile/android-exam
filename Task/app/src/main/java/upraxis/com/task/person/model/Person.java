package upraxis.com.task.person.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class Person implements Parcelable {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("birth_day")
    private String birthDay;

    private int age;

    private String email;

    @SerializedName("mobile_number")
    private String mobileNumber;

    @SerializedName("contact_person")
    private ContactPerson contactPerson;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.birthDay);
        dest.writeInt(this.age);
        dest.writeString(this.email);
        dest.writeString(this.mobileNumber);
        dest.writeParcelable(this.contactPerson, flags);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.birthDay = in.readString();
        this.age = in.readInt();
        this.email = in.readString();
        this.mobileNumber = in.readString();
        this.contactPerson = in.readParcelable(ContactPerson.class.getClassLoader());
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}

