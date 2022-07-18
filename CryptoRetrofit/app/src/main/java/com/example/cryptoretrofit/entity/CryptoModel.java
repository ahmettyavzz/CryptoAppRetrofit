package com.example.cryptoretrofit.entity;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

public class CryptoModel {

    @SerializedName("currency")
    private String currency;

    @SerializedName("price")
    private String price;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
