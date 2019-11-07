package com.example.pokemon.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.MainActivity;
import com.example.pokemon.R;
import com.example.pokemon.pokemonDetalle;
import com.example.pokemon.entity.Pokemon;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private LayoutInflater inflater;
    private List<Pokemon> misPokemon;
    private onItemClickListener listener;
    private MainActivity main;
    Context context;

    //INTERFAZ
    public interface onItemClickListener{

        public void onItemClick(Pokemon pokemon);
    }

    //para guardar los datos que vamos a sacar con el adapter
    public MyAdapter(List<Pokemon> misPokemons, onItemClickListener listener) {
        this.misPokemon = misPokemons;
        this.listener = listener;
    }

    public MyAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.my_recycler_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Pokemon pokemon = misPokemon.get(position);
       /* Log.v("hola123", Integer.toString(position));
        Log.v("hola123", pokemon.toString());
        Log.v("hola123", "TODAVIA NO HA ENTRADO");*/

        if(pokemon != null && pokemon.getRutaImg() != null && position>-1) {

            holder.tvNombre.setText(pokemon.getName());
            Uri uri = Uri.parse(pokemon.getRutaImg());
            holder.img.setImageURI(uri);

        }

     holder.cl.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(context, pokemonDetalle.class);
             intent.putExtra("PokemonName", pokemon.getName());
             intent.putExtra("PokemonHeight", pokemon.getHeight());
             intent.putExtra("PokemonWeight", pokemon.getWeight());
             intent.putExtra("PokemonType", pokemon.getType());
             intent.putExtra("PokemonAbility", pokemon.getAbility());
             intent.putExtra("PokemonImg", pokemon.getRutaImg());
             intent.putExtra("PokemonDesciption", pokemon.getDescription());
             context.startActivity(intent);
        }
     });

    }

    //ARREGLAR
    public void setCartas(List<Pokemon> cartasList){
        this.misPokemon = cartasList;
        notifyDataSetChanged();
    }

    //longuitud array datos
    @Override
    public int getItemCount() {

        int elementos=0;
        if (misPokemon != null){
            elementos = misPokemon.size();
        }
        return elementos;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //elementos recycler
        ImageView img;
        TextView tvNombre;
        ConstraintLayout cl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //elementos recycler
            img = itemView.findViewById(R.id.imgRecycler);
            tvNombre = itemView.findViewById(R.id.tvRecycler);
            cl = itemView.findViewById(R.id.clRecyclerView);

        }
    }

}
