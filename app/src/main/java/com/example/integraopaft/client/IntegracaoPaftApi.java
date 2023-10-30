package com.example.integraopaft.client;

import com.example.integraopaft.model.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IntegracaoPaftApi {

    String BASE_URL = "https://integracao-paft-appback.azurewebsites.net/api-integracao-paft/";
    @GET("buscar/{nome}")
    Call<User> buscar(@Path("nome") String nome);

    @POST("adicionar")
    Call<String> cadastrar(@Body User u);
}
