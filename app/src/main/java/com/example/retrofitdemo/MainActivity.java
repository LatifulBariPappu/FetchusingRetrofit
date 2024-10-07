package com.example.retrofitdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitdemo.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String url="https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.demoTv.setText("");

        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        MyApi myApi=retrofit.create(MyApi.class);
        Call<List<model>> call=myApi.getModels();
        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                List<model> list=response.body();
                for(int i=0;i<list.size();i++){
                    binding.demoTv.append("Id : "+list.get(i).getId()+"\nTitle : "+list.get(i).getTitle()+"\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {

            }
        });
    }
}