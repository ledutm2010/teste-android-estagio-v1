package com.example.olhovivo.controller;

import com.example.olhovivo.model.Constantes;
import com.example.olhovivo.model.Linha;
import com.example.olhovivo.model.Parada;
import com.example.olhovivo.model.ParadaLinhaVeiculo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiOlhoVivo {
    //1. Posições dos veículos: Exibir no mapa onde os veículos estavam na sua última atualização.
    //2. Linhas: Exibir informações sobre as linhas de ônibus.
    //3. Paradas: Exibir os pontos de parada da cidade no mapa.
    //4. Previsão de chegada: Dado uma parada informar a previsão de chegada de cada veículo que
    // passe pela parada selecionada.
    //5. TODO: Pesquisa e Filtros: Permitir que o usuário pesquise e filtre esses dados,
    // interagindo com a interface.
    //Extras:
    //6. TODO: Refresh automático: Que as informações exibidas no aplicativo sejam atualizadas
    // de tempo em tempo de forma transparente ao usuário
    //7. TODO: Cálculo de rotas: Exibir a possível rota de um ou mais ônibus em relação a uma
    // parada, ou do usuário em relação a uma parada (Utilizando API do Google Maps ou equivalentes)
    //8. TODO: Corredores: Mostrar informações sobre os corredores de ônibus de SP.
    //9. TODO: Velocidade das vias: Mostrar informações sobre as velocidades das vias.
    //TODO: Testes: Desenvolva testes que achar necessário para a aplicação.
    //TODO: Documentação: Gerar uma documentação da aplicação. A documentação pode incluir
    // detalhes sobre as decisões tomadas, especificação das funcionalidades desenvolvidas,
    // instruções de uso dentre outras informações que achar relevantes.

    //logar na api olho vivo
    @POST("Login/Autenticar?token={token}")
    Call<Constantes> login();

    //1 e 2. pega as posições de todos os veiculos por linhas
    @GET("Posicao")
    Call<ArrayList<Linha>> pegarTodosVeiculosPorLinhas();

    //3. busca uma parada especifica
    @GET("Parada/Buscar?termosBusca={codParada}")
    Call<Parada> pegarParadaEspecifica(@Path("codParada") int codParada);

    //4. pega todos os veiculos da linha que passam na parada e o horario de chegada
    @GET("Previsao?codigoParada={codParada}&codigoLinha={codLinha}")
    Call<ParadaLinhaVeiculo> pegarHorarioChegadaVeiculosDaLinhaPorParada(@Path("codParada") int codParada, @Path("codLinha") int codLinha);
}
