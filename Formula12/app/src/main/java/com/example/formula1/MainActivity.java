package com.example.formula1;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.formula1.fragmentsMenu.ConstructoresFragment;
import com.example.formula1.fragmentsMenu.Drivers;
import com.example.formula1.fragmentsMenu.FavoriteFragment;
import com.example.formula1.fragmentsMenu.Home;
import com.example.formula1.fragmentsMenu.PerfilFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //LOS FRAGMENTOS UTILIZADOS, LOS COLOCO AQUÍ PARA OPTIMIZAR EL USO DE LA APP
    FavoriteFragment favoriteFragment;
    Drivers drivers;
    Home inicio;
    ConstructoresFragment constructors;
    DrawerLayout drawerlayoutmain;
    LinearLayout linearmain;
    MaterialToolbar materialmain;
    NavigationView navegationmain;
    Toast toast;
    SharedPreferences prefs;
    String titulo ="";
    int itemPesistencia=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creación asociación de layout con el código:

        drawerlayoutmain = findViewById(R.id.drawerlayoutmain);
        linearmain = findViewById(R.id.linearmain);

        materialmain = findViewById(R.id.materialmain);
        navegationmain = findViewById(R.id.navegationmain);

        prefs = getSharedPreferences("perfil", Context.MODE_PRIVATE);
        //----------------------aquí empieza la creación del toggle -----------------------------------------------
        //creación del toogle:
        setSupportActionBar(materialmain);
        materialmain.setTitleTextColor(getColor(R.color.black));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayoutmain, materialmain, R.string.abrir_navigation, R.string.cerrar_navigation);
        // Establecer la imagen personalizada como indicador de inicio
        //agregamos escuchador del toggle:
        drawerlayoutmain.addDrawerListener(toggle);
        toggle.syncState();
        //forzamos nuestra imagen del toggle
        // Obtener una instancia de ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        //----------------------aquí termina la creación del toggle -----------------------------------------------

        //colocamos el escuchador del navigation aquí:
        navegationmain.setNavigationItemSelectedListener(this);
        //aquí preparo el hilo de carga del sonido de carga;


        //aquí lo voy a dejar seleccionado automáticamente:
        MenuItem homeItem = navegationmain.getMenu().getItem(itemPesistencia);
        onNavigationItemSelected(homeItem);

        //interacciones con la cabecera:
        View vistaHeader = navegationmain.getHeaderView(0);
        TextView nameuserheader = vistaHeader.findViewById(R.id.nameUser);
        String nombreUsuario = prefs.getString("user","Fernando Alonso");
        nameuserheader.setText(nombreUsuario);
        vistaHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = "Perfil";
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                reproducirCarga();
                toastCarga();
                PerfilFragment perfil = new PerfilFragment();
                transaction.replace(R.id.linearmain,perfil);
                transaction.commit();
                setTitle(titulo);
                drawerlayoutmain.closeDrawer(GravityCompat.START);
            }
        });


    }
    //para que no se cierre la aplicación cuando el usuario le da hacia atras, si no que se esconda el menú:
    @Override
    public void onBackPressed() {
        if (drawerlayoutmain.isDrawerOpen(GravityCompat.START)){
            drawerlayoutmain.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    public void mostrarFragmento(@NonNull MenuItem item){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (item.getItemId()){
            case R.id.homemenu:
                titulo = "Home";
                itemPesistencia = 0;
                reproducirCarga();
                toastCarga();
                if (inicio==null){
                    inicio= new Home();
                }
                transaction.replace(R.id.linearmain,inicio);
                transaction.commit();
                setTitle(titulo);

                return;
            case R.id.driversmenu:
                titulo = "Pilotos";
                itemPesistencia = 1;
                reproducirCarga();
                toastCarga();
                if (drivers == null){
                    drivers =new Drivers();
                }
                transaction.replace(R.id.linearmain,drivers);
                transaction.commit();
                setTitle(titulo);
                return ;

            case R.id.constructormenu:
                titulo = "Constructores";
                itemPesistencia = 2;
                reproducirCarga();
                toastCarga();
                if (constructors==null){
                    constructors= new ConstructoresFragment();
                }
                transaction.replace(R.id.linearmain,constructors);
                transaction.commit();
                setTitle(titulo);
                return;

            case R.id.favoritemenu:
                itemPesistencia = 3;
                titulo = "Mis Pilotos";
                reproducirCarga();
                toastCarga();
                favoriteFragment=new FavoriteFragment();
                transaction.replace(R.id.linearmain,favoriteFragment);
                transaction.commit();
                setTitle(titulo);
                return;

            case R.id.exitmenu:
                finish();
                return;

            default:
                Log.w("error","no se metido en ninguno de los anteriores");
                return ;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       mostrarFragmento(item);
       drawerlayoutmain.closeDrawer(GravityCompat.START);
        return true;
    }

    public void toastCarga(){
        if (toast != null){
            toast.cancel();
        }
        LayoutInflater inflater = getLayoutInflater();
        View layoutCarga = inflater.inflate(R.layout.carga,null);
        toast=new Toast(this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layoutCarga);
        toast.show();
    }
    //este metodo me sirve para crear el sonido cuando muestra Toast con el gif de carga
    //reproduce la carga del fragmento :)
    //y lo carga en un hilo
    public void reproducirCarga(){
        MediaPlayer soundf1 = MediaPlayer.create(this,R.raw.soundcarga);
        if (soundf1.isPlaying()){
            soundf1.release();
            soundf1=MediaPlayer.create(this,R.raw.soundcarga);
        }
        SoundCarga carga = new SoundCarga(soundf1);
        carga.start();
    }
    //metodos para reconstruir la actividad:

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("titulo",titulo);
        outState.putInt("item",itemPesistencia);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        titulo = savedInstanceState.getString("titulo");
        itemPesistencia = savedInstanceState.getInt("item");
        MenuItem homeItem = navegationmain.getMenu().getItem(itemPesistencia);
        onNavigationItemSelected(homeItem);

    }
}


