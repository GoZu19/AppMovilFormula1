package com.example.formula1.fragmentsMenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formula1.R;
import com.google.android.material.navigation.NavigationView;


public class PerfilFragment extends Fragment {

    Button guardarbtn;
    SharedPreferences prefs;
    EditText username,useremail;
    Toast toast;
    public PerfilFragment() {
        // Required empty public constructor
    }


    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();

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
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        username = vista.findViewById(R.id.username);
        useremail = vista.findViewById(R.id.emailuser);
        guardarbtn = vista.findViewById(R.id.guardarperfil);
        prefs = getContext().getSharedPreferences("perfil", Context.MODE_PRIVATE);
        String correo = prefs.getString("email","example@example.com");
        String usuario = prefs.getString("user","Alonso");
        username.setText(usuario);
        useremail.setText(correo);

        guardarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                String guardarusuario = username.getText().toString();
                String guardaremail = useremail.getText().toString();
                editor.putString("user",guardarusuario);
                editor.putString("email",guardaremail);
                editor.commit();
                String mensaje = getString(R.string.guardarDatos);
                toastTexto(mensaje);

                //esto es para refrescar la cabecera cuando le da a guardar lo refresca según lo que ha guardado
                NavigationView navigationView = getActivity().findViewById(R.id.navegationmain);
                View vistaHeader = navigationView.getHeaderView(0);
                TextView nameuserheader = vistaHeader.findViewById(R.id.nameUser);
                String nombreUsuario = prefs.getString("user","Fernando Alonso");
                nameuserheader.setText(nombreUsuario);

            }
        });

        return vista;
    }
    //toast de texto para cancelarlo su uso:
    public void toastTexto(String mensaje){
        if (toast !=null){
            toast.cancel();
        }
        toast =new Toast(getContext());
        toast.setText(mensaje);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}