package com.example.firefly.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.CatalogoViewModel;
import com.example.firefly.ViewModel.DatosProyectoViewModel;
import com.example.firefly.database.entity.ProyectosEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import static com.example.firefly.ui.ListaProyectos.EXTRA_SIZE;

public class DatosProyecto extends AppCompatActivity {

    static final String EXTRA_PROYECTO = "com.example.firefly.EXTRA_PROYECTO";


    MaterialEditText nombreProyecto;
    String nombreDb;
    String regex = "^[a-zA-Z0-9\\s]*$";
    DatosProyectoViewModel mDatosPrViewModel;
    CatalogoViewModel mCatalogoViewModel;

    RadioGroup mOpcionesAE;
    RadioGroup mOpcionesEnc;
    RadioGroup mOpciionesVer;
    FloatingActionButton irADatosLocal;
    ProyectosEntity proyecto;

    Boolean siAE;
    Boolean siEnc;
    Boolean siVer;
    public static int idProyecto;
    public static int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_proyecto);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        size = i.getIntExtra(EXTRA_SIZE, 0);

        setupViews();
        setUpViewModel();

        irADatosLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initError(nombreProyecto,"No se aceptan simbolos (#@$%?-)",regex);
                if (nombreProyecto.getText().toString().matches(regex)) {
                    nombreDb = nombreProyecto.getText().toString().trim();
                }

                Intent verDatosLocal = new Intent(DatosProyecto.this, DatosLocal.class);



                if (!TextUtils.isEmpty(nombreDb)) {
                    if (size > 0) {
                        idProyecto = size + 1;
                    } else {
                        idProyecto = 1;
                    }

                    createProyecto(nombreDb, idProyecto);
                    if (!(proyecto == null)) {
                        mDatosPrViewModel.insertProyecto(proyecto);
                        Toast.makeText(DatosProyecto.this, "Proyecto insertado con exito id: " + idProyecto, Toast.LENGTH_SHORT).show();
                        verDatosLocal.putExtra(EXTRA_PROYECTO, idProyecto);
                        startActivity(verDatosLocal);
                    } else {
                        Toast.makeText(DatosProyecto.this, "No se inserto proyecto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DatosProyecto.this, "No se inserto proyecto", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void initError(MaterialEditText editText, CharSequence error, String reg) {
        RegexpValidator validationEditText= new RegexpValidator(String.valueOf(error), reg);
        editText.validateWith(validationEditText);
    }

    private void createProyecto(String nombre, int id) {



        int h= mOpcionesAE.getCheckedRadioButtonId();
        if (h == R.id.si_analisis_electrico) {
            siAE = true;
        } else if (h == R.id.no_analisis_electrico) {
            siAE = false;
        }

        int j= mOpcionesEnc.getCheckedRadioButtonId();
        if (j == R.id.si_encuesta) {
            siEnc = true;
        } else if (j == R.id.no_encuesta) {
            siEnc = false;
        }

        int k= mOpciionesVer.getCheckedRadioButtonId();
        if (k == R.id.si_verificacion) {
            siVer = true;
        } else if (k == R.id.no_verificacion) {
            siVer = false;
        }


        if (!(siAE == null) && !(siEnc == null) && !(siVer == null)) {
            proyecto = new ProyectosEntity(id, nombre, siAE, siEnc, siVer);
        } else {
            Toast.makeText(DatosProyecto.this, "Debe responder todas las preguntas", Toast.LENGTH_SHORT).show();
        }


    }

    private void setUpViewModel() {

        DatosProyectoViewModel.DatosProyectoViewModelFactory factory =
                new DatosProyectoViewModel.DatosProyectoViewModelFactory(getApplication());

        mDatosPrViewModel = new ViewModelProvider(this, factory).get(DatosProyectoViewModel.class);


    }

    private void setupViews() {
        nombreProyecto = findViewById(R.id.insert_nombre_proyecto);

        irADatosLocal = findViewById(R.id.comenzar_analisis);
        mOpcionesAE = findViewById(R.id.opciones_analisis_electrico);
        mOpcionesEnc = findViewById(R.id.opciones_encuesta);
        mOpciionesVer = findViewById(R.id.opciones_verificacion);

    }
}
