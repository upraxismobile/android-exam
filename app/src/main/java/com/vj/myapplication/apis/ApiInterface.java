package com.vj.myapplication.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *
 * Created by VJ on 8/8/2017.
 */

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("v2/5a47f817300000ce0ec3c545")
    Call<JsonObject> getPersons();
}


