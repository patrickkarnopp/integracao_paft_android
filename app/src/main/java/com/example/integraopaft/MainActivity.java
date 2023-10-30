package com.example.integraopaft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.integraopaft.client.RetrofitClient;
import com.example.integraopaft.model.User;

import java.util.Calendar;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private EditText edt_nome;
    private EditText edt_dataNascimento;
    private AutoCompleteTextView sp_sexo;
    private CheckBox cb_termos;
    private Button bt_cadastrar;

    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitClient = new RetrofitClient();

        edt_nome = findViewById(R.id.nome);

        //config spinner
        sp_sexo = findViewById(R.id.spinner_sexo);
        sp_sexo.setAdapter(setupSpinner());

        //config datapick
        edt_dataNascimento = findViewById(R.id.editText_DataNascimento);
        edt_dataNascimento.setOnClickListener(view -> showDatePickerDialog());

        bt_cadastrar = findViewById(R.id.button_Cadastrar);
        bt_cadastrar.setEnabled(false);

        //config checkbox
        cb_termos = findViewById(R.id.checkBox_Termos);
        cb_termos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //verificar se os outros campos não estão vazios
                bt_cadastrar.setEnabled(true);
            } else {
                bt_cadastrar.setEnabled(false);
            }
        });
    }


    public void onClickCadastrar(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                User u = validate();
                if( u != null){
                    Call<String> call = retrofitClient.getMyApi().cadastrar(u);

                    exibirDialogSucesso();

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }

            }
        });
    }


    private User validate(){

        String nome = edt_nome.getText().toString();
        String sexo = sp_sexo.getText().toString();
        String dtNasc = edt_dataNascimento.getText().toString();

        if(nome.isEmpty()){
            edt_nome.setError("campo Nome não pode está vazio");
            return null;
        }

        if(sexo.isEmpty()){
            sp_sexo.setError("Selecione uma opção");
            return null;
        }

        if(dtNasc.isEmpty()){
            edt_dataNascimento.setError("Selecione uma data de nascimento");
            return null;
        }


        return new User(nome, sexo, dtNasc);
    }

    private ArrayAdapter<String> setupSpinner(){
        String[] type = new String[] {"Feminino", "Masculino", "Outros"};
        return new ArrayAdapter<>(this,R.layout.dropdown_menu_popup_item, type);
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", day1, month1 + 1, year1);
            edt_dataNascimento.setText(dataSelecionada);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void exibirDialogSucesso() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_black, null);
        builder.setView(dialogView);

        Button button_OK = dialogView.findViewById(R.id.button_OK);
        AlertDialog dialog = builder.create();

        button_OK.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }


}