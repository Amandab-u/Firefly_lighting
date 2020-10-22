package com.example.firefly.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.firefly.R;
import com.example.firefly.ViewModel.ListaProyectoViewModel;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaProyectos extends AppCompatActivity {

    private ListaProyectoViewModel mPViewModel;
    FloatingActionButton nuevoProyecto;
    RecyclerView recycler;
    ListaProyectosAdapter adapter;

    static final String EXTRA_SIZE = "com.example.firefly.EXTRA_SIZE";
    public static final String TAG= "Prueba";
    public int position;
    public static int idProyecto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyectos);


        int permissionCheck = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListaProyectos.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        nuevoProyecto = findViewById(R.id.nuevo_proyecto);

        recycler = findViewById(R.id.lista_proyectos);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaProyectosAdapter(this);
        recycler.setAdapter(adapter);

        ListaProyectoViewModel.Factory factory = new ListaProyectoViewModel.Factory(getApplication());
        mPViewModel = new ViewModelProvider(this, factory).get(ListaProyectoViewModel.class);

        mPViewModel.getAllProyectos().observe(this, new Observer<List<ProyectoCompleto>>() {
            @Override
            public void onChanged(List<ProyectoCompleto> proyectoCompletos) {

                adapter.setProyectos(proyectoCompletos);

            }
        });



        adapter.setOnItemClickListener(onItemClickListener);

        nuevoProyecto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idProyecto = adapter.getItemCount();
                    Intent nuevoProyectoIntent = new Intent(ListaProyectos.this, DatosProyecto.class);

                    nuevoProyectoIntent.putExtra(EXTRA_SIZE, idProyecto);
                    startActivity(nuevoProyectoIntent);

                }
        });


    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            position = viewHolder.getAdapterPosition();

            Intent seeDetallesLumCat = new Intent(ListaProyectos.this, DatosProyecto.class);
            startActivity(seeDetallesLumCat);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.proyectos_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
//            case R.id.action_insert_dummy_data:
//                insertPet();
//                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                mPViewModel.deleteAllProyectos();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
