package com.application.imageapp.api.ImageResponse;

import android.content.Context;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    Context context = null;


    @GET("v2/list")
    Call<List<ImageResponse>>imageList(@Query("page") String page, @Query("limit") String limit);

}

