package com.example.formula1.interfacezApi;

import com.example.formula1.clasesApi.ErgastResponseConstructor;
import com.example.formula1.guardarDatosApi.Constructores;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ErgastAPIConstructors {
    @GET("constructors/{constructorId}.json")
    Call<ErgastResponseConstructor<Constructores>> obtenerConstructor(
            @Path("constructorId") String constructorId
    );
}
