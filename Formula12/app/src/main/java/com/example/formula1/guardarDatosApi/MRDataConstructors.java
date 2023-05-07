package com.example.formula1.guardarDatosApi;

import com.google.gson.annotations.SerializedName;

public class MRDataConstructors<T> {
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
    @SerializedName("ConstructorTable")
    private ConstructorTable constructorTable;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ConstructorTable getConstructorTable() {
        return constructorTable;
    }

    public void setConstructorTable(ConstructorTable constructorTable) {
        this.constructorTable = constructorTable;
    }
}
