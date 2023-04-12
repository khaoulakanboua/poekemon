package com.example.poekemon.pokeapi;

import com.example.poekemon.models.Pokemon;
import com.example.poekemon.models.PokemonReq;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonReq> listePokemon();

    @GET("{indexpoke}/")
    Call<Pokemon> getPokemon(@Path("indexpoke") String indexpoke);
}
