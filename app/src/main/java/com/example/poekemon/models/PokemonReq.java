package com.example.poekemon.models;

import java.util.ArrayList;

public class PokemonReq {
    private ArrayList<Pokemon> results;
    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
