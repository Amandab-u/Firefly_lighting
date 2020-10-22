package com.example.firefly.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.DatosLocalViewModel;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.firefly.ui.DatosProyecto.EXTRA_PROYECTO;

public class DatosLocal extends AppCompatActivity {

    FloatingActionButton irADatosLum;
    DatosLocalViewModel mDatosViewModel;
    static final String EXTRA_LOCAL = "com.example.firefly.EXTRA_LOCAL";

    EditText mLargo;
    EditText mAncho;
    EditText mAlto;
    EditText mAltoTrabajo;
    EditText mSPared;
    EditText mSPiso;
    EditText mSTecho;
    EditText mSTrabajo;
    EditText mNumeroLuminarias;
    String textLargo;
    String textAncho;
    String textAlto;
    String textTrabajo;
    String textsuperPared;
    String textsuperPiso;
    String textsuperTecho;
    String textsuperTrabajo;
    String textNumLuminarias;

    /** variables para setup y inicializar los spinner
     *
     */
    Spinner mNormaSpinner;
    Spinner mRParedSpinner;
    Spinner mRTechoSpinner;
    Spinner mRPisoSpinner;
    Spinner mRTrabajoSpinner;
    Spinner mFMantenimiento;

    /** variables para insertar dentro de tabla Datos del local
     *
     */
    public static long idProyecto;
    int idLocal;
    int size;
    int mAplicacionId;
    int idRparedSelec;
    int idRPisoSelec;
    int idRTechoSelec;
    int idRTrabajoSelec;
    int idFMantenimiento;
    double largo;
    double ancho;
    double alto;
    double altoTrabajo;
    double sPared;
    double sPiso;
    double sTecho;
    double sTrabajo;
    public static int numLuminarias;
    DatosLocalEntity local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_local);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        idProyecto = i.getIntExtra(EXTRA_PROYECTO,0);

        setUpViewModel();

        setUpViews();

        setUpSpinnerNorma();
        setupSpinnerRPared();
        setupSpinnerRPiso();
        setupSpinnerRTecho();
        setUpSpinnerRTrabajo();
        setUpSpinnerFacMant();

        irADatosLum = findViewById(R.id.ir_datos_luminaria);

        mDatosViewModel.getDatosLocalSize().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                size = integer;
                if (size>0){
                    idLocal = size + 1;
                } else {
                    idLocal = 1;
                }

            }
        });

        irADatosLum.setOnClickListener(v -> {

            getTextEditText();

            createLocal(idLocal);

            if (local !=null){
              mDatosViewModel.insertDatosLocal(local);
              Toast.makeText(DatosLocal.this,"DatosLocal insertado con exito id: "+idProyecto,Toast.LENGTH_LONG).show();

            }
            Intent irAdatosLuminaria = new Intent(DatosLocal.this, DatosLuminaria.class);


            if (idProyecto!=0) {
               irAdatosLuminaria.putExtra(EXTRA_LOCAL, idProyecto);
            }

            startActivity(irAdatosLuminaria);

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.local_menu,menu);
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
            case R.id.dummy_data:
                insertDummyData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertDummyData() {
        mLargo.setText("3");
        mAlto.setText("2.8");
        mAncho.setText("3");
        mAltoTrabajo.setText("0.8");
        mSTrabajo.setText("6");
        mSTecho.setText("56");
        mSPiso.setText("50");
        mSPared.setText("60");
        mNumeroLuminarias.setText("1");

        mRPisoSpinner.setSelection(2);
        mRParedSpinner.setSelection(3);
        mRTechoSpinner.setSelection(2);
        mRTrabajoSpinner.setSelection(5);
        mFMantenimiento.setSelection(1);
        mNormaSpinner.setSelection(4);
    }

    private void createLocal (int idlocal){
        if (!TextUtils.isEmpty(textLargo) && !TextUtils.isEmpty(textAncho)
                && !TextUtils.isEmpty(textAlto) && !TextUtils.isEmpty(textTrabajo)
                && !TextUtils.isEmpty(textsuperPared) && !TextUtils.isEmpty(textsuperPiso)
                && !TextUtils.isEmpty(textsuperTecho) && !TextUtils.isEmpty(textsuperTrabajo)
                && !TextUtils.isEmpty(textNumLuminarias) && idFMantenimiento >0 && idRparedSelec >0
                && idRTechoSelec >0 && idRPisoSelec>0 && idRTrabajoSelec >0 && mAplicacionId>0 && idProyecto>0 && idlocal>0){
            largo = Double.parseDouble(textLargo);
            ancho = Double.parseDouble(textAncho);
            alto = Double.parseDouble(textAlto);
            altoTrabajo = Double.parseDouble(textTrabajo);
            sPared = Double.parseDouble(textsuperPared);
            sPiso = Double.parseDouble(textsuperPiso);
            sTecho = Double.parseDouble(textsuperTecho);
            sTrabajo = Double.parseDouble(textsuperTrabajo);
            numLuminarias = Integer.parseInt(textNumLuminarias);


            local = new DatosLocalEntity(idlocal, idProyecto, mAplicacionId,largo,alto,ancho,altoTrabajo,idFMantenimiento,
                    idRparedSelec,idRTechoSelec,idRPisoSelec,idRTrabajoSelec,sPared,sTecho,sPiso,sTrabajo,numLuminarias);
        } else {
            Toast.makeText(DatosLocal.this,"Debe rellenar todos los campos",Toast.LENGTH_LONG).show();
        }

    }

    private void setUpViews() {
        mAlto = findViewById(R.id.edit_alto);
        mLargo = findViewById(R.id.edit_largo);
        mAncho = findViewById(R.id.edit_ancho);
        mAltoTrabajo = findViewById(R.id.edit_altura_trabajo);
        mSPared = findViewById(R.id.area_superf_pared);
        mSPiso = findViewById(R.id.area_superf_piso);
        mSTecho = findViewById(R.id.area_superf_techo);
        mSTrabajo = findViewById(R.id.area_superf_trabajo);
        mNumeroLuminarias = findViewById(R.id.numero_luminarias);


    }

    private void getTextEditText(){
        textLargo = mLargo.getText().toString() ;
        textAncho = mAncho.getText().toString();
        textAlto = mAlto.getText().toString();
        textTrabajo = mAltoTrabajo.getText().toString();
        textsuperPared = mSPared.getText().toString();
        textsuperPiso = mSPiso.getText().toString();
        textsuperTecho = mSTecho.getText().toString();
        textsuperTrabajo = mSTrabajo.getText().toString();
        textNumLuminarias = mNumeroLuminarias.getText().toString();
    }

    private void setupSpinnerRPiso() {
        mRPisoSpinner=findViewById(R.id.reflectancia_piso);
        setUpSpinner(R.array.array_rpiso_options, mRPisoSpinner);

        mRPisoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                if (!TextUtils.isEmpty(selection)){
                    if (position != 0) {
                        idRPisoSelec = position; // esta es la id para la reflectancia para superficies claras

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setUpSpinnerRTrabajo() {
        mRTrabajoSpinner=findViewById(R.id.reflectancia_trabajo);
        setUpSpinner(R.array.array_rtrabajo_options, mRTrabajoSpinner );

        mRTrabajoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (position != 0) {
                        idRTrabajoSelec = position;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setUpSpinnerFacMant() {
        mFMantenimiento = findViewById(R.id.selec_mantenimiento);
        setUpSpinner(R.array.array_mantenimiento_options,mFMantenimiento);

        mFMantenimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (position != 0) {
                        idFMantenimiento = position;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    private void setupSpinnerRTecho(){
        mRTechoSpinner = findViewById(R.id.reflectancia_techo);
        setUpSpinner(R.array.array_rtecho_options,mRTechoSpinner);

        mRTechoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (position != 0) {
                        idRTechoSelec = position;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupSpinnerRPared() {
        mRParedSpinner = findViewById(R.id.reflectancia_pared);
        setUpSpinner(R.array.array_rpared_options,mRParedSpinner);

        mRParedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (position != 0) {
                        idRparedSelec = position;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setUpSpinnerNorma() {
        mNormaSpinner = findViewById(R.id.aplicacion_norma);
        setUpSpinner(R.array.array_norma_options,mNormaSpinner);

        mNormaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (position != 0) {
                        mAplicacionId = position;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setUpSpinner(int arraySpinner, Spinner options){

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter normaSpinnerAdapter = ArrayAdapter.createFromResource(this,
                arraySpinner, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        normaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        options.setAdapter(normaSpinnerAdapter);
    }

    private void setUpViewModel() {

        DatosLocalViewModel.Factory factory =
                new DatosLocalViewModel.Factory(getApplication());

        mDatosViewModel = new ViewModelProvider(this,factory).get(DatosLocalViewModel.class);
    }


}
