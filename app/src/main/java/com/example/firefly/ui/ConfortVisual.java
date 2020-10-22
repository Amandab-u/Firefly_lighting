package com.example.firefly.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.AElectricoViewModel;
import com.example.firefly.ViewModel.ConfortViewModel;
import com.example.firefly.database.entity.EncuestaResultsEntity;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.Objects;

import static com.example.firefly.ui.AnalisisElectrico.EXTRA_ID_AE;
import static com.example.firefly.ui.ResultadosBasicos.EXTRA_ID_PROY;

public class ConfortVisual extends AppCompatActivity {

    static final String EXTRA_ID_CV = "com.example.firefly.EXTRA_ID_CV";
    String regex = "^\\d+";
    CharSequence error;
    CharSequence error2;
    ConfortViewModel mViewModel;
    boolean verificacion;

    FloatingActionButton comenzarEncuestas;
    FloatingActionButton iraVerificacion;
    MaterialEditText numEncuestas;
    MaterialEditText edadEncuestado;
    Button submit;
    RadioGroup patologiaVisual;
    RadioGroup dificultad;
    RadioGroup postura;
    RadioGroup cambioPosi;
    RadioGroup perEspa;
    TextView pregunta1;
    TextView pregunta2;
    TextView pregunta3;
    TextView pregunta4;
    TextView pregunta5;
    TextView pregunta6;
    TextView pregunta7;
    CheckBox actLeer;
    CheckBox actColores;
    CheckBox actObjectSmall;
    CheckBox actMaq;
    CheckBox actCompu;
    CheckBox anomArdor;
    CheckBox anomDolor;
    CheckBox anomDistinguir;
    CheckBox anomNuca;
    CheckBox anomNinguna;
    public long idProyecto;

    /** Varianles para calculos de encuesta
     */
    int numeroEncuestados;
    int encuestasSubmited = 0;
    int edad = 0;
    int patologiaSi = 0;
    int patologiaNo = 0;
    int leer = 0;
    int maniObjSim = 0;
    int maniObjPeq = 0;
    int maniMaq = 0;
    int usarOrd = 0;
    int dificultadMuy = 0;
    int dificultadDif = 0;
    int dificultadMedio = 0;
    int dificultadFacil = 0;
    int dificultadMuyF = 0;
    int posturaSi = 0;
    int posturaNo = 0;
    int anomaliaArdor = 0;
    int anomaliaDolor = 0;
    int anomaliaDisting = 0;
    int anomaliaNuca = 0;
    int anomaliaNone = 0;
    int cambiarPSi = 0;
    int cambiarPNo = 0;
    int percibeMuyA = 0;
    int percibeAmplio = 0;
    int percibeAcorde = 0;
    int percibeEstrecho = 0;
    int percibeMuyE = 0;

    /** Variables para introducir en la  base de datos
     */
    double percentPatologiaSi = 0;
    double percentLeer = 0;
    double percentManiObjSim = 0;
    double percentManiObjPeq = 0;
    double percentManiMaq = 0;
    double percentUsarOrd = 0;
    double percentDificultadMuy = 0;
    double percentDificultadDif = 0;
    double percentDificultadMedio = 0;
    double percentDificultadFacil = 0;
    double percentDificultadMuyF = 0;
    double percentPosturaSi = 0;
    double percentAnomaliaArdor = 0;
    double percentAnomaliaDolor = 0;
    double percentAnomaliaDisting = 0;
    double percentAnomaliaNuca = 0;
    double percentAnomaliaNone = 0;
    double percentCambiarPSi = 0;
    double percentPercibeMuyA = 0;
    double percentPercibeAmplio = 0;
    double percentPercibeAcorde = 0;
    double percentPercibeEstrecho = 0;
    double percentPercibeMuyE = 0;
    double promedioEdad;
    EncuestaResultsEntity results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confort_visual);

        Intent in = getIntent();

        if (getCallingActivity()!=null){
          String className =  getCallingActivity().getClassName();

            switch (className){
                case "com.example.firefly.ui.ResultadosBasicos":
                    idProyecto = in.getLongExtra(EXTRA_ID_PROY, 0);
                    break;
                case "com.example.firefly.ui.AnalisisElectrico":
                    idProyecto = in.getLongExtra(EXTRA_ID_AE, 0);
            }
        }
        setUpViews();
        setupViewModel();

        mViewModel.getProyectoCompleto(idProyecto).observe(this, new Observer<ProyectoCompleto>() {
            @Override
            public void onChanged(ProyectoCompleto proyectoCompleto) {
                verificacion = proyectoCompleto.proyecto.getVerificacion();
            }
        });

        comenzarEncuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initError(numEncuestas,error,regex);

                if (validateText(numEncuestas, regex)){
                    getNumEncuestados(numEncuestas);
                    setUpEncuesta();
                    comenzarEncuestas.hide();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initError(edadEncuestado,error2,regex);
                if (validateText(edadEncuestado,regex) && validateOpciones() && validateCheckBox() && validateCheckbox2()) {
                    encuestasSubmited += 1;
                    if (encuestasSubmited != numeroEncuestados) {

                        contadorEdad();
                        contadoresSiNo(patologiaVisual, postura, cambioPosi);
                        contadoresCheckbox(actLeer, actColores, actObjectSmall, actMaq, actCompu,
                                anomArdor, anomDolor, anomDistinguir, anomNuca, anomNinguna);
                        contadoresMultiple(dificultad, perEspa);
                        vaciarEncuesta();
                        Toast.makeText(ConfortVisual.this, "Van " + encuestasSubmited +"/"+numeroEncuestados+ " encuestados", Toast.LENGTH_SHORT).show();

                    } else {
                        contadorEdad();
                        contadoresSiNo(patologiaVisual, postura, cambioPosi);
                        contadoresCheckbox(actLeer, actColores, actObjectSmall, actMaq, actCompu,
                                anomArdor, anomDolor, anomDistinguir, anomNuca, anomNinguna);
                        contadoresMultiple(dificultad, perEspa);
                        String results = dificultadMuy + " personas muy dificil" + "\n" + dificultadMuyF + " personas muy facil" +
                                "\n" + anomaliaNone + " personas no sufren anomalia";
                        Toast.makeText(ConfortVisual.this, results, Toast.LENGTH_SHORT).show();
                        submit.setVisibility(View.GONE);
                        iraVerificacion.show();
                        vaciarLayout();
                    }
                } else {
                    Toast.makeText(ConfortVisual.this, "Debe responder todas las preguntas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iraVerificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPorcentages();
                if (results != null) {
                    mViewModel.insertResultEncuesta(results);
                    String results = percentPatologiaSi + "% de las personas poseen una patologia visual" + "\n" +
                            percentDificultadMuyF + "% de las personas consideran muy facil realizar su labor" +
                            "\n" + percentDificultadMuy + "% de las personas consideran muy dificil realizar su labor" + "\n"
                            + promedioEdad + " es la edad promedio de los encuestados"+ "\n"+
                            "Insertado con exito";
                    Toast.makeText(ConfortVisual.this, results, Toast.LENGTH_SHORT).show();

                    if (verificacion) {
                        Intent i = new Intent(ConfortVisual.this, Verificacion.class);
                        i.putExtra(EXTRA_ID_CV, idProyecto);
                        startActivityForResult(i, 0);
                    } else {
                        Intent i = new Intent(ConfortVisual.this, ResultadosFinales.class);
                        i.putExtra(EXTRA_ID_CV, idProyecto);
                        startActivityForResult(i, 0);
                    }
                }
            }
        });
    }

    private void contadorEdad() {
        String text = Objects.requireNonNull(edadEncuestado.getText()).toString();
        edad += Double.parseDouble(text);
    }

    private void calcularPorcentages() {

        // calcular promedio de edad
        promedioEdad = edad/numeroEncuestados;

        // preguntas seleccion simple si-no
        percentPatologiaSi = (patologiaSi * 100)/numeroEncuestados;
        percentPosturaSi = (posturaSi * 100)/numeroEncuestados;
        percentCambiarPSi = (cambiarPSi * 100)/numeroEncuestados;

        // preguntas seleccion simple diferentes opciones
        percentDificultadMuy = (dificultadMuy * 100)/numeroEncuestados;
        percentDificultadDif = (dificultadDif * 100)/numeroEncuestados;
        percentDificultadMedio = (dificultadMedio * 100)/numeroEncuestados;
        percentDificultadFacil = (dificultadFacil * 100)/numeroEncuestados;
        percentDificultadMuyF = 100 - percentDificultadMuy - percentDificultadDif -
                percentDificultadMedio - percentDificultadFacil;

        percentPercibeMuyA = (percibeMuyA * 100)/numeroEncuestados;
        percentPercibeAmplio = (percibeAmplio * 100)/numeroEncuestados;
        percentPercibeAcorde = (percibeAcorde * 100)/numeroEncuestados;
        percentPercibeEstrecho = (percibeEstrecho * 100)/numeroEncuestados;
        percentPercibeMuyE = 100 - percentPercibeMuyA - percentPercibeAmplio - percentPercibeAcorde - percentPercibeEstrecho;

        // preguntas de seleccion multiple
        percentLeer = (leer * 100)/numeroEncuestados;
        percentManiObjSim = (maniObjSim * 100)/numeroEncuestados;
        percentManiObjPeq = (maniObjPeq * 100)/numeroEncuestados;
        percentManiMaq = (maniMaq * 100)/numeroEncuestados;
        percentUsarOrd = (usarOrd * 100)/numeroEncuestados;

        percentAnomaliaArdor = (anomaliaArdor * 100)/numeroEncuestados;
        percentAnomaliaDolor = (anomaliaDolor * 100)/numeroEncuestados;
        percentAnomaliaDisting = (anomaliaDisting * 100)/numeroEncuestados;
        percentAnomaliaNuca = (anomaliaNuca * 100)/numeroEncuestados;
        percentAnomaliaNone = (anomaliaNone * 100)/numeroEncuestados;

        results = new EncuestaResultsEntity(idProyecto,percentPatologiaSi,percentLeer,
                percentManiObjSim,percentManiObjPeq,percentManiMaq,percentUsarOrd,percentDificultadMuy,
                dificultadDif,dificultadMedio,dificultadFacil,dificultadMuyF,percentPosturaSi,percentAnomaliaArdor,
                percentAnomaliaDolor,percentAnomaliaDisting,percentAnomaliaNuca,percentAnomaliaNone,percentCambiarPSi,
                percentPercibeMuyA,percentPercibeAmplio,percentPercibeAcorde,percentPercibeEstrecho,
                percentPercibeMuyE,promedioEdad);

    }

    private void vaciarLayout() {
        pregunta2.setText("Todas las encuestas completadas");
        edadEncuestado.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        patologiaVisual.setVisibility(View.GONE);

        actLeer.setVisibility(View.GONE);
        actColores.setVisibility(View.GONE);
        pregunta1.setVisibility(View.GONE);
        actObjectSmall.setVisibility(View.GONE);
        actMaq.setVisibility(View.GONE);
        actCompu.setVisibility(View.GONE);
        pregunta3.setVisibility(View.GONE);
        dificultad.setVisibility(View.GONE);
        pregunta4.setVisibility(View.GONE);
        postura.setVisibility(View.GONE);
        pregunta5.setVisibility(View.GONE);
        anomArdor.setVisibility(View.GONE);
        anomDolor.setVisibility(View.GONE);
        anomDistinguir.setVisibility(View.GONE);
        anomNuca.setVisibility(View.GONE);
        anomNinguna.setVisibility(View.GONE);
        pregunta6.setVisibility(View.GONE);
        cambioPosi.setVisibility(View.GONE);
        perEspa.setVisibility(View.GONE);
        pregunta7.setVisibility(View.GONE);
    }

    private boolean validateCheckbox2() {
        if (!anomDolor.isChecked() && !anomNuca.isChecked() && !anomDistinguir.isChecked()
                && !anomArdor.isChecked() && !anomNinguna.isChecked()) {
            anomNinguna.setChecked(true);
        }
        return true;
    }

    private boolean validateCheckBox() {
        return actLeer.isChecked() || actColores.isChecked() || actObjectSmall.isChecked()
                || actMaq.isChecked() || actCompu.isChecked();
    }

    private boolean validateOpciones() {
        if ((patologiaVisual.getCheckedRadioButtonId() == -1) || (postura.getCheckedRadioButtonId() == -1)
                || (cambioPosi.getCheckedRadioButtonId() == -1) || (dificultad.getCheckedRadioButtonId() == -1)
                || (perEspa.getCheckedRadioButtonId() == -1)){
            return false;
        } else {
            return true;
        }
    }

    private void vaciarEncuesta() {

        edadEncuestado.getText().clear();
        patologiaVisual.clearCheck();
        actLeer.setChecked(false);
        actColores.setChecked(false);
        actObjectSmall.setChecked(false);
        actMaq.setChecked(false);
        actCompu.setChecked(false);
        dificultad.clearCheck();
        postura.clearCheck();
        anomArdor.setChecked(false);
        anomDolor.setChecked(false);
        anomDistinguir.setChecked(false);
        anomNuca.setChecked(false);
        anomNinguna.setChecked(false);
        cambioPosi.clearCheck();
        perEspa.clearCheck();
    }

    private void contadoresMultiple(RadioGroup dificul, RadioGroup percibe) {

        if (dificul.getCheckedRadioButtonId()==R.id.muy_dificil) {
            dificultadMuy += 1;
        } else if (dificul.getCheckedRadioButtonId()==R.id.dificil) {
            dificultadDif += 1;
        } else if (dificul.getCheckedRadioButtonId()==R.id.medio) {
            dificultadMedio += 1;
        } else if (dificul.getCheckedRadioButtonId()==R.id.facil) {
            dificultadFacil += 1;
        } else {
            dificultadMuyF += 1;
        }

        if (percibe.getCheckedRadioButtonId()==R.id.muy_amplio) {
            percibeMuyA += 1;
        } else if (percibe.getCheckedRadioButtonId()==R.id.amplio) {
            percibeAmplio += 1;
        } else if (percibe.getCheckedRadioButtonId()==R.id.acorde) {
            percibeAcorde += 1;
        } else if (percibe.getCheckedRadioButtonId()==R.id.estrecho) {
            percibeEstrecho += 1;
        } else {
            percibeMuyE += 1;
        }

    }

    private void contadoresCheckbox(CheckBox opc1, CheckBox opc2, CheckBox opc3, CheckBox opc4, CheckBox opc5,
                                    CheckBox opc6, CheckBox opc7, CheckBox opc8, CheckBox opc9, CheckBox opc10) {

        if (opc1.isChecked()){
            leer += 1;
        }
        if (opc2.isChecked()){
            maniObjSim += 1;
        }
        if (opc3.isChecked()){
            maniObjPeq += 1;
        }
        if (opc4.isChecked()){
            maniMaq += 1;
        }
        if (opc5.isChecked()){
            usarOrd += 1;
        }


        if (opc6.isChecked()){
            anomaliaArdor += 1;
        }
        if (opc7.isChecked()){
            anomaliaDolor += 1;
        }
        if (opc8.isChecked()){
            anomaliaDisting += 1;
        }
        if (opc9.isChecked()){
            anomaliaNuca += 1;
        }
        if (opc10.isChecked()){
            anomaliaNone += 1;
        }
    }

    private void contadoresSiNo(RadioGroup patolog, RadioGroup port, RadioGroup cambio) {
        if (patolog.getCheckedRadioButtonId()==R.id.si_patologia){
            patologiaSi += 1;
        } else {
            patologiaNo += 1;
        }

        if (port.getCheckedRadioButtonId()==R.id.si_postura){
            posturaSi += 1;
        } else {
            posturaNo += 1;
        }

        if (cambio.getCheckedRadioButtonId()==R.id.si_cambiar_posicion){
            cambiarPSi += 1;
        } else {
            cambiarPNo += 1;
        }

    }

    private boolean validateText(MaterialEditText numEncuestas, String reg) {
        String text = Objects.requireNonNull(numEncuestas.getText().toString());
        return text.matches(reg);
    }

    private void setUpEncuesta() {
        numEncuestas.setVisibility(View.GONE);
        edadEncuestado.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        patologiaVisual.setVisibility(View.VISIBLE);
        pregunta2.setVisibility(View.VISIBLE);
        actLeer.setVisibility(View.VISIBLE);
        actColores.setVisibility(View.VISIBLE);
        pregunta1.setVisibility(View.VISIBLE);
        actObjectSmall.setVisibility(View.VISIBLE);
        actMaq.setVisibility(View.VISIBLE);
        actCompu.setVisibility(View.VISIBLE);
        pregunta3.setVisibility(View.VISIBLE);
        dificultad.setVisibility(View.VISIBLE);
        pregunta4.setVisibility(View.VISIBLE);
        postura.setVisibility(View.VISIBLE);
        pregunta5.setVisibility(View.VISIBLE);
        anomArdor.setVisibility(View.VISIBLE);
        anomDolor.setVisibility(View.VISIBLE);
        anomDistinguir.setVisibility(View.VISIBLE);
        anomNuca.setVisibility(View.VISIBLE);
        anomNinguna.setVisibility(View.VISIBLE);
        pregunta6.setVisibility(View.VISIBLE);
        cambioPosi.setVisibility(View.VISIBLE);
        perEspa.setVisibility(View.VISIBLE);
        pregunta7.setVisibility(View.VISIBLE);
    }

    private void setUpViews() {
        error = "Debe introducir un numero entero de personas";
        error2 = "Debe introducir un numero entero de años";
        comenzarEncuestas = findViewById(R.id.comenzar_encuesta);
        iraVerificacion = findViewById(R.id.ira_verificacion);
        numEncuestas = findViewById(R.id.numero_encuestas);
        edadEncuestado = findViewById(R.id.edad_encuestado);
        submit = findViewById(R.id.submit);
        patologiaVisual = findViewById(R.id.patologia_visual);
        pregunta1 = findViewById(R.id.pregunta1);
        pregunta2 = findViewById(R.id.pregunta2);
        actLeer = findViewById(R.id.actividad_leer);
        actColores = findViewById(R.id.actividad_coloressim);
        actObjectSmall = findViewById(R.id.actividad_objetos_pequeños);
        actMaq = findViewById(R.id.actividad_maquinas);
        actCompu = findViewById(R.id.actividad_compu);
        pregunta3 = findViewById(R.id.pregunta3);
        dificultad = findViewById(R.id.dificultad);
        pregunta4 = findViewById(R.id.pregunta4);
        postura = findViewById(R.id.posture);
        pregunta5 = findViewById(R.id.pregunta5);
        anomArdor = findViewById(R.id.anomalia_ardor);
        anomDolor = findViewById(R.id.anomalia_dolor);
        anomDistinguir = findViewById(R.id.anomalia_distinguir);
        anomNinguna = findViewById(R.id.anomalia_ninguna);
        anomNuca = findViewById(R.id.anomalia_nuca);
        pregunta6 = findViewById(R.id.pregunta6);
        cambioPosi = findViewById(R.id.cambiar_posicion);
        perEspa = findViewById(R.id.percibe_espacio);
        pregunta7 = findViewById(R.id.pregunta7);
    }

    private void initError(MaterialEditText editText, CharSequence error, String reg) {
        RegexpValidator validationEditText= new RegexpValidator(String.valueOf(error), reg);
        editText.validateWith(validationEditText);
    }

    private void getNumEncuestados(MaterialEditText editText){
        String text = editText.getText().toString();
        if (!TextUtils.isEmpty(text)){
            numeroEncuestados = Integer.parseInt(text);
        }
    }

    private void setupViewModel() {
        ConfortViewModel.Factory factory =
                new ConfortViewModel.Factory(getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(ConfortViewModel.class);
    }
}