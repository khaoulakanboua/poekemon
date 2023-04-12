package com.example.poekemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.poekemon.interfacee.IItemClickListener;
import com.example.poekemon.models.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{
    private ArrayList<Pokemon> dataset;
    private Context context;
    public PokemonAdapter(Context context) {
        this.context=context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main6, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fotoImageView;
        private TextView nombreTextView;
        IItemClickListener iTemClickListener;
        public void setiItemClickListener(IItemClickListener iTemClickListener){
            this.iTemClickListener=iTemClickListener;
        }
        public ViewHolder(View itemView) {
            super(itemView);


            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);

        }
    }
}
