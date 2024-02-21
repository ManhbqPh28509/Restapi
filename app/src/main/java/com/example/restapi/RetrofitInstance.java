package com.example.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit retrofit;
    public static Retrofit getInstance(){
        if (retrofit == null){
             retrofit = new Retrofit.Builder()
                    .baseUrl("https://5f77cba4d5c9cb001623772a.mockapi.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
