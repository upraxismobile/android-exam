package com.vj.myapplication.models;

import com.google.gson.annotations.SerializedName;
import com.vj.myapplication.Util;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

	@SerializedName("birthday")
	private String birthday;

	@SerializedName("last_name")
	private String lastName;

    @SerializedName("image")
    private String image;

	@SerializedName("mobile_number")
	private String mobileNumber;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("age")
	private int age;

	@SerializedName("email")
	private String email;

	@SerializedName("address")
	private List<Address> address;

	@SerializedName("contacts")
	private List<Contact> contacts;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return birthday;
	}

	public void setAddress(List<Address> address){
		this.address = address;
	}

	public List<Address> getAddress(){
		return address;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return Util.calculateAge(getBirthday(), "MM/dd/yyyy");
    }

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setContacts(List<Contact> contacts){
		this.contacts = contacts;
	}

	public List<Contact> getContacts(){
		return contacts;
	}

	@Override
 	public String toString(){
		return 
			"Person{" +
			"birthday = '" + birthday + '\'' + 
			",address = '" + address + '\'' + 
			",last_name = '" + lastName + '\'' +
			",mobile_number = '" + mobileNumber + '\'' +
			",first_name = '" + firstName + '\'' +
			",age = '" + age + '\'' +
			",email = '" + email + '\'' + 
			",contacts = '" + contacts + '\'' + 
			"}";
		}
}