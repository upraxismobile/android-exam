package upraxis.com.task.person.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class ContactPerson implements Parcelable {

    public String name;

    @SerializedName("mobile_number")
    public String mobileNumber;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.mobileNumber);
    }

    public ContactPerson() {
    }

    protected ContactPerson(Parcel in) {
        this.name = in.readString();
        this.mobileNumber = in.readString();
    }

    public static final Parcelable.Creator<ContactPerson> CREATOR = new Parcelable.Creator<ContactPerson>() {
        @Override
        public ContactPerson createFromParcel(Parcel source) {
            return new ContactPerson(source);
        }

        @Override
        public ContactPerson[] newArray(int size) {
            return new ContactPerson[size];
        }
    };
}
