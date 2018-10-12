package com.example.v.vikaApp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CatInterface {
    @GET("{status_code}")
    Call<ResponseBody> imagesOfCats(@Path("status_code") int code);
}
