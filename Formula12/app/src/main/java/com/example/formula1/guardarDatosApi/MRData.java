package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

public class MRData<T> {
    @SerializedName("xmlns")
    private String xmlns;
    @SerializedName("series")
    private String series;
    @SerializedName("url")
    private String url;
    @SerializedName("limit")
    private String limit;
    @SerializedName("offset")
    private String offset;
    @SerializedName("total")
    private String total;
    @SerializedName("DriverTable")
    private DriverTable driverTable;

    public DriverTable getDriversTable() {
        return driverTable;
    }

    public void setDrivers(DriverTable driverTable) {
        this.driverTable = driverTable;
    }
}
