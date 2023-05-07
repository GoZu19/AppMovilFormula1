package com.example.formula1.clasesApi;

import com.example.formula1.guardarDatosApi.MRDataConstructors;
import com.google.gson.annotations.SerializedName;

public class ErgastResponseConstructor<T> {
    @SerializedName("MRData")
    private MRDataConstructors<T> data;

    public MRDataConstructors<T> getData() {
        return data;
    }
    public void setData(MRDataConstructors<T> data) {
        this.data = data;
    }
}
