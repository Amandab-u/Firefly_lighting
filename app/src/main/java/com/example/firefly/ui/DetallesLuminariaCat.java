package com.example.firefly.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.DetallesLumCatViewModel;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.firefly.ui.Catalogo.EXTRA_POSITION;
import static com.example.firefly.ui.Catalogo.EXTRA_PROYECTO;

public class DetallesLuminariaCat extends AppCompatActivity {

    static final String EXTRA_DETALLES= "com.example.firefly.EXTRA_DETALLES";
    static final String EXTRA_LUMIN_SELEC= "com.example.firefly.EXTRA_LUMIN_SELEC";

    private DetallesLumCatViewModel mFireViewModel;
    int posicionCat;
    int idLuminariaSelec;
    public long idProyecto;
    String name;
    double potenciaNominal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_luminaria_cat);

        Intent i = getIntent();

        posicionCat = i.getIntExtra(EXTRA_POSITION, 0);
        idProyecto = i.getLongExtra(EXTRA_PROYECTO, 0);

        TextView nombre = findViewById(R.id.nombre_luminaria);
        TextView descripcion = findViewById(R.id.descripcion_luminaria);
        ImageView imagenReal = findViewById(R.id.luminaria_real);
        ImageView imagenFoto = findViewById(R.id.fotometria_catalogo);
        TextView size = findViewById(R.id.size_luminaria);
        TextView balasto = findViewById(R.id.balasto_luminaria);
        TextView flujoTotal = findViewById(R.id.flujo_luminaria_total);
        TextView ratio = findViewById(R.id.ratio_luminaria);
        TextView flujo = findViewById(R.id.flujo_luminaria);
        TextView potencia = findViewById(R.id.potencia_luminaria);
        TextView irc = findViewById(R.id.irc_luminaria);
        TextView temperatura = findViewById(R.id.temperatura_luminaria);
        TextView difusor = findViewById(R.id.difusor_luminaria);
        EditText alturaColgante = findViewById(R.id.altura_colgante);

        setupViewModel();

        mFireViewModel.getLuminariaDetallada().observe(this, new Observer<CatalogoCompleto>() {
            @Override
            public void onChanged(@Nullable final CatalogoCompleto words) {
                // Update the cached copy of the words in the adapter.
                name = words.catalogo.getNombre();
                nombre.setText(name);
                descripcion.setText(words.catalogo.getDescripcion());

                name += " "+ words.catalogo.getDescripcion();
                imagenReal.setImageResource(words.catalogo.getImagenLumReal());
                imagenFoto.setImageResource(words.catalogo.getImagenFotometria());

                if (words.balastos != null) {
                    balasto.setVisibility(View.VISIBLE);
                    balasto.setText("Balasto: " + words.balastos.getBalasto());
                } else {
                    balasto.setVisibility(View.GONE);
                }

                String dimensiones = "Dimensiones: ";
                if (words.diametro != null) {
                    dimensiones = dimensiones + " Diametro: " + words.diametro.getDiametro() + ";";
                }
                if (words.altos != null) {
                    dimensiones = dimensiones + " Alto: " + words.altos.getAlto() + ";";
                }
                if (words.largos != null) {
                    dimensiones = dimensiones + " Largo: " + words.largos.getLargo() + ";";
                }
                if (words.anchos != null) {
                    dimensiones = dimensiones + " Ancho: " + words.anchos.getAncho() + ";";
                }
                if (words.profundidades != null) {
                    dimensiones = dimensiones + " Profundidad: " + words.profundidades.getProfundidad();
                }

                size.setText(dimensiones);

                String flujoTotalString = "Flujo Luminoso Total: " + words.catalogo.getFlujoLumTotal() + "lm";
                flujoTotal.setText(flujoTotalString);

                String ratioString = "Light Output Rate: " + words.catalogo.getLightOutputRate();
                ratio.setText(ratioString);

                String flujoString = "Flujo Luminoso: " + words.catalogo.getFlujoLuminoso() + "lm";
                flujo.setText(flujoString);

                potenciaNominal = words.catalogo.getPotenciaElectrica();
                String potenciaString = "Potencia Electrica: " + words.catalogo.getPotenciaElectrica() + "W";
                potencia.setText(potenciaString);

                String ircString = "IRC: " + words.catalogo.getIndiceRc() + "%";
                irc.setText(ircString);

                String temperaturaString = "Temperatura de Color: " + words.catalogo.getTemperaturaColor() + "K";
                temperatura.setText(temperaturaString);


                if (words.catalogo.difusor != null) {
                    String difusorString = "Difusor: " + words.catalogo.getDifusor();
                    difusor.setText(difusorString);
                }

            }
        });

        Button seleccionar = findViewById(R.id.seleccionar_luminaria);

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mFireViewModel.updateLuminariaSelec(idLuminariaSelec,idProyecto,name,potenciaNominal);
                    Intent irAresultados = new Intent(DetallesLuminariaCat.this, ResultadosBasicos.class);
                    irAresultados.putExtra(EXTRA_DETALLES,idProyecto);
                    irAresultados.putExtra(EXTRA_LUMIN_SELEC,idLuminariaSelec);
                    startActivity(irAresultados);


            }
        });

    }

    private void setupViewModel() {

        idLuminariaSelec = posicionCat +1;
        DetallesLumCatViewModel.DetallesViewModelFactory factory =
                new DetallesLumCatViewModel.DetallesViewModelFactory(getApplication(), idLuminariaSelec);

        mFireViewModel = new ViewModelProvider(this, factory).get(DetallesLumCatViewModel.class);
    }

}
