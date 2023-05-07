package com.example.formula1.clasesApi;

import com.example.formula1.guardarDatosApi.MRData;
import com.google.gson.annotations.SerializedName;

public class ErgastResponse<T> {
    @SerializedName("MRData")
    private MRData<T> data;

    public MRData<T> getData() {
        return data;
    }
    public void setData(MRData<T> data) {
        this.data = data;
    }
}
