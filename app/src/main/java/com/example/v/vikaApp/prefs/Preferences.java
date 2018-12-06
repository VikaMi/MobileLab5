package com.example.v.vikaApp.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.example.v.vikaApp.entity.CatEntity;

public class Preferences {

    private SharedPreferences mSharedPreferences;

    public Preferences(Context context) {
        mSharedPreferences = context.getSharedPreferences("favourites", Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPrefs() {
        return mSharedPreferences;
    }

    public void putIntoSharedPrefs(CatEntity catEntity) {
        mSharedPreferences.edit().putString(catEntity.getId(), new Gson().toJson(catEntity)).apply();
    }

    public void rmFromSharedPrefs(CatEntity catEntity) {
        mSharedPreferences.edit().remove(catEntity.getId()).apply();
    }

}
