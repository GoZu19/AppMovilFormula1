package com.example.formula1.interfacezApi;

import com.example.formula1.clasesApi.ErgastResponse;
import com.example.formula1.guardarDatosApi.Piloto;

import retrofit2.Call;
import retrofit2.http.*;


public interface ErgastAPI {
    @GET("drivers/{driverId}.json")
    Call<ErgastResponse<Piloto>> obtenerPiloto(
            @Path("driverId") String driverId
    );
}
