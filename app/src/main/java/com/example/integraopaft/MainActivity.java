package com.example.integraopaft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editText_DataNascimento;
    private CheckBox checkBox_Termos;
    private Button button_Cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_Cadastrar = findViewById(R.id.button_Cadastrar);
        button_Cadastrar.setEnabled(false);

        button_Cadastrar.setOnClickListener(view -> {
            exibirDialogSucesso();
        });




        // Setup do Spinner
        String[] type = new String[] {"Feminino", "Masculino", "Outros"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        type);

        AutoCompleteTextView spinner_sexo = findViewById(R.id.spinner_sexo);
        spinner_sexo.setAdapter(adapter);


        //Setup da Data de Nascimento
        editText_DataNascimento = findViewById(R.id.editText_DataNascimento);
        editText_DataNascimento.setOnClickListener(view -> showDatePickerDialog());


        //Setup dos Termos
        checkBox_Termos = findViewById(R.id.checkBox_Termos);

        checkBox_Termos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                button_Cadastrar.setEnabled(true);
            } else {
                button_Cadastrar.setEnabled(false);
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            String dataSelecionada = String.format("%02d/%02d/%04d", day1, month1 + 1, year1);
            editText_DataNascimento.setText(dataSelecionada);
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