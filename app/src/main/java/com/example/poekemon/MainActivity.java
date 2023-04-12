package com.example.poekemon;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.poekemon.models.Pokemon;
import com.example.poekemon.models.PokemonReq;
import com.example.poekemon.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData();


    }
    private void getData() {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonReq> pokemonRespuestaCall = service.listePokemon();

        pokemonRespuestaCall.enqueue(new Callback<PokemonReq>() {

            @Override
            public void onResponse(Call<PokemonReq> call, Response<PokemonReq> response) {
                if (response.isSuccessful()) {
                    PokemonReq pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    for(int i=0;i<listaPokemon.size();i++){
                        Pokemon p=listaPokemon.get(i);
                        Log.i(TAG,"Pokemon: " +p.getName());
                    }

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<PokemonReq> call, Throwable t) {

                    Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });
    }

    public void sendMessage(View view) {


        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}