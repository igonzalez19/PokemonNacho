package com.example.pokemon;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.pokemon.adapter.MyAdapter;
import com.example.pokemon.entity.Pokemon;
import com.example.pokemon.model.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ID_PERMISO_LEER = 2;
    private static final String TAG = "zxy";

    private RecyclerView myRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private MainViewModel viewModel;
    FloatingActionButton fab;
    TextView tv;

    //foreach para la verificacion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, R.string.tituloExplicacion2, R.string.mensajeExplicacion2);
                if (checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, R.string.tituloExplicacion2, R.string.mensajeExplicacion2)){

                    addActivity();
                }
            }
        });
        checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, R.string.tituloExplicacion2, R.string.mensajeExplicacion2);
        initComponents();
        //tengo que recoger las cartas que habia antes y lograr insertarlas para tener los datos de la bd que tenia antes
        //viewModel.getCartas();
        //viewModel.insert(new Pokemon("nacho", 4, 5, 6, 7, 8, "R.drawable.android"));
    }

    private void initComponents() {
        myRecycler = findViewById(R.id.myRecycler);
        //asignarle un layout manager (my_recycler_view
        layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);
        //asignarle un adaptador
        crearArray();
        final MyAdapter myAdapter = new MyAdapter(this);
        myRecycler.setAdapter(myAdapter);

        //con esta instruccion se engancha el viewmodel con el MainActivity
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        //OBSERVACION
        viewModel.getCartas().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable final List<Pokemon> pokemons) {
                // Update the cached copy of the words in the adapter.
                myAdapter.setCartas(pokemons);
            }
        });
    }

    //PERMISO DE LECTURA
    private boolean checkPermissions(String permiso, int titulo, int mensaje) {
        if (ContextCompat.checkSelfPermission(this, permiso)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
                explain(R.string.tituloExplicacion, R.string.mensajeExplicacion, permiso);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{permiso},
                        ID_PERMISO_LEER);
            }
        } else {
            return true;
        }
        return false;
    }


    private void explain(int title, int message, final String permissions) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.respSi, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permissions}, ID_PERMISO_LEER);
            }
        });
        builder.setNegativeButton(R.string.respNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addActivity(){

        Intent intent = new Intent(this, AddPokemonActivity.class);
        startActivity(intent);
    }

    private void crearArray() {

        //viewModel.insert(new Pokemon(null, "Leopardo", ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
