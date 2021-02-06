package com.application.imageapp.api.ImageResponse;

import android.content.Context;

import com.application.imageapp.utils.PrefManager;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;


public class ApiManager extends ApiClient {
    private static ApiManager apiManager;
    private Context mContext;
    private String accessToken;

    public ApiManager(Context context) {
        super(context);
        mContext = context;
        PrefManager prefManager = PrefManager.getInstance(mContext);

    }

    public static ApiManager getInstance(Context context) {
        if (apiManager == null) {
            apiManager = new ApiManager(context);
        }
        return apiManager;
    }

    public void imageLis(ApiCallBack<List<ImageResponse>> callBack,String page,String limit ) {
        ApiClient.current(mContext, false).imageList(page,limit).enqueue(callBack);
    }

}


