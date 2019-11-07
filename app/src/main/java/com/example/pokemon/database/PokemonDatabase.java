package com.example.pokemon.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemon.dao.CartaDAO;
import com.example.pokemon.entity.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase
{
    public abstract CartaDAO getCartaDAO();

    private static volatile PokemonDatabase INSTANCE;

    public static PokemonDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized(PokemonDatabase.class)
            {
                if(INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PokemonDatabase.class, "database.db").build();
            }
        }
        return INSTANCE;
    }
}
