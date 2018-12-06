package com.example.v.vikaApp;

import android.app.Application;

import com.example.v.vikaApp.api.CatInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static CatInterface catInterface;

    public static CatInterface getApi() {
        return catInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        catInterface = retrofit.create(CatInterface.class);
    }
}
