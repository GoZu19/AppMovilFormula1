package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

public class Constructores {
    @SerializedName("constructorId")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private  String name;
    @SerializedName("nationality")
    private String nacionalidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
