package com.example.formula1.fragmentsMenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formula1.adaptadores.AdapterRc;
import com.example.formula1.interfacezApi.ErgastAPI;
import com.example.formula1.clasesApi.ErgastResponse;
import com.example.formula1.guardarDatosApi.Piloto;
import com.example.formula1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Drivers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Drivers extends Fragment {
    AdapterRc rcadapter;
    ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    //lista de pilotos de formula 1
    String[] pilostosname = {
            "verstappen",
            "perez",
            "alonso",
            "hamilton",
            "sainz",
            "stroll",
            "russell",
            "norris",
            "hulkenberg",
            "leclerc",
            "bottas",
            "ocon",
            "piastri",
            "gasly",
            "zhou",
            "tsunoda",
            "magnussen",
            "albon",
            "sargeant",
            "de_vries"
    };
    RecyclerView rcdrivers;

    public Drivers() {
        // Required empty public constructor

    }

    public static Drivers newInstance() {
        Drivers fragment = new Drivers();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_drivers, container, false);
        //casteos de interfaz gráfica:
        rcdrivers = vista.findViewById(R.id.rcdrivers);
        try {
            for (int i = 0; i<pilostosname.length;i++) {
                devolverDatos(pilostosname[i]);
            }
        }catch (Exception e){

        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcdrivers.setLayoutManager(linearLayoutManager);
        rcadapter = new AdapterRc(pilotos, getContext(),1);
        rcdrivers.setAdapter(rcadapter);

        Log.w("cantidad", "está es la cantidad" + pilotos.size());


        return vista;
    }

    //metodos para recrear la actividad:


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public ArrayList<Piloto> devolverDatos(String namepiloto) throws InterruptedException {

        //crear convertidor de json api a clase
        String baseUrl = "http://ergast.com/api/f1/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //pasar a objeto
            ErgastAPI api = retrofit.create(ErgastAPI.class);
            Call<ErgastResponse<Piloto>> call = api.obtenerPiloto(namepiloto);
            call.enqueue(new Callback<ErgastResponse<Piloto>>() {
                @Override
                public void onResponse(Call<ErgastResponse<Piloto>> call, Response<ErgastResponse<Piloto>> response) {
                    if (response.isSuccessful()) {
                        ErgastResponse<Piloto> ergastResponse = response.body();
                        if (ergastResponse != null && ergastResponse.getData() != null && ergastResponse.getData().getDriversTable().getDrivers() != null && !ergastResponse.getData().getDriversTable().getDrivers().isEmpty()){
                            Piloto piloto = (Piloto) ergastResponse.getData().getDriversTable().getDrivers().get(0);
                            pilotos.add(piloto);
                            rcadapter.notifyDataSetChanged();
                            Log.w("piloto añadido", piloto.getNombre()+" "+piloto.getApellido());

                        }else {Log.w("piloto no encontrado","este es el piloto que no tiene drivers: "+namepiloto);}

                    } else {
                        // Error http:
                        Log.w("error","hubo un error en la conexión");

                    }

                }

                @Override
                public void onFailure(Call<ErgastResponse<Piloto>> call, Throwable t) {
                    // Maneja el error de la solicitud HTTP
                    Log.w("error","hubo un error en la conexión"+ t.toString());
                }
            });



        Log.w("tamano","resultado: "+ pilotos.size() );
        return pilotos;
    }


}