package com.example.formula1.fragmentsMenu;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.formula1.R;
import com.example.formula1.adaptadores.AdapterRc;
import com.example.formula1.conexionBBDD.AdminBBDD;
import com.example.formula1.conexionBBDD.Conexiones;
import com.example.formula1.guardarDatosApi.Piloto;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    AdminBBDD adminBBDD;
    SQLiteDatabase db;
    RecyclerView recicleFavorito;
    ArrayList<Piloto> arrayFavoritoList;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //crear la instancia de bases de datos
        adminBBDD = new AdminBBDD(getContext(),"Favorito",null,1);
        db = adminBBDD.getWritableDatabase();
        Conexiones conexionBBDD = new Conexiones(db);
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        recicleFavorito = vista.findViewById(R.id.rcfavorito);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        arrayFavoritoList = conexionBBDD.obtenerPilotosFavoritos();
        AdapterRc adapterRc = new AdapterRc(arrayFavoritoList,getContext(),2);
        recicleFavorito.setLayoutManager(layoutManager);
        recicleFavorito.setAdapter(adapterRc);
        Log.w("arrayListFavorito","el tamaño de la array: "+arrayFavoritoList.size());
        return vista;
    }

}