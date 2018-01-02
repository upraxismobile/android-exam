package com.vj.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contact implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("image")
	private String image;

	@SerializedName("mobile_number")
	private String mobileNumber;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	@Override
 	public String toString(){
		return 
			"Contact{" +
			"name = '" + name + '\'' + 
			"name = '" + image + '\'' +
			",mobile_number = '" + mobileNumber + '\'' +
			"}";
		}
}