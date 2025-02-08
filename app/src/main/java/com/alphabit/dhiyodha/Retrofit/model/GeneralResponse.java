package com.alphabit.dhiyodha.Retrofit.model;

import com.google.gson.annotations.SerializedName;

public class GeneralResponse {

    @SerializedName("$id")
    private String $id;

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("data")
    private String data;

    @SerializedName("errors")
    private String errors;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
