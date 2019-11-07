package com.example.pokemon.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.pokemon.dao.CartaDAO;
import com.example.pokemon.database.PokemonDatabase;
import com.example.pokemon.entity.Pokemon;

import java.util.List;

//clase para hacer todas las operaciones contra la BD
public class Repository
{
    private CartaDAO cartaDAO;
    private LiveData<List<Pokemon>> cartasLive;

    public Repository(Context context)
    {
        PokemonDatabase db = PokemonDatabase.getDatabase(context);
        cartaDAO = db.getCartaDAO();
        //fetchUsers();
        cartasLive = cartaDAO.getAllLive();
    }

    /*private void populateDb(){
        for (int i = 0; i <100 ; i++) {
            User user = new User();
            user.apellidos= "Pérez" + i;
            user.nombre= "Paco" + i;
        }
    }*/


    public LiveData<List<Pokemon>> getUsersLive()
    {
        return cartasLive;
    }

    public void insertUser(Pokemon pokemon)
    {
        new InsertThread().execute(pokemon);
    }


    private class InsertThread extends AsyncTask<Pokemon, Void, Void>
    {
        @Override
        protected Void doInBackground(Pokemon... pokemons)
        {
            cartaDAO.insert(pokemons[0]);
            Log.v("holaINSERT", pokemons[0].toString());
            return null;
        }
    }

}