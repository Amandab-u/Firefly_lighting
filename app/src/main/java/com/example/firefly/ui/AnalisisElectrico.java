package com.example.firefly.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.AElectricoViewModel;
import com.example.firefly.database.entity.AEResultadosEntity;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import java.util.Objects;

import static com.example.firefly.ui.ResultadosBasicos.EXTRA_ID_PROY;

public class AnalisisElectrico extends AppCompatActivity {

    static final String EXTRA_ID_AE = "com.example.firefly.EXTRA_ID_AE";

    FloatingActionButton iraConfort;
    MaterialEditText voltajeAlimentacion;
    MaterialEditText corrienteAlimentacion;
    MaterialEditText factorPotencia;
    MaterialEditText horasSemana;


    boolean isvalidVoltaje;
    boolean isvalidCorriente;
    boolean isvalidFactor;
    boolean isvalidHoras;
    String regex = "^[-+]?\\d+(\\.\\d+)?$";
    String regexFP= "^0(?:\\.\\d+)?|1(\\.0*)?$";

    CharSequence error;
    CharSequence errorFP;

    AElectricoViewModel mViewModel;
    long idProyecto;
    boolean encuesta;
    boolean verificacion;
    ConsumoElectricoEntity datosAElectrico;
    int numLuminarias;
    long idLuminaria;
    double potencia;

    String textVoltaje;
    String textCorriente;
    String textFactor;
    String textHoras;


    double voltaje;
    double corriente;
    double factor;
    double horas;

    double potenciaIdeal;
    double potenciaReal;
    AEResultadosEntity results;

    public AnalisisElectrico() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis_electrico);

        Intent in = getIntent();
        idProyecto = in.getLongExtra(EXTRA_ID_PROY, 0);

        setupViewModel();
        setupViews();

        mViewModel.getProyectoCompleto(idProyecto).observe(this, new Observer<ProyectoCompleto>() {
            @Override
            public void onChanged(ProyectoCompleto proyectoCompleto) {
                encuesta = proyectoCompleto.proyecto.getEncuesta();
                verificacion = proyectoCompleto.proyecto.getVerificacion();
                numLuminarias = proyectoCompleto.local.getNumeroLuminarias();
                potencia = proyectoCompleto.luminariaProyecto.getPotencia();
            }
        });




        iraConfort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initError(voltajeAlimentacion, error,regex);
                initError(corrienteAlimentacion, error,regex);
                initError(factorPotencia, errorFP, regexFP);
                initError(horasSemana, error, regex);
                validateEditText();
                if (isvalidVoltaje && isvalidCorriente && isvalidFactor && isvalidHoras) {
                    obtenerDatosConsumo();

                    calcularResultadosElectricos();
                    Toast.makeText(AnalisisElectrico.this, "Debe introducir todos los datos", Toast.LENGTH_SHORT).show();
                    if (!(datosAElectrico == null)) {
                        mViewModel.insertConsumoElectrico(datosAElectrico);
                        mViewModel.insertResults(results);
                        Toast.makeText(AnalisisElectrico.this, "Datos consumo Electrico insertado con exito", Toast.LENGTH_SHORT).show();
                        if (encuesta) {
                            Intent i = new Intent(AnalisisElectrico.this, ConfortVisual.class);
                            i.putExtra(EXTRA_ID_AE,idProyecto);
                            startActivityForResult(i,0);
                        } else {
                            if (verificacion) {
                                Intent in = new Intent(AnalisisElectrico.this, Verificacion.class);
                                in.putExtra(EXTRA_ID_AE,idProyecto);
                                startActivityForResult(in,0);
                            } else {
                                Intent in = new Intent(AnalisisElectrico.this, ResultadosFinales.class);
                                in.putExtra(EXTRA_ID_AE,idProyecto);
                                startActivityForResult(in,0);
                            }
                        }
                    } else {
                        Toast.makeText(AnalisisElectrico.this, "Debe introducir todos los datos", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    private void calcularResultadosElectricos() {
        potenciaIdeal = potencia * numLuminarias * horas;
        potenciaReal = voltaje * corriente * factor * horas;
        Toast.makeText(AnalisisElectrico.this, "Potencia Nominal: "+potenciaIdeal +"\n"+"Potencia Real: "+potenciaReal, Toast.LENGTH_SHORT).show();
        results = new AEResultadosEntity(idProyecto,potenciaIdeal,potenciaReal);
    }

    private void initError(MaterialEditText editText, CharSequence error, String reg) {
        RegexpValidator validationEditText= new RegexpValidator(String.valueOf(error), reg);
        editText.validateWith(validationEditText);
    }

    private void validateEditText(){
        textVoltaje = Objects.requireNonNull(voltajeAlimentacion.getText()).toString();
        textCorriente = Objects.requireNonNull(corrienteAlimentacion.getText()).toString();
        textFactor = Objects.requireNonNull(factorPotencia.getText()).toString();
        textHoras = Objects.requireNonNull(horasSemana.getText()).toString();

        isvalidVoltaje = textVoltaje.matches(regex);
        isvalidCorriente = textCorriente.matches(regex);
        isvalidFactor = textFactor.matches(regex);
        isvalidHoras = textHoras.matches(regex);

    }

    private void obtenerDatosConsumo() {


        if (!textVoltaje.isEmpty() && !textCorriente.isEmpty() &&
        !textFactor.isEmpty() && !textHoras.isEmpty() && Double.parseDouble(textFactor)<=1){

            voltaje = Double.parseDouble(textVoltaje);
            corriente = Double.parseDouble(textCorriente);
            factor = Double.parseDouble(textFactor);
            horas = Double.parseDouble(textHoras);

            datosAElectrico = new ConsumoElectricoEntity(idProyecto,voltaje,corriente,factor,horas);
        } else {
            Toast.makeText(AnalisisElectrico.this,"Debe introducir todos los datos",Toast.LENGTH_SHORT).show();
        }

    }

    private void setupViews() {
        error = "Debe introducir una cifra";
        errorFP = "Debe introducir un valor entre 0 - 1";
        iraConfort = findViewById(R.id.ira_confort);
        voltajeAlimentacion = findViewById(R.id.voltaje_alimentacion);
        corrienteAlimentacion = findViewById(R.id.corriente_alimentacion);
        factorPotencia = findViewById(R.id.factor_potencia);
        horasSemana = findViewById(R.id.horas_semanal);
    }

    private void setupViewModel() {
        AElectricoViewModel.Factory factory =
                new AElectricoViewModel.Factory(getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(AElectricoViewModel.class);
    }

}