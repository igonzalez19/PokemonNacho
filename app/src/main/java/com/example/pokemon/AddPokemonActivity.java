package com.example.pokemon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemon.adapter.MyAdapter;
import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.model.MainViewModel;

import java.util.List;

public class AddPokemonActivity extends AppCompatActivity {

    private static final int PHOTO_SELECTED=1;

    //DATOS DE LA CARTA
    private String nombre, rutaImg, tipo, habilidad, descripcion;
    private Uri  uriImg;
    private int altura, peso;

    EditText etNombre, etAltura, etPeso, etTipo, etHabilidad, etDescripcion;
    Button btInsertar;
    private MainViewModel viewModel;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        initComponents();
        initEvents();

    }

    private void initEvents() {

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getString();


                viewModel.insert(new Pokemon(nombre, altura, peso, tipo, habilidad, rutaImg, descripcion));

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "parece que ha ido guauy", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subirFoto();
            }
        });

    }

    private void getString(){

        nombre = etNombre.getText().toString();
        altura = Integer.parseInt(etAltura.getText().toString());
        peso = Integer.parseInt(etPeso.getText().toString());
        tipo = etTipo.getText().toString();
        habilidad = etHabilidad.getText().toString();
        descripcion = etDescripcion.getText().toString();
        rutaImg = uriImg.toString(); //Uri.parse(String.valueOf(img));
    }

    private void initComponents() {

        btInsertar = findViewById(R.id.btInsertar);
        etNombre = findViewById(R.id.tvNombre);
        etAltura = findViewById(R.id.etAltura);
        etPeso = findViewById(R.id.etPeso);
        etTipo = findViewById(R.id.etTipo);
        etHabilidad = findViewById(R.id.etHabilidad);
        etDescripcion = findViewById(R.id.etDescripcion);
        img = findViewById(R.id.addImg);


        final MyAdapter myAdapter = new MyAdapter(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getCartas().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable final List<Pokemon> pokemons) {
                // Update the cached copy of the words in the adapter.
                myAdapter.setCartas(pokemons);
            }
        });


    }

    private void subirFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PHOTO_SELECTED);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_SELECTED && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri imageUri = data.getData();
            uriImg = imageUri;
            Glide.with(this)
                    .load(imageUri)
                    .placeholder(R.drawable.placeholder)
                    .override(500, 500)//pruebadeescalado
                    .into(img);
        }
    }
}
