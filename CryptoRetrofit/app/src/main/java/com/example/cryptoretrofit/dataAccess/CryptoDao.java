package com.example.cryptoretrofit.dataAccess;

import com.example.cryptoretrofit.entity.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoDao {

    //GET POST UPDATE DELETE
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CryptoModel>> getData();
}
