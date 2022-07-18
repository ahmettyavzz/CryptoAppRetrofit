package com.example.cryptoretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.cryptoretrofit.R;
import com.example.cryptoretrofit.adapter.RecyclerViewAdapter;
import com.example.cryptoretrofit.dataAccess.CryptoDao;
import com.example.cryptoretrofit.databinding.ActivityMainBinding;
import com.example.cryptoretrofit.entity.CryptoModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    private ActivityMainBinding binding;
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL="https://raw.githubusercontent.com/";
    Retrofit retrofit;
    CryptoDao cryptoDao;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);


        setRetrofit();
        loadData();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    private void setRetrofit(){
        //JSON
       // Gson gson= new GsonBuilder().setLenient().create();  //createin içine verilebilir ama gerek yok

        //Retrofit oluşturma
        retrofit= new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }



    //Verileri alan metot
    private void loadData(){
        //Service oluşturma
        cryptoDao=retrofit.create(CryptoDao.class);

        Call<List<CryptoModel>> call= cryptoDao.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                    List<CryptoModel> responseList=response.body();
                    cryptoModels= new ArrayList<>(responseList);

                    //RECYCLER VİEW
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter= new RecyclerViewAdapter(cryptoModels);
                    binding.recyclerView.setAdapter(recyclerViewAdapter);



                   /* for (CryptoModel c:cryptoModels){
                        System.out.println(c.getCurrency());
                    }*/


                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ArrayList<CryptoModel> searchedList = new ArrayList<>();
        for(CryptoModel searchedModel:cryptoModels){
          if(searchedModel.getCurrency().toLowerCase().contains(s.toLowerCase())){
                searchedList.add(searchedModel);
          }

        }

        recyclerViewAdapter.setCryptoModelArrayList(searchedList);
        recyclerViewAdapter.notifyDataSetChanged();
        return true;
    }
}
