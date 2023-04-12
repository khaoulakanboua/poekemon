package com.example.poekemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.poekemon.models.Pokemon;
import com.example.poekemon.models.PokemonReq;
import com.example.poekemon.pokeapi.PokeapiService;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity5 extends AppCompatActivity {
    TextView txt,txt1;

    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private PokemonAdapter listaPokemonAdapter;

    private Retrofit retrofit;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        recyclerView = findViewById(R.id.recyclerView);
        listaPokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity5.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(MainActivity5.this,MainActivity2.class);
                intent2.putExtra("index",String.valueOf(position+1));

                img= findViewById(R.id.fotoImageView);
                startActivity(intent2);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));


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
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);
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
        txt = findViewById(R.id.nombreTextView);
        ImageView img =findViewById(R.id.fotoImageView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= (String) txt.getText();
                Intent i = new Intent(v.getContext(), MainActivity2.class);
                i.putExtra("key",id);
                startActivity(i);

            }
        });

    }
}