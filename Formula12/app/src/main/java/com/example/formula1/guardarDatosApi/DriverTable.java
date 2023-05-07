package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverTable {
    @SerializedName("driverId")
    private String driverId;
    @SerializedName("Drivers")
    private List<Piloto> drivers;

    public List getDrivers() {
        return drivers;
    }

    public void setDrivers(List drivers) {
        this.drivers = drivers;
    }

}
