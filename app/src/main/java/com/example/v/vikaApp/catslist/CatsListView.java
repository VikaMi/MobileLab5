package com.example.v.vikaApp.catslist;

import com.example.v.vikaApp.entity.CatEntity;

import java.util.List;

public interface CatsListView {
    void setDataToRecyclerView(List<CatEntity> catsArrayList);
    void refreshDataInRecyclerView(List<CatEntity> catsArrayList);
    void onResponseFailure(Throwable throwable);
}
