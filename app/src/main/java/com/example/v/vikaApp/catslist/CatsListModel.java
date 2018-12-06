package com.example.v.vikaApp.catslist;

import com.example.v.vikaApp.entity.CatEntity;

import java.util.List;

public interface CatsListModel {
    interface OnFinishedListener {
        void onFinished(List<CatEntity> catsArrayList);
        void onFailure(Throwable t);
    }

    void getCatsArrayList(boolean isRefresh, OnFinishedListener onFinishedListener);
}
