package com.example.integraopaft.servico;

import com.example.integraopaft.client.RetrofitClient;
import com.example.integraopaft.model.User;

import lombok.AllArgsConstructor;
import retrofit2.Call;

@AllArgsConstructor
public class RetrofitService {

    private RetrofitClient retrofitClient;

    public String cadastrar(User u){
        Call<String> call = retrofitClient.getMyApi().cadastrar(u);

                    /*
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Resposta resposta = response.body();
                                // Lidar com a resposta de sucesso aqui
                            } else {
                                // Lidar com erros aqui
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            // Lidar com erros de conexão ou outros erros aqui
                        }
                    });*/
        return null;
    }
}
