package com.example.integraopaft.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
public class User {

    @SerializedName("nome")
    private String nome;
    @SerializedName("sexo")
    private String sexo;
    @SerializedName("dataNascimento")
    private String dataNascimento;

    public User(String nome, String sexo, String dataNascimento) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
