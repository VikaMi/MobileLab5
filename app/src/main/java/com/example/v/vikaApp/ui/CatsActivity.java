package com.example.v.vikaApp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.v.vikaApp.App;
import com.example.v.vikaApp.CatInterface;
import com.example.v.vikaApp.R;
import com.example.v.vikaApp.adapter.RecyclerViewAdapter;
import com.example.v.vikaApp.model.Cat;
import com.example.v.vikaApp.model.ResultCat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsActivity extends AppCompatActivity {

    CatInterface mCatInterface;
    private ArrayList<Cat> mCats = new ArrayList<>();
    private SwipeRefreshLayout swipeContainer;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @BindView(R.id.button_go_to_favourite)
    ImageButton mFavouritesButton;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, CatsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        initRetrofit();
        initRefreshLayout();
        initRecyclerView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_go_to_favourite})
    public void onClick(View v) {
        startActivity(FavouritesListActivity.getStartIntent(CatsActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCats();
    }

    private void initRefreshLayout() {
        swipeContainer = findViewById(R.id.swipe_container);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRetrofit();
                getCats();
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_cats);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mCats);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initRetrofit() {
        mCatInterface = App.getApi();
    }

    private void getCats() {
        Call<List<ResultCat>> call = mCatInterface.imagesOfCats();
        call.enqueue(new Callback<List<ResultCat>>() {
            @Override
            public void onResponse(Call<List<ResultCat>> call, Response<List<ResultCat>> response) {
                mRecyclerViewAdapter.clear();
                mCats.clear();
                List<ResultCat> resultCats = response.body();
                if (resultCats != null) {
                    for (ResultCat resultCat : resultCats) {
                        final int[] codes = {100, 101, 200, 201, 202, 204, 500, 404, 300, 425, 426,
                                207, 307, 410, 411, 420, 421, 422, 423, 424};
                        for (final int code : codes) {
                            Cat cat = new Cat(resultCat.getId(code), resultCat.getUrl(code));
                            mCats.add(cat);
                        }
                    }
                    mRecyclerViewAdapter.addAll(mCats);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ResultCat>> call, Throwable t) {
                Log.i("Cats", "Something went wrong.");
            }
        });
    }
}
