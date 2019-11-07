package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon.model.MainViewModel;
import com.example.pokemon.model.Repository;

public class pokemonDetalle extends AppCompatActivity {

    private int id;
    MainViewModel viewModel;
    Repository repository;

    TextView etNombre, etAltura, etPeso, etTipo, etHabilidad, etDescripcion;
    ImageView imageView;
    Uri imgUri;
    //atributos Pokemon
    private String nombre, tipo, habilidad, rutaImg, descripcion;
    private int altura, peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detalle);

        initComponents();
        getAtributos();
        mostrarAtributos();
    }

    private void mostrarAtributos() {
        setTitle(nombre);
        etNombre.setText(nombre);
        etAltura.setText(String.valueOf(altura));
        etPeso.setText(String.valueOf(peso));
        etTipo.setText(tipo);
        etHabilidad.setText(habilidad);
        etDescripcion.setText(descripcion);

        imgUri = Uri.parse(rutaImg);
        Glide.with(this)
                .load(imgUri)
                .placeholder(R.drawable.placeholder)
                .override(300, 300)//pruebadeescalado
                .into(imageView);
    }

    private void getAtributos() {

        nombre = getIntent().getStringExtra("PokemonName");
        altura = getIntent().getIntExtra("PokemonHeight", 0);
        peso = getIntent().getIntExtra("PokemonWeight", 0);
        tipo = getIntent().getStringExtra("PokemonType");
        habilidad = getIntent().getStringExtra("PokemonAbility");
        rutaImg = getIntent().getStringExtra("PokemonImg");
        descripcion = getIntent().getStringExtra("PokemonDescription");

    }

    private void initComponents() {

        etNombre = findViewById(R.id.tvNombre);
        etAltura = findViewById(R.id.tvAltura);
        etPeso = findViewById(R.id.tvPeso);
        etTipo = findViewById(R.id.tvTipo);
        etHabilidad = findViewById(R.id.tvHabilidad);
        etDescripcion = findViewById(R.id.tvDescripcion);
        imageView = findViewById(R.id.imageView);


        id = getIntent().getIntExtra("cartaId", 900);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        repository = new Repository(pokemonDetalle.this);

    }
}
