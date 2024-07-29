package com.example.olhovivo.controller;

import com.example.olhovivo.model.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexaoApiOlhoVivo {

    public static Retrofit criarConexao(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.OLHOVIVO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
