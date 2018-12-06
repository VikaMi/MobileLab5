package com.example.v.vikaApp.catslist;

import com.example.v.vikaApp.App;
import com.example.v.vikaApp.entity.CatEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsListModelImpl implements CatsListModel {

    @Override
    public void getCatsArrayList(final boolean isRefresh, final OnFinishedListener onFinishedListener) {
        Call<List<CatEntity>> call = App.getApi().imagesOfCats();
        call.enqueue(new Callback<List<CatEntity>>() {
            @Override
            public void onResponse(Call<List<CatEntity>> call,
                                   Response<List<CatEntity>> response) {
                List<CatEntity> resultCats = response.body();
                onFinishedListener.onFinished(resultCats);
            }

            @Override
            public void onFailure(Call<List<CatEntity>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}
