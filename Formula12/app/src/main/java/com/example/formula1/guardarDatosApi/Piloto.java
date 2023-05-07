package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

public class Piloto {
    @SerializedName("driverId")
    private String id;

    @SerializedName("permanentNumber")
    private String numero;

    @SerializedName("code")
    private String codigo;

    @SerializedName("url")
    private String url;

    @SerializedName("givenName")
    private String nombre;

    @SerializedName("familyName")
    private String apellido;

    @SerializedName("dateOfBirth")
    private String fechaNacimiento;

    @SerializedName("nationality")
    private String nacionalidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}

