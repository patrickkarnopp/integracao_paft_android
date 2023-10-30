package com.example.integraopaft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.integraopaft.client.RetrofitClient;
import com.example.integraopaft.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private EditText edt_nome;
    private Button bt_enviar;
    private TextView tx_nome;
    private TextView tx_sexo;
    private TextView tx_dtNasc;

    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        retrofitClient = new RetrofitClient();

        edt_nome = findViewById(R.id.edt_nome);
        bt_enviar = findViewById(R.id.btn_enviar);
        tx_nome = findViewById(R.id.tx_nome);
        tx_sexo = findViewById(R.id.tx_sexo);
        tx_dtNasc = findViewById(R.id.tx_dtNasc);

    }

    public void onClickBuscar(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edt_nome.getText().toString().trim();
                if(nome.isEmpty()){
                    edt_nome.setError("campo Nome não pode está vazio");
                }else{

                    Call<User> call = retrofitClient.getMyApi().buscar(nome);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User u = response.body();
                            tx_nome.setText(u.getNome());
                            tx_sexo.setText(u.getSexo());
                            tx_dtNasc.setText(u.getDataNascimento());
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });


                }
            }
        });
    }
}