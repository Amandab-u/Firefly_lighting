package com.example.firefly.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.AElectricoViewModel;
import com.example.firefly.ViewModel.VerificacionViewModel;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.VerificacionEntity;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;

import static com.example.firefly.ui.AnalisisElectrico.EXTRA_ID_AE;
import static com.example.firefly.ui.ConfortVisual.EXTRA_ID_CV;
import static com.example.firefly.ui.ResultadosBasicos.EXTRA_ID_PROY;

public class Verificacion extends AppCompatActivity {

    public long idProyecto;
    static final String EXTRA_ID_V = "com.example.firefly.EXTRA_ID_V";
    public String TAG_ILUM="iluminancia";
    VerificacionViewModel mViewModel;

    TextView prueba;
    FloatingActionButton iraResultados;
    TextView coordx;
    TextView coordy;
    TextView pts;
    MaterialEditText iluminancia;
    LinearLayout layout;

    int numeroPuntosAnalisis;
    String regex = "^[-+]?\\d+(\\.\\d+)?$";
    CharSequence error;


    List<PuntosAnalisisEntity> puntos;
    List<VerificacionEntity> medida = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);

        Intent in = getIntent();
        if (getCallingActivity()!=null){
            String className =  getCallingActivity().getClassName();
            switch (className){
                case "com.example.firefly.ui.ResultadosBasicos":
                    idProyecto = in.getLongExtra(EXTRA_ID_PROY, 0);
                    break;
                case "com.example.firefly.ui.AnalisisElectrico":
                    idProyecto = in.getLongExtra(EXTRA_ID_AE, 0);
                    break;
                case "com.example.firefly.ui.ConfortVisual":
                    idProyecto = in.getLongExtra(EXTRA_ID_CV, 0);
                    break;
            }
        }
        setupViewModel();
        setupViews();

        mViewModel.getProyectoCompleto(idProyecto).observe(this, new Observer<ProyectoCompleto>() {
            @SuppressLint("NewApi")
            @Override
            public void onChanged(ProyectoCompleto proyectoCompleto) {
                numeroPuntosAnalisis = proyectoCompleto.puntos.size();


                puntos = proyectoCompleto.puntos;


                addViews(puntos);

            }
        });

        iraResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateEditText()) {
                    getIluminancias();
                    if (medida != null) {
                        mViewModel.insertIluminaMedida(medida);
                        Toast.makeText(Verificacion.this, "Insertado con exito", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Verificacion.this, ResultadosFinales.class);
                        i.putExtra(EXTRA_ID_V, idProyecto);
                        startActivityForResult(i, 0);
                    }
                }

            }
        });

    }

    private boolean validateEditText() {
        List<Boolean> validados= new ArrayList<>();
        for (int s=0; s<numeroPuntosAnalisis; s++){
            boolean isvalid;
            MaterialEditText iluminanMedida= (MaterialEditText) layout.findViewWithTag(TAG_ILUM+s);
            String textMedida = Objects.requireNonNull(iluminanMedida.getText().toString());
            isvalid = textMedida.matches(regex);

            if (isvalid){
                validados.add(true);
            }
            initError(iluminanMedida,error,regex);
        }
        return validados.size() == numeroPuntosAnalisis;
    }

    private void getIluminancias() {
        double iluminancia=0;
        for (int o=0; o<numeroPuntosAnalisis; o++){
            MaterialEditText iluminanMedida= (MaterialEditText) layout.findViewWithTag(TAG_ILUM+o);
            String ilum = iluminanMedida.getText().toString();
            if (!TextUtils.isEmpty(ilum)) {
                iluminancia = Double.parseDouble(ilum);
                VerificacionEntity medi = new VerificacionEntity(idProyecto,iluminancia);
                medida.add(medi);
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addViews(List<PuntosAnalisisEntity> puntos) {

        layout = findViewById(R.id.layout_verification);

        if (layout.getChildCount()==0){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lm.setMargins(20,20,20,20);



            if (numeroPuntosAnalisis>0){

                for (int i=0; i<numeroPuntosAnalisis; i++){
                    PuntosAnalisisEntity current = puntos.get(i);
                    pts = new TextView(getApplicationContext());
                    pts.setLayoutParams(lp);
                    pts.setText("Punto "+(i+1));
                    pts.setTextAppearance(R.style.questionStyle);
                    layout.addView(pts);

                    coordx = new TextView(getApplicationContext());
                    coordx.setLayoutParams(lp);
                    coordx.setText("Ubicacion en X= "+current.getCoordenadaX());
                    coordx.setTextAppearance(R.style.detailsStyle);
                    layout.addView(coordx);

                    coordy = new TextView(getApplicationContext());
                    coordy.setLayoutParams(lp);
                    coordy.setText("Ubicacion en Y= "+current.getCoordenadaY());
                    coordy.setTextAppearance(R.style.detailsStyle);
                    layout.addView(coordy);

                    iluminancia = new MaterialEditText(getApplicationContext());
                    iluminancia.setLayoutParams(lm);
                    iluminancia.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);
                    iluminancia.setFloatingLabelAlwaysShown(true);
                    iluminancia.setFloatingLabelText("Iluminancia Medida");
                    iluminancia.setFloatingLabelTextSize(20);
                    iluminancia.setId(View.generateViewId());
                    iluminancia.setTag(TAG_ILUM+i);
                    iluminancia.setPrimaryColor(getResources().getColor(R.color.secondaryColor));
                    iluminancia.setHint("Lumen");
                    iluminancia.setEnabled(true);
                    iluminancia.setFocusable(true);
                    iluminancia.setFocusableInTouchMode(true);
                    layout.addView(iluminancia);



                }
            }
        }

    }

    private void initError(MaterialEditText editText, CharSequence error, String reg) {
        RegexpValidator validationEditText= new RegexpValidator(String.valueOf(error), reg);
        editText.validateWith(validationEditText);
    }

    private void setupViews() {
       iraResultados = findViewById(R.id.ira_resultados_generales);
        error = "Debe introducir una cifra";
    }

    private void setupViewModel(){
        VerificacionViewModel.Factory factory =
                new VerificacionViewModel.Factory(getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(VerificacionViewModel.class);
    }
}