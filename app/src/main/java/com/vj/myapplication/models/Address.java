package com.vj.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {

	@SerializedName("lng")
	private double lng;

	@SerializedName("name")
	private String name;

	@SerializedName("lat")
	private double lat;

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Address{" +
			"lng = '" + lng + '\'' + 
			",name = '" + name + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}