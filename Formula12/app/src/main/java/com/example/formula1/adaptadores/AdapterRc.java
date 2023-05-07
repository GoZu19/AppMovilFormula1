package com.example.formula1.adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formula1.conexionBBDD.AdminBBDD;
import com.example.formula1.conexionBBDD.Conexiones;
import com.example.formula1.guardarDatosApi.Piloto;
import com.example.formula1.R;

import java.util.List;

public class AdapterRc extends RecyclerView.Adapter<AdapterRc.ViewHolder>{
    private List<Piloto> pilotos;
    private LayoutInflater inflater;
    private Context context;
    public int pos;
    int color;
    int colorText;
    private int opcionMenu;
    AdminBBDD adminBBDD;
    SQLiteDatabase db;
    Toast toast;
    public AdapterRc(List<Piloto> pilotos, Context context,int opcionMenu) {
        this.pilotos = pilotos;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.color =context.getColor(R.color.white);
        this.colorText = context.getColor(R.color.black);
        this.opcionMenu = opcionMenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.filadriver,parent,false);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Piloto piloto = pilotos.get(position);

        //añadir a contenido de la fila:
        holder.nombreDriver.setText("Nombre: "+piloto.getNombre());
        holder.apellidoDriver.setText("Apellido: "+piloto.getApellido());
        holder.nacionalidadDriver.setText("Nacionalidad: "+piloto.getNacionalidad());
        holder.dateDriver.setText("Fecha: "+piloto.getFechaNacimiento());
        holder.numeroDriver.setText("Nº: "+piloto.getNumero());
        holder.fotoDriver.setImageDrawable(seleccionImage(piloto.getApellido()));


        //cambiar colores de fondo según el equipo:
        holder.nombreDriver.setBackgroundColor(color);
        holder.apellidoDriver.setBackgroundColor(color);
        holder.nacionalidadDriver.setBackgroundColor(color);
        holder.dateDriver.setBackgroundColor(color);
        holder.numeroDriver.setBackgroundColor(color);
        holder.linearLayoutfoto.setBackgroundColor(color);
        holder.cardPiloto.setCardBackgroundColor(color);

        //cambiar colores del texto según el fondo actual:
        holder.nombreDriver.setTextColor(colorText);
        holder.apellidoDriver.setTextColor(colorText);
        holder.nacionalidadDriver.setTextColor(colorText);
        holder.dateDriver.setTextColor(colorText);
        holder.numeroDriver.setTextColor(colorText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opcionMenu==1){

                    adminBBDD = new AdminBBDD(context, "Favorito",null,1);
                    db = adminBBDD.getWritableDatabase();

                    AlertDialog.Builder alertdialogadd = new AlertDialog.Builder(v.getContext());
                    alertdialogadd.setTitle(context.getString(R.string.titulo_alertDialog_agregar));
                    alertdialogadd.setMessage(context.getString(R.string.mensaje_alertDialog_agregar)+piloto.getNombre());
                    alertdialogadd.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Conexiones agregarBBDD = new Conexiones(db);
                                agregarBBDD.guardarPilotoFavorito(piloto);
                                toastTexto("Se agregado el piloto: "+piloto.getNombre());

                            }catch (SQLiteConstraintException e){
                                toastTexto("Existe el piloto: "+piloto.getNombre());

                            }


                        }
                    });
                    alertdialogadd.setNegativeButton("Cancelar",null);
                    AlertDialog dialog = alertdialogadd.create();
                    dialog.show();
                } else if (opcionMenu==2) {



                    AlertDialog.Builder alertdialogdell = new AlertDialog.Builder(v.getContext());
                    alertdialogdell.setTitle(context.getString(R.string.titulo_alertDialog_eliminar));
                    alertdialogdell.setMessage(context.getString(R.string.mensaje_alertDialog_eliminar)+piloto.getNombre());
                    alertdialogdell.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Thread mihilo = new Thread(()->{
                                adminBBDD = new AdminBBDD(context, "Favorito",null,1);
                                db = adminBBDD.getWritableDatabase();
                                Conexiones agregarBBDD = new Conexiones(db);
                                agregarBBDD.eliminarPilotoFavorito(piloto);
                            });
                            mihilo.start();
                            int pos = position;

                            pilotos.remove(pos);
                            notifyItemRemoved(pos);
                            notifyDataSetChanged();
                            toastTexto("Se quito el piloto: "+piloto.getNombre());

                        }
                    });
                    alertdialogdell.setNegativeButton("Cancelar",null);
                    AlertDialog dialog = alertdialogdell.create();
                    dialog.show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pilotos.size();
    }

    public Drawable seleccionImage(String name){
        Drawable foto;

        switch (name){
            case "Verstappen":
                foto = context.getDrawable(R.drawable.verstapen);
                color = context.getColor(R.color.Red_bull);
                colorText = context.getColor(R.color.white);
                break;

            case "Pérez":
                foto = context.getDrawable(R.drawable.perez);
                color = context.getColor(R.color.Red_bull);
                colorText = context.getColor(R.color.white);
                break;

            case "Alonso":
                foto = context.getDrawable(R.drawable.alonso);
                color = context.getColor(R.color.Aston_Martin);
                colorText = context.getColor(R.color.white);
                break;

            case "Hamilton":
                foto = context.getDrawable(R.drawable.hamilton);
                color = context.getColor(R.color.Mercedes);
                colorText = context.getColor(R.color.white);
                break;

            case "Sainz":
                foto = context.getDrawable(R.drawable.sainz);
                color = context.getColor(R.color.Ferrari);
                colorText = context.getColor(R.color.black);
                break;

            case "Stroll":
                foto = context.getDrawable(R.drawable.stroll);
                color = context.getColor(R.color.Aston_Martin);
                colorText = context.getColor(R.color.white);
                break;

            case "Russell":
                foto = context.getDrawable(R.drawable.rusell);
                color = context.getColor(R.color.Mercedes);
                colorText = context.getColor(R.color.white);
                break;

            case "Norris":
                foto = context.getDrawable(R.drawable.norris);
                color = context.getColor(R.color.Mclaren);
                colorText = context.getColor(R.color.black);
                break;

            case "Hülkenberg":
                foto = context.getDrawable(R.drawable.hulkenberg);
                color = context.getColor(R.color.Hass);
                colorText = context.getColor(R.color.black);
                break;

            case "Leclerc":
                foto = context.getDrawable(R.drawable.leclerc);
                color = context.getColor(R.color.Ferrari);
                colorText = context.getColor(R.color.black);
                break;

            case "Bottas":
                foto = context.getDrawable(R.drawable.bottas);
                color = context.getColor(R.color.Alfa_Romeo);
                colorText = context.getColor(R.color.white);
                break;

            case "Ocon":
                foto = context.getDrawable(R.drawable.ocon);
                color = context.getColor(R.color.Alpine);
                colorText = context.getColor(R.color.black);
                break;

            case "Piastri":
                foto = context.getDrawable(R.drawable.piastri);
                color = context.getColor(R.color.Mclaren);
                colorText = context.getColor(R.color.black);
                break;

            case "Gasly":
                foto = context.getDrawable(R.drawable.gasly);
                color = context.getColor(R.color.Alpine);
                colorText = context.getColor(R.color.black);
                break;

            case "Zhou":
                foto = context.getDrawable(R.drawable.zhou);
                color = context.getColor(R.color.Alfa_Romeo);
                colorText = context.getColor(R.color.white);
                break;

            case "Tsunoda":
                foto = context.getDrawable(R.drawable.tsunoda);
                color = context.getColor(R.color.Alpha_Tauri);
                colorText = context.getColor(R.color.white);
                break;

            case "Magnussen":
                foto = context.getDrawable(R.drawable.magnussen);
                color = context.getColor(R.color.Hass);
                colorText = context.getColor(R.color.black);
                break;

            case "Albon":
                foto = context.getDrawable(R.drawable.albon);
                color = context.getColor(R.color.Williams);
                colorText = context.getColor(R.color.white);
                break;

            case "Sargeant":
                foto = context.getDrawable(R.drawable.sergeant);
                color = context.getColor(R.color.Williams);
                colorText = context.getColor(R.color.white);
                break;

            case "de Vries":
                foto = context.getDrawable(R.drawable.devries);
                color = context.getColor(R.color.Alpha_Tauri);
                colorText = context.getColor(R.color.white);
                break;
            default:
                foto = context.getDrawable(R.drawable.resource_default);
                color = context.getColor(R.color.white);
                colorText = context.getColor(R.color.black);
                break;
        }
        return foto;
    }

    //toast de texto para cancelarlo su uso:
    public void toastTexto(String mensaje){
        if (toast !=null){
            toast.cancel();
        }
        toast =new Toast(context);
        toast.setText(mensaje);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombreDriver,apellidoDriver,nacionalidadDriver, dateDriver, numeroDriver;
        ImageView fotoDriver;
        LinearLayout linearLayoutfoto;
        CardView cardPiloto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreDriver = itemView.findViewById(R.id.id_driver);
            apellidoDriver = itemView.findViewById(R.id.apellido_driver);
            nacionalidadDriver = itemView.findViewById(R.id.nacionalidad_driver);
            dateDriver = itemView.findViewById(R.id.date_diver);
            numeroDriver = itemView.findViewById(R.id.numero_driver);
            linearLayoutfoto = itemView.findViewById(R.id.lineardriverfoto);
            fotoDriver = itemView.findViewById(R.id.foto_driver);
            cardPiloto = itemView.findViewById(R.id.cardPiloto);
        }
    }
}
