package com.example.integraopaft.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private IntegracaoPaftApi paftApi;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(paftApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        paftApi = retrofit.create(IntegracaoPaftApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public IntegracaoPaftApi getMyApi() {
        return paftApi;
    }
}
