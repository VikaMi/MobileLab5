package com.example.v.vikaApp.favourites;

import com.example.v.vikaApp.entity.CatEntity;

import java.util.List;

public interface FavouritesView {
    void setDataToRecyclerView(List<CatEntity> catsArrayList);
    void onResponseFailure(Throwable throwable);
}
