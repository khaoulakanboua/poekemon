package com.example.poekemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.poekemon.models.Pokemon;
import com.example.poekemon.pokeapi.PokeapiService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;

public class MainActivity2 extends AppCompatActivity {
Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();

        String id = extras.getString("index");

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData(id);
}
    private void getData(String pos) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<Pokemon> poke = service.getPokemon(pos);
        poke.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                TextView txt = findViewById(R.id.namee);
                TextView txt2 = findViewById(R.id.txt1);
                TextView txt3 = findViewById(R.id.txt2);
                TextView txt5 = findViewById(R.id.txt5);
                ImageView img = findViewById(R.id.img);
                Pokemon p = response.body();
                txt.setText(p.getName());
                txt2.setText(p.getWeight()+"Kg");
                txt3.setText(p.getHeight()+"M");
                txt5.setText(p.getType());

                //txt3.setText(p.getGameIndex());
                Glide.with(MainActivity2.this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pos+".png").into(img);

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

    }

}