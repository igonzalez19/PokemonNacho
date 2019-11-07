package com.example.pokemon.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pokemon.entity.Pokemon;

import java.util.List;

@Dao
public interface CartaDAO {

    @Delete
    int delete(Pokemon pokemon);

    @Update
    int edit(Pokemon pokemon);

    @Insert
    Long insert(Pokemon pokemon);

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    Pokemon get(int id);

    @Query("SELECT * FROM Pokemon;")
    List<Pokemon> getAll();

    @Query("SELECT * FROM Pokemon ORDER BY name,  id desc")
    LiveData<List<Pokemon>> getAllLive();
}
