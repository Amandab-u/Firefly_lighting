package com.example.firefly.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firefly.R;
import com.example.firefly.ViewModel.CatalogoViewModel;
import com.example.firefly.database.relations.CatalogoCompleto;

import java.util.List;

import static com.example.firefly.ui.DatosLuminaria.EXTRA_LUMIN;

public class Catalogo extends AppCompatActivity {

    private CatalogoViewModel mFireViewModel;
    RecyclerView listaLuminariasCatalogo;
    CatalogoAdapter adapter;
    LinearLayoutManager manager;

    static final String EXTRA_POSITION = "com.example.firefly.EXTRA_POSITION";
    static final String EXTRA_PROYECTO = "com.example.firefly.EXTRA_PROYECTO";
    public static final String TAG= "Test";
    public int position;
    public long idProyecto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_catalogo);

        Intent i = getIntent();
        idProyecto = i.getLongExtra(EXTRA_LUMIN,0);

        initRecyclerView();

        CatalogoViewModel.Factory factory = new CatalogoViewModel.Factory(getApplication());

        mFireViewModel = new ViewModelProvider(this, factory).get(CatalogoViewModel.class);


        mFireViewModel.getCatalogoCompleto().observe(this, new Observer<List<CatalogoCompleto>>() {
            @Override
            public void onChanged(@Nullable final List<CatalogoCompleto> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setCatalogo(words);
            }
        });



        adapter.setOnItemClickListener(onItemClickListener);

    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            position = viewHolder.getAdapterPosition();

            Intent seeDetallesLumCat = new Intent(Catalogo.this,DetallesLuminariaCat.class);

            seeDetallesLumCat.putExtra(EXTRA_POSITION, position);
            seeDetallesLumCat.putExtra(EXTRA_PROYECTO,idProyecto);

            startActivity(seeDetallesLumCat);
        }
    };

    private void initRecyclerView (){
        listaLuminariasCatalogo = findViewById(R.id.recyclerview);
        adapter = new CatalogoAdapter(this);
        listaLuminariasCatalogo.setAdapter(adapter);
        manager = new LinearLayoutManager(this);
        listaLuminariasCatalogo.setLayoutManager(manager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listaLuminariasCatalogo.getContext(),
                manager.getOrientation());
        listaLuminariasCatalogo.addItemDecoration(dividerItemDecoration);
    }

}
