package com.example.v.vikaApp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.v.vikaApp.CatInterface;
import com.example.v.vikaApp.R;
import com.example.v.vikaApp.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsActivity extends AppCompatActivity {

    private static final String TAG = "CatsActivity";

    ArrayList<Bitmap> mImages = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        recyclerView = findViewById(R.id.parent_layout);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        initRetrofit();
        swipeLayout = findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light)
        );

    }



    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://http.cat/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CatInterface catInterface = retrofit.create(CatInterface.class);
        int[] codes = {100, 101, 200};
        for (int code : codes) {
            Call<ResponseBody> call = catInterface.imagesOfCats(code);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                    mImages.add(bm);
                    recyclerViewAdapter = new RecyclerViewAdapter(CatsActivity.this, mImages);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    Log.e("ITEM", String.valueOf(mImages.size()));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Cats", "Something went wrong.");
                    Log.e("Cats", t.toString());
                }
            });
        }
        Log.e("IMAGES", String.valueOf(mImages.size()));
    }


}
