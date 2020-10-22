package com.example.firefly.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.BuildConfig;
import com.example.firefly.R;
import com.example.firefly.ViewModel.AElectricoViewModel;
import com.example.firefly.ViewModel.ResultFinalViewModel;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.VerificacionEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import static com.example.firefly.ui.AnalisisElectrico.EXTRA_ID_AE;
import static com.example.firefly.ui.ConfortVisual.EXTRA_ID_CV;
import static com.example.firefly.ui.ResultadosBasicos.EXTRA_ID_PROY;
import static com.example.firefly.ui.Verificacion.EXTRA_ID_V;

public class ResultadosFinales extends AppCompatActivity {

    long idProyecto;
    ResultFinalViewModel mViewModel;

    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;
    TextView luminSelec;
    TextView numLumin;
    TextView ilumMedia;
    TextView norma;
    TextView uniformidad;
    TextView dimensiones;
    TextView consumoNominal;
    TextView consumoMedido;
    TextView potenciaTotal;
    TextView areaPiso;
    TextView cargaConectada;
    TextView edadPromedio;
    TextView pregunta1;
    TextView pregunta2;
    TextView pregunta3;
    TextView pregunta4;
    TextView pregunta5;
    TextView pregunta6;
    TextView pregunta7;
    LinearLayout verifLayout;
    TextView text11;
    ImageView map;
    Button generarReporte;
    ConstraintLayout capture;

    boolean analisisElectrico;
    boolean verificacion;
    boolean encuesta;

    String nombreLuminaria;
    String nombreProyecto;
    double numLuminarias;
    double iluminanciaMedia;
    long idNorma;
    double uniform;
    double alto;
    double largo;
    double ancho;
    double consuIdeal;
    double consuReal;
    double potenTotal;
    double edad;
    double percentPregunta1;
    double percentLeer;
    double percentManipularObjectos;
    double percentObjPequenos;
    double percentMaquinas;
    double percentCompu;
    double percentMuyDificil;
    double percentDificil;
    double percentMedio;
    double percentFacil;
    double percentMuyFacil;
    double percentPregunta4;
    double percentArdor;
    double percentDolor;
    double percentDistinguir;
    double percentNuca;
    double percentNinguna;
    double percentPregunta6;
    double percentMuyAmplio;
    double percentAmplio;
    double percentAcorde;
    double percentAngosto;
    double percentMuyAngosto;
    List<VerificacionEntity> iMedida;
    List<IlumiCalculadaEntity> iCalculada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_finales);

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
                case "com.example.firefly.ui.Verificacion":
                    idProyecto = in.getLongExtra(EXTRA_ID_V, 0);
            }
        }

        int permissionCheck = ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ResultadosFinales.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        setupViewModel();
        setupViews();

        mViewModel.getEverything(idProyecto).observe(this, new Observer<ProyectoCompleto>() {
            @Override
            public void onChanged(ProyectoCompleto proyectoCompleto) {
                nombreProyecto = proyectoCompleto.proyecto.getNombreProyecto();
                analisisElectrico = proyectoCompleto.proyecto.getConsumoElectrico();
                verificacion = proyectoCompleto.proyecto.getVerificacion();
                encuesta = proyectoCompleto.proyecto.getEncuesta();

                nombreLuminaria = proyectoCompleto.luminariaProyecto.getNombreLuminariaSelec();
                numLuminarias = proyectoCompleto.local.getNumeroLuminarias();
                iluminanciaMedia = proyectoCompleto.analisisBasico.getIluminanciamedia();
                idNorma = proyectoCompleto.local.getIluminanciaNorma();
                uniform = proyectoCompleto.analisisBasico.getUniformidad();
                alto = proyectoCompleto.local.getAltoLocal();
                largo = proyectoCompleto.local.getLargoLocal();
                ancho = proyectoCompleto.local.getAnchoLocal();
                if (analisisElectrico) {
                    consuIdeal = proyectoCompleto.resultElectricos.getPotenciaIdeal();
                    consuReal = proyectoCompleto.resultElectricos.getPotenciaReal();
                    potenTotal = proyectoCompleto.luminariaProyecto.getPotencia();
                }
                if (encuesta){
                    edad = proyectoCompleto.encuestaResults.getPromedioEdad();
                    percentPregunta1 = proyectoCompleto.encuestaResults.getPercentPatologiaSi();
                    percentLeer = proyectoCompleto.encuestaResults.getPercentLeer();
                    percentManipularObjectos = proyectoCompleto.encuestaResults.getPercentManiObjSim();
                    percentObjPequenos = proyectoCompleto.encuestaResults.getPercentManiObjPeq();
                    percentMaquinas = proyectoCompleto.encuestaResults.getPercentManiMaq();
                    percentCompu = proyectoCompleto.encuestaResults.getPercentUsarOrd();
                    percentMuyDificil = proyectoCompleto.encuestaResults.getPercentDificultadMuy();
                    percentDificil = proyectoCompleto.encuestaResults.getPercentDificultadDif();
                    percentMedio = proyectoCompleto.encuestaResults.getPercentDificultadMedio();
                    percentFacil = proyectoCompleto.encuestaResults.getPercentDificultadFacil();
                    percentMuyFacil = proyectoCompleto.encuestaResults.getPercentDificultadMuyF();
                    percentPregunta4 = proyectoCompleto.encuestaResults.getPercentPosturaSi();
                    percentArdor = proyectoCompleto.encuestaResults.getPercentAnomaliaArdor();
                    percentDolor = proyectoCompleto.encuestaResults.getPercentAnomaliaDolor();
                    percentDistinguir = proyectoCompleto.encuestaResults.getPercentAnomaliaDisting();
                    percentNuca = proyectoCompleto.encuestaResults.getPercentAnomaliaNuca();
                    percentNinguna = proyectoCompleto.encuestaResults.getPercentAnomaliaNuca();
                    percentPregunta6 = proyectoCompleto.encuestaResults.getPercentCambiarPSi();
                    percentMuyAmplio = proyectoCompleto.encuestaResults.getPercentPercibeMuyA();
                    percentAmplio = proyectoCompleto.encuestaResults.getPercentPercibeAmplio();
                    percentAcorde = proyectoCompleto.encuestaResults.getPercentPercibeAcorde();
                    percentAngosto = proyectoCompleto.encuestaResults.getPercentPercibeEstrecho();
                    percentMuyAngosto = proyectoCompleto.encuestaResults.getPercentPercibeMuyE();
                }
                if (verificacion){
                    iMedida = proyectoCompleto.iluminanciasMedidas;
                    iCalculada = proyectoCompleto.iluminanciasCalculadas;
                }

                fillViews();
            }
        });


        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + "/Pictures");
            File file = new File(directory, "fireflymap.jpg"); //or any other format supported
            FileInputStream streamIn = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image
            streamIn.close();
            map.setImageBitmap(bitmap);
        } catch (Exception e){
            e.printStackTrace();
        }

        generarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap viewBitmap = Bitmap.createBitmap(capture.getWidth(),capture.getHeight(),Bitmap.Config.RGB_565);

                Canvas viewCanvas = new Canvas(viewBitmap);

                //get background of canvas
                Drawable backgroundDrawable = capture.getBackground();

                if(backgroundDrawable!=null){
                    backgroundDrawable.draw(viewCanvas);//draw the background on canvas;
                }
                else{
                    viewCanvas.drawColor(Color.WHITE);
                    //draw on canvas
                    capture.draw(viewCanvas);
                }

                // generate PDF
                PdfDocument pd = new PdfDocument();

                PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(capture.getWidth(), capture.getHeight(), 1).create();
                PdfDocument.Page p = pd.startPage(pi);
                Canvas c = p.getCanvas();
                c.drawBitmap(viewBitmap, 0, 0, new Paint());
                pd.finishPage(p);



                String subject = "Reporte-" + nombreProyecto + "-FireflyLighting.pdf";
                    //write the above generated bitmap  to a file
                    try {
                        File imgFile = new File(Environment.getExternalStorageDirectory() + File.separator +subject);
                        Uri contentUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                                BuildConfig.APPLICATION_ID + ".provider", imgFile);
                        pd.writeTo(new FileOutputStream(imgFile));
                        shareDocument(contentUri,subject);
                        Toast.makeText(ResultadosFinales.this, "Reporte Generado y Guardado en memoria", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    pd.close();


                }

        });

    }

    private void shareDocument(Uri uri, String text) {
        Intent mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("application/pdf");
        // Assuming it may go via eMail:
        mShareIntent.putExtra(Intent.EXTRA_SUBJECT, text);
        // Attach the PDf as a Uri, since Android can't take it as bytes yet.
        mShareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(mShareIntent);
    }


    private void fillViews() {
        luminSelec.setText(nombreLuminaria);
        numLumin.setText("Numero de Luminarias: "+numLuminarias);
        ilumMedia.setText("Iluminancia Media Calculada: "+iluminanciaMedia+" lx");
        double[] normaValores = ReflectanciaFactorMante.getNorma((int)idNorma);
        norma.setText("Iluminancia segun norma Covenin (Min - Media - Max):"+"\n"+
                (int)normaValores[0]+" lx - "+(int)normaValores[1]+" lx - "+(int)normaValores[2]);
        uniformidad.setText("Uniformidad: "+uniform);
        dimensiones.setText("Dimensiones Local (AltoxAnchoxLargo): "+"\n"+alto+"m - "+ancho+"m - "+largo);

        if (analisisElectrico) {
            consumoNominal.setText("Consumo Energetico Nominal: " + consuIdeal + " Kwh");
            consumoMedido.setText("Consumo Energetico Medido: " + consuReal + " Kwh");
            potenciaTotal.setText("Potencia total: " + (potenTotal * numLuminarias) + " W");
            areaPiso.setText("Area del piso: " + (ancho * largo) + "m^2");
            cargaConectada.setText("Carga Conectada Especifica: " + (potenTotal / (ancho * largo)) + " W/m^2");
        }else {
            consumoNominal.setVisibility(View.GONE);
            consumoMedido.setVisibility(View.GONE);
            potenciaTotal.setVisibility(View.GONE);
            areaPiso.setVisibility(View.GONE);
            cargaConectada.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
        }

        if (encuesta){
            edadPromedio.setText("La edad promedio de los encuestados es "+edad);
            pregunta1.setText(percentPregunta1 + "% Si"+"\n"+
                        (100-percentPregunta1)+"% No");
            pregunta2.setText(percentLeer+"% Leer o Escribir"+"\n"+percentManipularObjectos+"% Manipular Objetos de Colores Simiares"+
                    "\n"+percentObjPequenos+"% Manipular Objetos Pequeños"+"\n"+
                    percentMaquinas+"% Maquinas o Herramientas"+"\n"+
                    percentCompu+"% Usar Ordenadores de Escritorio o Portatiles");
            pregunta3.setText(percentMuyDificil+"% Muy Dificil"+ "\n"+percentDificil+"% Dificil"+"\n"+
                    percentMedio+"% Medio"+"\n"+percentFacil+"% Facil"+"\n"+percentMuyFacil+"% Muy Facil");
            pregunta4.setText(percentPregunta4+"% Si"+"\n"+(100-percentPregunta4)+"% No");
            pregunta5.setText(percentArdor+"% Ardor en los Ojos"+"\n"+percentDolor+"% Dolor de Cabeza"+"\n"+
                    percentDistinguir+"% Dificultad para distingir objetos"+"\n"+percentNuca+"% Molestia en la Nuca o Columna Vertebral"+
                    "\n"+percentNinguna+"% Ninguna");
            pregunta6.setText(percentPregunta6+"% Si"+"\n"+(100-percentPregunta6)+"% No");
            pregunta7.setText(percentMuyAmplio+"% Muy Amplio"+"\n"+percentAmplio+"% Amplio"+"\n" +
                    percentAcorde+"% Acorde"+"\n"+percentAngosto+"% Estrecho"+"\n"+percentMuyAngosto+"% Muy Estrecho");
        }else {
            pregunta1.setVisibility(View.GONE);
            pregunta2.setVisibility(View.GONE);
            pregunta3.setVisibility(View.GONE);
            pregunta4.setVisibility(View.GONE);
            pregunta5.setVisibility(View.GONE);
            pregunta6.setVisibility(View.GONE);
            pregunta7.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
            text4.setVisibility(View.GONE);
            text5.setVisibility(View.GONE);
            text6.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
            text8.setVisibility(View.GONE);
            text9.setVisibility(View.GONE);
            text10.setVisibility(View.GONE);
        }

        if (verificacion) {
            if (verifLayout.getChildCount() == 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lm.setMargins(20, 20, 20, 20);

                for (int i=0; i<iMedida.size(); i++){
                    VerificacionEntity currentMedida= iMedida.get(i);
                    IlumiCalculadaEntity currentCalculada = iCalculada.get(i);

                    TextView pts = new TextView(getApplicationContext());
                    pts.setLayoutParams(lp);
                    pts.setText("Iluminancias en Punto "+(i+1));
                    verifLayout.addView(pts);

                    TextView calculada = new TextView(getApplicationContext());
                    calculada.setLayoutParams(lp);
                    calculada.setText("Calculada= "+currentCalculada.getIluminanciaCalculada()+" lx");
                    verifLayout.addView(calculada);

                    TextView medida = new TextView(getApplicationContext());
                    medida.setLayoutParams(lp);
                    medida.setText("Medida= "+currentMedida.getIluminanciaMedida()+" lx");
                    verifLayout.addView(medida);
                }
            }
        } else {
            verifLayout.setVisibility(View.GONE);
            text11.setVisibility(View.GONE);
        }
    }

    private void setupViews() {
        text2 = findViewById(R.id.text_view2);
        luminSelec = findViewById(R.id.luminaria_seleccionada);
        numLumin = findViewById(R.id.numero_luminarias);
        ilumMedia = findViewById(R.id.iluminancia_media);
        norma = findViewById(R.id.iluminancia_norma);
        uniformidad = findViewById(R.id.uniformidad);
        dimensiones = findViewById(R.id.dimensiones_local);
        consumoNominal = findViewById(R.id.consumo_nominal);
        consumoMedido = findViewById(R.id.consumo_real);
        potenciaTotal = findViewById(R.id.potencia_total);
        areaPiso = findViewById(R.id.area_piso);
        cargaConectada = findViewById(R.id.carga_conectada);
        edadPromedio = findViewById(R.id.edad_promedio);
        pregunta1 = findViewById(R.id.pregunta_1);
        pregunta2 = findViewById(R.id.pregunta_2);
        pregunta3 = findViewById(R.id.pregunta_3);
        pregunta4 = findViewById(R.id.pregunta_4);
        pregunta5 = findViewById(R.id.pregunta_5);
        pregunta6 = findViewById(R.id.pregunta_6);
        pregunta7 = findViewById(R.id.pregunta_7);
        text3 = findViewById(R.id.text_view3);
        text4 = findViewById(R.id.text_view4);
        text5 = findViewById(R.id.text_view5);
        text6 = findViewById(R.id.text_view6);
        text7 = findViewById(R.id.text_view7);
        text8 = findViewById(R.id.text_view8);
        text9 = findViewById(R.id.text_view9);
        text10 = findViewById(R.id.text_view10);
        verifLayout = findViewById(R.id.verif_layout);
        text11 = findViewById(R.id.text_view11);
        map = findViewById(R.id.image_map);
        generarReporte = findViewById(R.id.generar_reporte);
        capture = findViewById(R.id.resultados_finales);
    }

    private void setupViewModel() {
        ResultFinalViewModel.Factory factory =
                new ResultFinalViewModel.Factory(getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(ResultFinalViewModel.class);
    }
}