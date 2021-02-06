package com.application.imageapp.api.ImageResponse;

import android.content.Context;

import com.application.imageapp.utils.ApiConstant;
import com.application.imageapp.utils.PrefManager;
import com.application.imageapp.utils.SavedPrefManager;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static ApiClient apiClient = null;
    private ApiInterface apiInterface;
    private static boolean isTrue;
    private PrefManager prefManager;
    private Context context;

    private Request.Builder requestBuilder;
    ApiClient( Context context) {
        this.context=context;
        prefManager= PrefManager.getInstance(context);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);


            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                if (isTrue) {

                    requestBuilder = original.newBuilder()
                            .header("Authorization", "JWT "+ SavedPrefManager.getStringPreferences(context,
                                    ApiConstant.KEY_JWT_TOKEN))
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body());
                } else {
                    requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body());
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    public static ApiInterface current(Context context, boolean istrue) {
        isTrue = istrue;
        if (istrue) {
            return getInstance(context, true).getApiInterface();
        } else {
            return getInstance(context, false).getApiInterface();
        }
    }


    public static ApiClient getInstance(Context context, boolean istrue) {
        if (istrue) {
            if (apiClient == null) {
                apiClient = new ApiClient(context);
            }
        } else {
            if (apiClient == null) {
                apiClient = new ApiClient(context);
            }
        }

        return apiClient;
    }

    private ApiInterface getApiInterface() {
        return apiInterface;
    }

}
