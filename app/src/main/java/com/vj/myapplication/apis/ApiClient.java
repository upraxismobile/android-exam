package com.vj.myapplication.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 * Created by VJ on 8/8/2017.
 */
public class ApiClient {

    public static final String BASE_URL = " http://www.mocky.io/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getRestService() {
        return getClient().create(ApiInterface.class);
    }

}
