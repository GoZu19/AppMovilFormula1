package com.example.formula1.fragmentsMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formula1.adaptadores.AdapterRcContructors;
import com.example.formula1.guardarDatosApi.Constructores;
import com.example.formula1.interfacezApi.ErgastAPIConstructors;
import com.example.formula1.clasesApi.ErgastResponseConstructor;
import com.example.formula1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConstructoresFragment extends Fragment {

    AdapterRcContructors rcadapter;
    ArrayList<Constructores> contructores = new ArrayList<Constructores>();
    //lista de pilotos de formula 1
    String[] contructoresname = {
            "red_bull",
            "aston_martin",
            "ferrari",
            "mercedes",
            "mclaren",
            "alpine",
            "haas",
            "alfa",
            "alphatauri",
            "williams"
    };
    RecyclerView rcconstructors;
    public ConstructoresFragment() {
        // Required empty public constructor
    }


    public static ConstructoresFragment newInstance(String param1, String param2) {
        ConstructoresFragment fragment = new ConstructoresFragment();
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
        View vista = inflater.inflate(R.layout.fragment_constructores, container, false);
        rcconstructors = vista.findViewById(R.id.contructor_rc);
        rcadapter = new AdapterRcContructors(contructores, getContext());
        try {
            for (int i = 0; i< contructoresname.length;i++) {
                devolverDatos(contructoresname[i]);
            }
        }catch (Exception e){

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcconstructors.setLayoutManager(linearLayoutManager);
        rcconstructors.setAdapter(rcadapter);
        return vista;
    }
    public void devolverDatos(String nameconstructor) {

        //crear convertidor de json api a clase
        String baseUrl = "http://ergast.com/api/f1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //pasar a objeto
        ErgastAPIConstructors api = retrofit.create(ErgastAPIConstructors.class);
        Call<ErgastResponseConstructor<Constructores>> call = api.obtenerConstructor(nameconstructor);
        call.enqueue(new Callback<ErgastResponseConstructor<Constructores>>() {
            @Override
            public void onResponse(Call<ErgastResponseConstructor<Constructores>> call, Response<ErgastResponseConstructor<Constructores>> response) {
                if (response.isSuccessful()) {
                    ErgastResponseConstructor<Constructores> ergastResponse = response.body();
                    if (ergastResponse != null && ergastResponse.getData() != null && ergastResponse.getData().getConstructorTable().getConstructores() != null && !ergastResponse.getData().getConstructorTable().getConstructores().isEmpty()) {
                        Constructores constructores = (Constructores) ergastResponse.getData().getConstructorTable().getConstructores().get(0);
                        contructores.add(constructores);
                        rcadapter.notifyDataSetChanged();
                        Log.w("Constructor añadido", constructores.getName() + " " + constructores.getId());

                    } else {
                        Log.w("constructor no encontrado", "este es el Constructor que no tiene constructors: " + nameconstructor);
                    }

                } else {
                    // Error http:
                    Log.w("error", "hubo un error en la conexión");

                }
            }

            @Override
            public void onFailure(Call<ErgastResponseConstructor<Constructores>> call, Throwable t) {
                Log.w("error","hubo un error en la conexión"+ t.toString());
            }
        });

    }
}