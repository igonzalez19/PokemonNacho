package com.example.pokemon.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.pokemon.entity.Pokemon;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Pokemon>> cartas;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        cartas = repository.getUsersLive();
    }

    public  LiveData<List<Pokemon>> getCartas(){

        return cartas;
    }

    public void insert(Pokemon pokemon){

        repository.insertUser(pokemon);
    }
}
