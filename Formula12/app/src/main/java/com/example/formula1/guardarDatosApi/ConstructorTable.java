package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConstructorTable {
    @SerializedName("constructorId")
    private String constructorId;
    @SerializedName("Constructors")
    private List<Constructores> constructores;

    public String getConstructorId() {
        return constructorId;
    }

    public void setConstructorId(String constructorId) {
        this.constructorId = constructorId;
    }

    public List<Constructores> getConstructores() {
        return constructores;
    }

    public void setConstructores(List<Constructores> constructores) {
        this.constructores = constructores;
    }
}
