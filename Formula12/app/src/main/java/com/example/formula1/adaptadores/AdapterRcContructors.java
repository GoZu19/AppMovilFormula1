package com.example.formula1.adaptadores;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formula1.guardarDatosApi.Constructores;
import com.example.formula1.R;

import java.util.ArrayList;

public class AdapterRcContructors extends RecyclerView.Adapter<AdapterRcContructors.ViewHolder>{

    private ArrayList<Constructores> constructores;
    private LayoutInflater inflater;
    private Context context;
    int color;
    int colorText;

    public AdapterRcContructors(ArrayList<Constructores> constructores,Context context) {
        this.constructores = constructores;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.filaconstructor,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Constructores constructor = constructores.get(position);
        //añadir a contenido de la fila:
        holder.nombre_contructor.setText("Nombre: "+constructor.getName());
        holder.cede_constructor.setText("Cede: "+constructor.getNacionalidad());
        holder.nacionalidad_contructor.setText("Nacionalidad: "+constructor.getNacionalidad());
        holder.image_constructor.setImageDrawable(seleccionImage(constructor.getId()));

        //cambiar colores de fondo según el equipo:
        holder.nombre_contructor.setBackgroundColor(color);
        holder.cede_constructor.setBackgroundColor(color);
        holder.nacionalidad_contructor.setBackgroundColor(color);
        holder.linearLayout.setBackgroundColor(color);


        //cambiar colores del texto según el fondo actual:
        holder.nombre_contructor.setTextColor(colorText);
        holder.cede_constructor.setTextColor(colorText);
        holder.nacionalidad_contructor.setTextColor(colorText);
    }

    @Override
    public int getItemCount() {
        return constructores.size();
    }
    public Drawable seleccionImage(String name){
        Drawable foto;

        switch (name){
            case "red_bull":
                foto = context.getDrawable(R.drawable.redbull);
                color = context.getColor(R.color.Red_bull);
                colorText = context.getColor(R.color.white);
                break;

            case "aston_martin":
                foto = context.getDrawable(R.drawable.astonmartin);
                color = context.getColor(R.color.Aston_Martin);
                colorText = context.getColor(R.color.white);
                break;

            case "ferrari":
                foto = context.getDrawable(R.drawable.ferrari);
                color = context.getColor(R.color.Ferrari);
                colorText = context.getColor(R.color.black);
                break;

            case "mercedes":
                foto = context.getDrawable(R.drawable.mercedes);
                color = context.getColor(R.color.Mercedes);
                colorText = context.getColor(R.color.white);
                break;

            case "mclaren":
                foto = context.getDrawable(R.drawable.mclaren);
                color = context.getColor(R.color.Mclaren);
                colorText = context.getColor(R.color.black);
                break;

            case "alpine":
                foto = context.getDrawable(R.drawable.alpine);
                color = context.getColor(R.color.Alpine);
                colorText = context.getColor(R.color.black);
                break;

            case "haas":
                foto = context.getDrawable(R.drawable.haas);
                color = context.getColor(R.color.Hass);
                colorText = context.getColor(R.color.black);
                break;

            case "alfa":
                foto = context.getDrawable(R.drawable.alfaromeo);
                color = context.getColor(R.color.Alfa_Romeo);
                colorText = context.getColor(R.color.white);
                break;

            case "alphatauri":
                foto = context.getDrawable(R.drawable.alphatauri);
                color = context.getColor(R.color.Alpha_Tauri);
                colorText = context.getColor(R.color.white);
                break;

            case "williams":
                foto = context.getDrawable(R.drawable.williams);
                color = context.getColor(R.color.Williams);
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_contructor,nacionalidad_contructor,cede_constructor;
        ImageView image_constructor;
        LinearLayout linearLayout ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_contructor = itemView.findViewById(R.id.nombre_constructor);
            nacionalidad_contructor = itemView.findViewById(R.id.nacionalidad_constructor);
            cede_constructor = itemView.findViewById(R.id.cede_constructor);
            image_constructor = itemView.findViewById(R.id.image_constructor);
            linearLayout = itemView.findViewById(R.id.linearcontructorfoto);
        }
    }
}
