package com.example.firefly.ui;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.DatosLuminViewModel;
import com.example.firefly.ViewModel.DatosProyectoViewModel;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.firefly.R.color.SecondaryDarkColor;
import static com.example.firefly.R.color.colorAccent;
import static com.example.firefly.R.color.secondaryColor;
import static com.example.firefly.ui.DatosLocal.EXTRA_LOCAL;

public class DatosLuminaria extends AppCompatActivity {

    public String TAG = "firefly.datosluminaria";
    public String TAG_X = "coordenada x";
    public String TAG_Y = "coordenada y";
    static final String EXTRA_LUMIN = "com.example.firefly.EXTRA_LUMIN";
    public long idProyecto;
    public int numLuminarias;
    static final String ID_PROYECTO = "idProyecto";

    public int idLuminaria;
    public double coordX;
    public double coordY;
    public double coordZ;

    TextView luminaria;
    EditText coordenadaX;
    EditText coordenadaY;
    EditText coordenadaZ;
    LinearLayout layout;

    boolean existingViews;

    List<DatosLuminariaEntity> luminariasProyecto = new ArrayList<>();


    DatosLuminViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_luminaria);


        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null) {
            idProyecto = extras.getLong(EXTRA_LOCAL);
        }



        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        setUpViewModel(idProyecto);

        mViewModel.getNumLuminarias().observe(this, new Observer<ProyectoCompleto>() {
            @Override
            public void onChanged(ProyectoCompleto datosLocalEntity) {

                if (datosLocalEntity != null ) {
                    numLuminarias = datosLocalEntity.datosLocal.getNumeroLuminarias();
                    setLuminariaList(numLuminarias);
                }
            }
        });


        FloatingActionButton calcular = findViewById(R.id.calcular);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setDatosLuminaria();
                if (luminariasProyecto.size() > 0) {
                    insertLuminarias(luminariasProyecto);
//                    Toast.makeText(DatosLuminaria.this, "Coordenada Y= "+luminariasProyecto.get(1).getCoordenadaY()
//                            +"\n"+"Coordenada X= "+luminariasProyecto.get(1).getCoordenadaX(), Toast.LENGTH_LONG).show();

                    Intent goToCatalogo = new Intent(DatosLuminaria.this, Catalogo.class);
                    goToCatalogo.putExtra(EXTRA_LUMIN, idProyecto);
                    startActivity(goToCatalogo);
                } else {
                    Toast.makeText(DatosLuminaria.this, "Debe introducir ubicacion de luminarias", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void insertLuminarias(List<DatosLuminariaEntity> lumin) {

        mViewModel.insertDatosLuminarias(lumin);

    }

    private void setDatosLuminaria() {
        luminariasProyecto.clear();
        EditText coordeZ = findViewById(R.id.coordenada_z);
        String z = coordeZ.getText().toString();
        if (!TextUtils.isEmpty(z)) {
            coordZ = Double.parseDouble(z);
        }
        for (int i = 1; i <= numLuminarias; i++) {
            EditText coordeX = layout.findViewWithTag(TAG_X+i);
            String x = coordeX.getText().toString();
            if (!TextUtils.isEmpty(x)) {
                coordX = Double.parseDouble(x);
            }

            EditText coordeY = layout.findViewWithTag(TAG_Y+i);
            String y = coordeY.getText().toString();
            if (!TextUtils.isEmpty(y)) {
                coordY = Double.parseDouble(y);
            }


            if (coordX > 0 && coordY > 0 && coordZ > 0) {
                DatosLuminariaEntity proyecto = new DatosLuminariaEntity(idProyecto, 0, coordX, coordY, coordZ, "name",0);
                luminariasProyecto.add(proyecto);
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setUpViewModel(long id) {

        DatosLuminViewModel.Factory factory =
                new DatosLuminViewModel.Factory(getApplication(), id);

        mViewModel = new ViewModelProvider(this, factory).get(DatosLuminViewModel.class);
    }

    @SuppressLint("ResourceAsColor")
    private void setLuminariaList(int numero) {

        layout = findViewById(R.id.relative_layout);

        if (layout.getChildCount()==0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout.LayoutParams lc = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            coordenadaZ = new EditText(getApplicationContext());
            coordenadaZ.setLayoutParams(lc);
            coordenadaZ.setId(R.id.coordenada_z);
            coordenadaZ.setHint("Coordenada z");
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(secondaryColor));
            ViewCompat.setBackgroundTintList(coordenadaZ, colorStateList);
            coordenadaZ.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            layout.addView(coordenadaZ);

            if (numero > 0) {

                for (int i = 1; i <= numero; i++) {
                    luminaria = new TextView(getApplicationContext());
                    luminaria.setLayoutParams(lp);
                    luminaria.setText("Luminaria " + i);
                    luminaria.setTextAppearance(R.style.questionStyle);
                    layout.addView(luminaria);

                    coordenadaX = new EditText(getApplicationContext());
                    coordenadaX.setLayoutParams(lc);
                    coordenadaX.setId(View.generateViewId());
                    coordenadaX.setHint("Coordenada x");
                    coordenadaX.setTag(TAG_X + i);

                    ViewCompat.setBackgroundTintList(coordenadaX, colorStateList);
                    coordenadaX.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    layout.addView(coordenadaX);

                    coordenadaY = new EditText(getApplicationContext());
                    coordenadaY.setLayoutParams(lc);
                    coordenadaY.setId(View.generateViewId());
                    coordenadaY.setHint("coordenada y");
                    coordenadaY.setTag(TAG_Y + i);
                    ViewCompat.setBackgroundTintList(coordenadaY, colorStateList);
                    coordenadaY.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    layout.addView(coordenadaY);

                }


            }

        }
    }
}
