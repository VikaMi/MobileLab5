package com.example.v.vikaApp.favourites;

import com.example.v.vikaApp.entity.CatEntity;

import java.util.List;

public interface FavouritesModel {

    interface Result {
        void onResult(List<CatEntity> catEntities);
        void onFailure(Throwable throwable);
    }
    void getListFromSharedPrefs(Result result);
}
