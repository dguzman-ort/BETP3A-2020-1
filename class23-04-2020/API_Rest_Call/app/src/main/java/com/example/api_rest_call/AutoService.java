package com.example.api_rest_call;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutoService {

    /**
     * Definicion de ruta para GET
     */
    String API_ROUTE= "app/api/read";

    /**
     * Metodo abstracto para utilizar HTTP.GET
     * @return
     */
    @GET(API_ROUTE)
    Call<List<Auto>> getAutos();



}
