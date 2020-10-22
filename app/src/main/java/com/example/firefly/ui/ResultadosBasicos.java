package com.example.firefly.ui;

import androidx.annotation.AnyThread;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firefly.POJOs.Coordenadas;
import com.example.firefly.POJOs.DatosPuntos;
import com.example.firefly.R;
import com.example.firefly.ViewModel.ResultadosViewModel;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.ResultadosBasicosEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.hss.heatmaplib.HeatMap;
import ca.hss.heatmaplib.HeatMapMarkerCallback;

import static com.example.firefly.ui.DetallesLuminariaCat.EXTRA_DETALLES;
import static com.example.firefly.ui.DetallesLuminariaCat.EXTRA_LUMIN_SELEC;

public class ResultadosBasicos extends AppCompatActivity {


    ResultadosViewModel mViewModel;
    static final String EXTRA_ID_PROY = "com.example.firefly.EXTRA_ID_PROY";
    static final String EXTRA_MAP = "com.example.firefly.EXTRA_MAP";

    String result2;

    public long idProyecto;
    int idLuminariaSelec;
    boolean analisisE;
    boolean encuesta;
    boolean verificacion;

    /** Variables para calcular la Iluminancia Directa
     *
     */
    double eD;
    int numPtsAnalisis;
    int numLuminarias;
    int flujoTotal;
    double relacionArea;
    double coordZ;
    double coordX;
    double coordY;
    double anguloInter;
    double tita;
    double intensidadLimiteX;
    double intensidadLimiteY;
    double anchoLocal;
    double largoLocal;
    double altoTrabajo;
    double deltaH;
    double IC;
    double factorMantenimiento;

    /** Variables para calcular Iluminancia Indirecta
     *
     */
    double eInD;
    double rPared;
    double rPiso;
    double rTecho;
    double rTrabajo;
    double sPiso;
    double sPared;
    double sTecho;
    double sTrabajo;
    long idFM;
    long idRPiso;
    long idRPared;
    long idRTecho;
    long idRTrabajo;
    int flujo;

    /** Variables que seran guardadas en la base de datos
     *
     */
    double eMedia;
    double edirectaMayor;
    double edirectaMenor;
    double uniformidad;
    ResultadosBasicosEntity result;
    ArrayList<PuntosAnalisisEntity> puntosDeAnalisis = new ArrayList<>();
    List<IlumiCalculadaEntity> calculadas = new ArrayList<>();

    /**Listas o matrices para stored los datos de todos los puntos
     *
     */
    ArrayList<Coordenadas> puntosAnalisis = new ArrayList<>();
    ArrayList<Coordenadas> coordenadasLuminarias = new ArrayList<>();
    ArrayList<Double> angulosTita = new ArrayList<>();
    List<FotometriaEntity> fotometriaX = new ArrayList<>();
    List<FotometriaEntity> fotometriaY = new ArrayList<>();
    ArrayList<DatosPuntos> datosEdirecta = new ArrayList<>();
    ArrayList<Double> eDirecta = new ArrayList<>();
    ArrayList<Double> eDTotal = new ArrayList<>();

    /** Listas o matrices para guardar datos para la grafica mas detallada
     *
     */
    ArrayList<Coordenadas> puntosGrafDetail = new ArrayList<>();
    ArrayList<Double> titaGrafDetail = new ArrayList<>();
    ArrayList<DatosPuntos> datosGrafDetail = new ArrayList<>();
    ArrayList<Double> eDirectaGrafDetail = new ArrayList<>();
    ArrayList<Double> eDTotalGraf = new ArrayList<>();
    ArrayList<Float> escalaColor = new ArrayList<Float>();
    ArrayList<Float> escalaMenor = new ArrayList<>();
    float scalaPrueba = 50;
    float scalaMap = 50;


    /**Views de la actividad
     *
     */
    TextView resultado;
    TextView resultado1;
    TextView resultado2;
    Button calcular;
    FloatingActionButton iraElectrica;
    HeatMap map;
    HeatMap prueba;
    ImageView imagen;
    TextView emax;
    TextView emin;
    TextView emedia;
    TextView fifty;
    ConstraintLayout c;



    ArrayList<Double> intensidadMap = new ArrayList<>();
    ArrayList<Double> intensidadGraf = new ArrayList<>();

    ArrayList<Double> primerIntento = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_basicos);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        Intent in = getIntent();
        idProyecto = in.getLongExtra(EXTRA_DETALLES, 0);
        idLuminariaSelec = in.getIntExtra(EXTRA_LUMIN_SELEC,0);





        resultado = findViewById(R.id.resultado);
        resultado1 = findViewById(R.id.resultado1);
        resultado2 = findViewById(R.id.resultado2);
        calcular = findViewById(R.id.button);
        map = findViewById(R.id.example_map);
        prueba = findViewById(R.id.prueba_map);
        emax = findViewById(R.id.e_max);
        emin = findViewById(R.id.e_min);
        iraElectrica = findViewById(R.id.ira_electrico);
        emedia = findViewById(R.id.e_media);
        fifty = findViewById(R.id.cincuenta);
        c = findViewById(R.id.contraint_layout);


        setUpViewModel();
        puntosAnalisis.clear();
        coordenadasLuminarias.clear();
        fotometriaX.clear();
        fotometriaY.clear();
        puntosGrafDetail.clear();
        titaGrafDetail.clear();
        datosGrafDetail.clear();
        eDirectaGrafDetail.clear();
        eDTotal.clear();
        eDTotalGraf.clear();
        eDirecta.clear();
        escalaColor.clear();
        primerIntento.clear();



        mViewModel.getFotometria(idLuminariaSelec,1).observe(this, new Observer<List<FotometriaEntity>>() {
            @Override
            public void onChanged(List<FotometriaEntity> fotometriaEntities) {
                fotometriaX = fotometriaEntities;
            }
        });

        mViewModel.getFotometria(idLuminariaSelec,2).observe(this, new Observer<List<FotometriaEntity>>() {
            @Override
            public void onChanged(List<FotometriaEntity> fotometriaEntities) {
                fotometriaY = fotometriaEntities;
            }
        });

        mViewModel.getCatalogo(idLuminariaSelec).observe(this, new Observer<CatalogoCompleto>() {
            @Override
            public void onChanged(CatalogoCompleto catalogoCompleto) {


                flujoTotal = catalogoCompleto.catalogo.getFlujoLumTotal();
                flujo = catalogoCompleto.catalogo.getFlujoLuminoso();

            }
        });


        mViewModel.getProyectoCompleto(idProyecto).observe(this, new Observer<ProyectoCompleto>() {
            @Override
            public void onChanged(ProyectoCompleto proyectoCompleto) {

                numLuminarias = proyectoCompleto.datosLocal.getNumeroLuminarias();

                //Obtener valores para calcular iluminancia indirecta directa desde la base de datos
                idFM = proyectoCompleto.local.getId_factor_mantenimiento();
                factorMantenimiento = ReflectanciaFactorMante.getfactorMantenimiento((int)idFM);
                idRPiso = proyectoCompleto.local.getId_rPiso();
                rPiso = ReflectanciaFactorMante.getReflectanciaPiso((int)idRPiso);
                idRPared = proyectoCompleto.local.getId_rPared();
                rPared = ReflectanciaFactorMante.getReflectanciaPared((int)idRPared);
                idRTecho = proyectoCompleto.local.getId_rTecho();
                rTecho = ReflectanciaFactorMante.getReflectanciaTecho((int)idRTecho);
                idRTrabajo = proyectoCompleto.local.getId_rTrabajo();
                rTrabajo = ReflectanciaFactorMante.getReflectanciaTrabajo((int)idRTrabajo);
                sPiso = proyectoCompleto.local.getSuperficiePiso();
                sPared = proyectoCompleto.local.getSuperficiePared();
                sTecho = proyectoCompleto.local.getSuperficieTecho();
                sTrabajo = proyectoCompleto.local.getSuperficieTrabajo();

                altoTrabajo = proyectoCompleto.local.getAltoTrabajo();

                coordZ = proyectoCompleto.luminariaProyecto.getCoordenadaZ();
                deltaH = calcularDeltaH(coordZ);

                anchoLocal = proyectoCompleto.local.getAnchoLocal();
                largoLocal = proyectoCompleto.local.getLargoLocal();
                relacionArea = anchoLocal / largoLocal;
                calcularNumeroPuntosAnalisis(anchoLocal, largoLocal, deltaH);
                calcularNumPuntosGrafDetail(anchoLocal,largoLocal);

                if (numLuminarias>1){
                    for (int a=0; a<numLuminarias; a++){
                        DatosLuminariaEntity currentLuminaria = proyectoCompleto.luminariasProyecto.get(a);
                        coordX = currentLuminaria.getCoordenadaX();
                        coordY = currentLuminaria.getCoordenadaY();
                        Coordenadas coordenadas = new Coordenadas(coordX,coordY);
                        coordenadasLuminarias.add(coordenadas);
                    }
                }else {
                    coordX = proyectoCompleto.luminariaProyecto.getCoordenadaX();
                    coordY = proyectoCompleto.luminariaProyecto.getCoordenadaY();
                    Coordenadas coordenadas = new Coordenadas(coordX,coordY);
                    coordenadasLuminarias.add(coordenadas);
                }

                analisisE = proyectoCompleto.proyecto.getConsumoElectrico();
                encuesta = proyectoCompleto.proyecto.getEncuesta();
                verificacion = proyectoCompleto.proyecto.getVerificacion();

                if (!analisisE && !encuesta && !verificacion){
                    iraElectrica.hide();
                }

            }
        });



        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    datosEdirecta.clear();
                    angulosTita.clear();
                    calcularAngulosIntensidades(puntosAnalisis, angulosTita, datosEdirecta, intensidadMap);
                    calcularAngulosIntensidades(puntosGrafDetail, titaGrafDetail, datosGrafDetail, intensidadGraf);
                    calcularIluminanciaIndirecta(sPared,sPiso,sTecho,sTrabajo,rPared,rTecho,rPiso,rTrabajo,flujo);
                    resultado2.setText(result2);
//                    calcularEDirectasinInter(flujoTotal,factorMantenimiento,deltaH,resultado, eDirecta);
                    calcularIluminanciaDirecta( flujoTotal, factorMantenimiento, angulosTita, deltaH, datosEdirecta, eDirecta, intensidadMap);
                    calcularIluminanciaDirecta( flujoTotal, factorMantenimiento, titaGrafDetail, deltaH, datosGrafDetail, eDirectaGrafDetail, intensidadGraf);
                    calcularETotal(eDirecta,eDTotal);
                    calcularETotal(eDirectaGrafDetail,eDTotalGraf);
                edirectaMayor = eDTotalGraf.get(0);
                edirectaMenor = eDTotalGraf.get(0);
                double eSumatoria = 0;
                for (int s=0; s<eDTotalGraf.size();s++){
                    double edireccomp = eDTotalGraf.get(s);

                    eSumatoria += edireccomp;
                    if (edirectaMayor<edireccomp){
                        edirectaMayor = edireccomp;
                    }

                    if (edirectaMenor>edireccomp){
                        edirectaMenor = edireccomp;
                    }
                }
                ilCalculadas(eDTotal);
                eMedia = eSumatoria/eDTotalGraf.size();
                uniformidad = edirectaMenor/eMedia;
                resultado1.setText("Iluminancia max: "+edirectaMayor + "\n"+"Iluminancia min: "+edirectaMenor+
                        "\n"+ "Iluminancia media: " +eMedia +"\n"+ "Uniformidad: " +uniformidad);
                emin.setText("0%");
                emax.setText("100%");
                emedia.setText("20%");
                fifty.setText("50%");

                result = new ResultadosBasicosEntity(idProyecto,eMedia,
                        edirectaMayor,edirectaMenor,uniformidad);

            }
        });


        map.setOnMapClickListener(new HeatMap.OnMapClickListener() {
            @Override
            public void onMapClicked(int x, int y, HeatMap.DataPoint closest) {
//                if (!(closest ==null)) {
//                    Toast.makeText(ResultadosBasicos.this, "Iluminancia: " + closest.value, Toast.LENGTH_LONG).show();
//                }
                setUpGrafica();
                generarGrafica();
                map.bringToFront();
            }
        });



        iraElectrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap viewBitmap = Bitmap.createBitmap(c.getWidth(),c.getHeight(),Bitmap.Config.RGB_565);

                Canvas viewCanvas = new Canvas(viewBitmap);

                //get background of canvas
                Drawable backgroundDrawable = c.getBackground();

                if(backgroundDrawable!=null){
                    backgroundDrawable.draw(viewCanvas);//draw the background on canvas;
                }
                else{
                    viewCanvas.drawColor(Color.WHITE);
                    //draw on canvas
                    c.draw(viewCanvas);
                }

                //write the above generated bitmap  to a file
                String fileStamp = "fireflymap";
                OutputStream outputStream = null;
                try{
                    File imgFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileStamp+".jpg");
                    outputStream = new FileOutputStream(imgFile);
                    viewBitmap.compress(Bitmap.CompressFormat.JPEG,40,outputStream);
                    outputStream.close();
                    Toast.makeText(ResultadosBasicos.this, "Imagen Guardada",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                }


                mViewModel.insertPuntosAnalisis(puntosDeAnalisis);
                mViewModel.insertResultadosBasicos(result);
                mViewModel.insertIlumiCalculadas(calculadas);
                Toast.makeText(ResultadosBasicos.this, "Iluminancias calculadas= "+calculadas.size(),Toast.LENGTH_SHORT).show();
                if (analisisE) {
                    Intent iraElectrica = new Intent(ResultadosBasicos.this, AnalisisElectrico.class);
                    iraElectrica.putExtra(EXTRA_ID_PROY,idProyecto);
                    startActivity(iraElectrica);

                } else {
                    if (encuesta){
                        Intent iraEncuesta = new Intent(ResultadosBasicos.this, ConfortVisual.class);
                        iraEncuesta.putExtra(EXTRA_ID_PROY,idProyecto);
                        startActivityForResult(iraEncuesta,0);
                    } else {
                        if (verificacion){
                            Intent iraVerificacion = new Intent(ResultadosBasicos.this, Verificacion.class);
                            iraVerificacion.putExtra(EXTRA_ID_PROY,idProyecto);
                            startActivityForResult(iraVerificacion,0);
                        }
                    }
                }
            }
        });
    }

    private void calcularIntento(ArrayList<Double> ilum, double max){
        for (int i=0; i<ilum.size(); i++){
            Double ilumPt= ilum.get(i);
            Double inif = ilumPt/max;
            primerIntento.add(inif);
        }
    }

    private void ilCalculadas(ArrayList<Double> etotal){
        for (int i=0; i<etotal.size(); i++){
            double edireccomp = etotal.get(i);
            IlumiCalculadaEntity calculada = new IlumiCalculadaEntity(idProyecto,edireccomp);
            calculadas.add(calculada);
        }
    }

    private void setUpGrafica() {
        map.setMinimum(0);
        map.setMaximum((float)edirectaMayor);

        final Paint backPaint = new Paint();
        backPaint.setColor(Color.LTGRAY);
        backPaint.setStyle(Paint.Style.FILL);

        final Paint textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(20);
        textPaint.setStyle(Paint.Style.FILL);

        map.setMarkerCallback(new HeatMapMarkerCallback() {
            @Override
            public void drawMarker(Canvas canvas, float x, float y, HeatMap.DataPoint point) {
                canvas.drawCircle(x, y, 5, backPaint);
                canvas.drawText(String.valueOf((int)point.value), x, y, textPaint);

            }
        });



        map.setRadius(10.0);

        Map<Float, Integer> colors1 = new ArrayMap<>();
        colors1.put(0.0f, Color.TRANSPARENT);
        colors1.put(1.0f, Color.BLACK);
        map.setColorStops(colors1);
        Map<Float, Integer> colors = new ArrayMap<>();



        // Logica para crear escala de colores adaptable a los valores de iluminancia

        if (anchoLocal<5 || largoLocal<5) {
            for (int i=0; i<=4; i++) {
                float primerValorEscala = 0f;
                float difvaloresmenores = 0.75f / 4f;
                primerValorEscala += difvaloresmenores;
                escalaMenor.add(primerValorEscala);
            }
            for (int g = 0; g <= 9; g++) {
                float segundoValorEscala = 0.75f;
                float diferenciaEscala = (1 - segundoValorEscala) / 9f;
                segundoValorEscala += g * diferenciaEscala;
                escalaColor.add(segundoValorEscala);
            }
        } else if ((anchoLocal>5 && anchoLocal<10) || (largoLocal>5 && largoLocal<10)){
            for (int i=0; i<=4; i++) {
                float primerValorEscala = 0f;
                float difvaloresmenores = 0.9f / 4f;
                primerValorEscala += difvaloresmenores;
                escalaMenor.add(primerValorEscala);
            }
            for (int g = 0; g <= 9; g++) {
                float segundoValorEscala = 0.9f;
                float diferenciaEscala = (1 - segundoValorEscala) / 9f;
                segundoValorEscala += g * diferenciaEscala;
                escalaColor.add(segundoValorEscala);
            }
        }else {
            for (int i=0; i<=4; i++) {
                float primerValorEscala = 0f;
                float difvaloresmenores = 0.92f / 4f;
                primerValorEscala += difvaloresmenores;
                escalaMenor.add(primerValorEscala);
            }
            for (int g = 0; g <= 9; g++) {
                float segundoValorEscala = 0.92f;
                float diferenciaEscala = (1 - segundoValorEscala) / 9f;
                segundoValorEscala += g * diferenciaEscala;
                escalaColor.add(segundoValorEscala);
            }
        }
        colors.put(escalaMenor.get(0), 0xff000000);
        colors.put(escalaMenor.get(1), 0xff00023F);
        colors.put(escalaMenor.get(2), 0xff330F38);
        colors.put(escalaMenor.get(3), 0xff923793);
        colors.put(escalaColor.get(0), 0xff7060FF);
        colors.put(escalaColor.get(1), 0xff3F92F4);
        colors.put(escalaColor.get(2), 0xff71A9FE);
        colors.put(escalaColor.get(3), 0xff03EFC2);
        colors.put(escalaColor.get(4), 0xff52DE70);
        colors.put(escalaColor.get(5), 0xffACFC42);
        colors.put(escalaColor.get(6), 0xffDFFF53);
        colors.put(escalaColor.get(7), 0xffFEFC04);
        colors.put(escalaColor.get(8), 0xffFFFF9B);



//        prueba.setMinimum((float)edirectaMenor);
//        prueba.setMaximum((float)edirectaMayor);
        prueba.setMinimum(0);
        prueba.setMaximum((float)edirectaMayor);
        prueba.setRadius(80.0);
        prueba.setColorStops(colors);

    }


    private void generarGrafica() {
        boolean testAsync = false;
        if (testAsync) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    drawNewMap();
                    map.forceRefreshOnWorkerThread();
                    prueba.forceRefreshOnWorkerThread();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            map.invalidate();
                            prueba.invalidate();
                        }
                    });
                }
            });
        }
        else {
            drawNewMap();
            map.forceRefresh();
            prueba.forceRefresh();
        }
    }


    @AnyThread
    private void drawNewMap() {
        map.clearData();
        prueba.clearData();
        for (int s=0; s<puntosGrafDetail.size(); s++) {
            Coordenadas currentPt = puntosGrafDetail.get(s);
            HeatMap.DataPoint point1 = calcularEtotal(s,eDTotalGraf,currentPt, scalaPrueba);
            prueba.addData(point1);
        }

        for (int s=0; s<puntosAnalisis.size(); s++) {
            Coordenadas currentPt = puntosAnalisis.get(s);
            HeatMap.DataPoint point1 = calcularEtotal(s,eDTotal,currentPt, scalaMap);
            map.addData(point1);

        }
    }

    private HeatMap.DataPoint calcularEtotal(int i, ArrayList<Double> iluminancia, Coordenadas pts, float scala){

        float x = (float) pts.getCoordenadaX() / scala;
        float y = (float) pts.getCoordenadaY() / scala;
//        double eTotal = iluminancia.get(i) +  eInD;
        double eTotal = iluminancia.get(i);
        return new HeatMap.DataPoint(x, y, eTotal);
    }

    /**
     * Metodo para calcular iluminancia indirecta en el recinto
     * @param spared
     * @param spiso
     * @param stecho
     * @param strabajo
     * @param rpared
     * @param rtecho
     * @param rpiso
     * @param rtrabajo
     * @param flujo
     */
    private void calcularIluminanciaIndirecta(double spared, double spiso, double stecho,
                                                double strabajo, double rpared,double rtecho,
                                                double rpiso,double rtrabajo, int flujo){
        double totalSumSuperficies = spared+spiso+stecho+strabajo;
        double totalSumSuperfxReflect = (rpared*spared)+(rpiso*spiso)+(rtecho*stecho)+(rtrabajo*strabajo);
        double reflectanciaMedia = totalSumSuperfxReflect/totalSumSuperficies;
        eInD = (flujo * reflectanciaMedia)/(totalSumSuperficies*(1-reflectanciaMedia));
        result2 = "Sumatoria de Superficies= "+totalSumSuperficies+"\n"
                +"Sumatoria de superficies por reflectancia= "+totalSumSuperfxReflect+"\n"
                +"Reflectancia Media= "+reflectanciaMedia +"\n"+ "Iluminancia Indirecta= "+eInD;
    }

    /**
     * 3er calculo del procedimiento de iluminancia
     * para obtener el angulo de interpolacion y los
     * angulos limites. Con los angulos limites se entra
     * en la fotometria para cada plano y se optiene las intensidades
     * correspondientes
     * @param puntos
     * @param tita
     * @param datos
     */
    private void calcularAngulosIntensidades(ArrayList<Coordenadas> puntos, ArrayList<Double> tita, ArrayList<DatosPuntos> datos, ArrayList<Double> intens) {
        for (int u=0; u<puntos.size(); u++){
            double intensidad;
            DatosPuntos punto;
            double x = puntos.get(u).getCoordenadaX();
            double y = puntos.get(u).getCoordenadaY();
            for (int p=0; p<coordenadasLuminarias.size(); p++){
                double xl = coordenadasLuminarias.get(p).getCoordenadaX();
                double yl = coordenadasLuminarias.get(p).getCoordenadaY();

                anguloInter = calcularAngulosInterpolacion(x, y, xl, yl);


                //Para calcular los angulos que se usan en la formula de iluminancia directa
                calcularAnguloTita(x,y,xl,yl,tita);

                double anguloLimX= calcularAngulosLimite(x,xl);
                double anguloLimY = calcularAngulosLimite(y,yl);
                for (int k = 0; k < fotometriaX.size(); k++) {
                    if (anguloLimX<= fotometriaX.get(k).getAngulo()) {
                        FotometriaEntity current = fotometriaX.get(k);
                        intensidadLimiteX = current.getIntensidad();
                        break;
                    }
                }
                if (fotometriaY!=null) {
                    for (int k = 0; k < fotometriaY.size(); k++) {
                        if (anguloLimY <= fotometriaY.get(k).getAngulo()) {
                            FotometriaEntity current = fotometriaY.get(k);
                            intensidadLimiteY = current.getIntensidad();
                            break;
                        }
                    }
                    punto = new DatosPuntos(intensidadLimiteX,intensidadLimiteY,anguloInter);
                } else {
                    intensidad = intensidadLimiteX;
                    punto = new DatosPuntos(intensidadLimiteX,intensidad,anguloInter);
                }

                datos.add(u,punto);

                if (yl == y || xl == x) {
                    intensidad = intensidadLimiteX;
                } else {
                    double y1 = punto.getIntensidadY();
                    double y3 = punto.getIntensidadX();
                    double x2 = punto.getAnguloInter();
                    intensidad = y3 + ((y1 - y3)/(90-0))*(x2 - 0);
                }
                intens.add(intensidad);
            }


        }


    }


    /**
     * 4to calculo para realizar interpolacion y obtener la intensidad real
     * Luego caldular la iluminancia directa
     * @param flujoTotal
     * @param factorMante
     * @param angulotita
     * @param delta
     * @param datosG lista de datos para la interpolacion
     * @param edirec
     */
    private void calcularIluminanciaDirecta( double flujoTotal, double factorMante, ArrayList<Double> angulotita,
                                             double delta, ArrayList<DatosPuntos> datosG,
                                             ArrayList<Double> edirec, ArrayList<Double> intens){
        double intensidad;
        for (int h = 0; h< datosG.size(); h++) {
            double currentTita = angulotita.get(h);
            intensidad = intens.get(h);
            double iReal = (intensidad * flujoTotal * factorMante) / 1000;
            double cosTita = Math.cos(currentTita);
            double cosTita3 = Math.pow(cosTita, 3);
            double delta2 = Math.pow(delta, 2);
            eD = (iReal * cosTita3) / delta2;
            edirec.add(eD);

        }

    }
    private void calcularETotal(ArrayList<Double> edirec, ArrayList<Double> eDirectTotal){
        for (int i = 0; i<edirec.size(); i+=numLuminarias){
            double ilumTotal =edirec.get(i);
            for (int f=1; f<numLuminarias; f++){
                ilumTotal += edirec.get(i+f);
            }
            eDirectTotal.add(ilumTotal);

        }
    }


    /**
     * Metodo para procedimiento de iluminancia
     * para calcular los angulos en las posiciones limites para
     * obtener a su vez las intensidades limites
     * Utilizado en el tercer calculo de calcular angulos
     * e intensidades
     * @param x
     * @param y
     * @param xl
     * @param yl
     * @return
     */
    private double calcularAngulosInterpolacion(double x, double y, double xl, double yl){
        double d = Math.abs(x-xl);
        double l = Math.abs(y-yl);
        double angulosInter = Math.toDegrees(Math.atan(d / l));
        return angulosInter;
    }


    /**
     * Metodo para calcular el angulo tita que se utiliza
     * en el calculo de la iluminancia directa
     * @param x coordenada x del punto de analisis
     * @param y coordenada y del punto de analisis
     * @param xl coordenada x de la luminaria
     * @param yl coordenada y de la luminaria
     * @param angul lista para store temporalmente los angulos tita calculados
     */
    private void calcularAnguloTita(double x, double y, double xl, double yl, ArrayList<Double> angul){
        double d = Math.abs(x-xl);
        double l = Math.abs(y-yl);
        double hip = Math.hypot(d, l);
        double angulotita = Math.atan(hip / deltaH);
        angul.add(angulotita);
    }


    /**
     * Metodo para calcular angulos limite para
     * entrar luego a la fotometria para cada plano
     * @param ubicacionPt
     * @param ubicacionLumin
     * @return
     */
    private double calcularAngulosLimite(double ubicacionPt, double ubicacionLumin){
        double d = Math.abs(ubicacionPt-ubicacionLumin);
        //Primer posible error
        double titax = Math.toDegrees(Math.atan(d/deltaH));
        return titax;
    }


    /**
     * 2do calculo para procedimiento de iluminancia
     * que determina el indice del local para cada recinto y ubicar los puntos
     * @param ancho
     * @param largo
     * @param delta diferencia de altura entre la ubicacion de la luminaria y el plano de trabajo
     */
    private void calcularNumeroPuntosAnalisis(double ancho, double largo, double delta) {
        double xporY = ancho * largo;
        double xmasY = ancho + largo;
        IC = xporY / (delta * xmasY);


        if (IC < 1) {
            numPtsAnalisis = 4;
        } else if (1 <= IC && IC <= 2) {
            numPtsAnalisis = 9;
        } else if (2 <= IC && IC <= 3) {
            numPtsAnalisis = 16;
        } else {
            numPtsAnalisis = 25;
        }

        if (0.6 < relacionArea && relacionArea <= 1.5) {

            switch (numPtsAnalisis) {
                case 4:
                    calcularPuntos2(2, 2,puntosAnalisis);
                    break;
                case 9:
                    calcularPuntos2(3, 3, puntosAnalisis);
                    break;
                case 16:
                    calcularPuntos2(4, 4, puntosAnalisis);
                    break;
                case 25:
                    calcularPuntos2(5, 5, puntosAnalisis);
                    break;
                default:
                    break;
            }
            if (ancho>largo){
                scalaMap = (float) ancho;
            }else {
                scalaMap = (float) largo;
            }

        } else {

            if (largo > ancho) {
                switch (numPtsAnalisis) {
                    case 4:
                        calcularPuntos2(2, 3, puntosAnalisis);
                        scalaMap = (float) largo;
                        break;
                    case 9:
                        calcularPuntos2(2, 5, puntosAnalisis);
                        scalaMap = (float) largo;
                        break;
                    case 16:
                        calcularPuntos2(2, 8, puntosAnalisis);
                        scalaMap = (float) largo;
                        break;
                    case 25:
                        calcularPuntos2(3, 9, puntosAnalisis);
                        scalaMap = (float) largo;
                        break;
                    default:
                        break;
                }

            } else {
                switch (numPtsAnalisis) {
                    case 4:
                        calcularPuntos2(3, 2, puntosAnalisis);
                        scalaMap = (float) ancho;
                        break;
                    case 9:
                        calcularPuntos2(5, 2, puntosAnalisis);
                        scalaMap = (float) ancho;
                        break;
                    case 16:
                        calcularPuntos2(8, 2, puntosAnalisis);
                        scalaMap = (float) ancho;
                        break;
                    case 25:
                        calcularPuntos2(9, 3, puntosAnalisis);
                        scalaMap = (float) ancho;
                        break;
                    default:
                        break;
                }

            }
        }
    }

    /** metodo para ubicar las coordenadas de los puntos de analisis
     * utilizado en el calcularNumeroPuntosAnalisis metodo
     * @param numPtsX
     * @param numPtsY
     * @param puntos lista para store temporalmente la ubicacion de los puntos
     */
    private void calcularPuntos(int numPtsX, int numPtsY, ArrayList<Coordenadas> puntos) {
        for (int o = 1; o <= numPtsX; o++) {
            double fraccionX = (double) o / (numPtsX + 1);
            double ubicacionX = anchoLocal * fraccionX;
            for (int p = 1; p <= numPtsY; p++) {
                double fraccionY = (double) p / (numPtsY + 1);
                double ubicacionY = largoLocal * fraccionY;
                Coordenadas ubicacion = new Coordenadas(ubicacionX, ubicacionY);
                puntos.add(ubicacion);
            }
        }
    }
    private void calcularPuntos2(int numPtsX, int numPtsY, ArrayList<Coordenadas> puntos) {
        for (int o = 1; o <= numPtsX; o++) {
            double fraccionX = (double) o / (numPtsX + 1);
            double ubicacionX = anchoLocal * fraccionX;
            for (int p = 1; p <= numPtsY; p++) {
                double fraccionY = (double) p / (numPtsY + 1);
                double ubicacionY = largoLocal * fraccionY;
                Coordenadas ubicacion = new Coordenadas(ubicacionX, ubicacionY);
                PuntosAnalisisEntity punto = new PuntosAnalisisEntity(idProyecto,ubicacionX,ubicacionY);
                puntos.add(ubicacion);
                puntosDeAnalisis.add(punto);
            }
        }
    }

    /**
     * metodo para inicializar el viewmodel
     */
    private void setUpViewModel() {
        ResultadosViewModel.Factory factory =
                new ResultadosViewModel.Factory(getApplication());

        mViewModel = new ViewModelProvider(this, factory).get(ResultadosViewModel.class);
    }


    /** 1er calculo para procedimiento de iluminancia
     *  para determinar deltaH que es la diferencia
     *  de altura entre la luminaria y
     *  la superficie de trabajo
     *
     * @param coordZ
     * @return
     */
    private double calcularDeltaH(double coordZ) {
        return coordZ - altoTrabajo;
    }

    /**
     * 2do calculo para procedimiento de heatmap
     * para determinar ubicacion de puntos de analisis para la malla
     * @param ancho
     * @param largo
     */
    private void calcularNumPuntosGrafDetail(double ancho, double largo){
        if (0.6 < relacionArea && relacionArea <= 1.5 ){
            if (ancho<5){
                calcularPuntos(25,25,puntosGrafDetail);
            } else if (5<ancho && ancho<=10){
                calcularPuntos(30,30,puntosGrafDetail);
            } else {
                calcularPuntos(80,80,puntosGrafDetail);
            }
            if (ancho>largo){
                scalaPrueba = (float) ancho;
            }else {
                scalaPrueba = (float) largo;
            }

        } else {
            if (ancho<5){

                if (largo>ancho){
                    calcularPuntos(20,30,puntosGrafDetail);
                    scalaPrueba = (float) largo;
                }else {
                    calcularPuntos(30,20,puntosGrafDetail);
                    scalaPrueba = (float) ancho;
                }

            } else if (5<ancho && ancho<=10){

                if (largo>ancho){
                    calcularPuntos(20,45,puntosGrafDetail);
                    scalaPrueba = (float) largo;
                }else {
                    calcularPuntos(45,20,puntosGrafDetail);
                    scalaPrueba = (float) ancho;
                }
            } else {

                if (largo>ancho){
                    calcularPuntos(80,100,puntosGrafDetail);
                    scalaPrueba = (float) largo;
                }else {
                    calcularPuntos(100,80,puntosGrafDetail);
                    scalaPrueba = (float) ancho;
                }
            }
        }

    }

    private static int doGradient(double value, double min, double max, int min_color, int max_color) {
        if (value >= max) {
            return max_color;
        }
        if (value <= min) {
            return min_color;
        }
        float[] hsvmin = new float[3];
        float[] hsvmax = new float[3];
        float frac = (float)((value - min) / (max - min));
        Color.RGBToHSV(Color.red(min_color), Color.green(min_color), Color.blue(min_color), hsvmin);
        Color.RGBToHSV(Color.red(max_color), Color.green(max_color), Color.blue(max_color), hsvmax);
        float[] retval = new float[3];
        for (int i = 0; i < 3; i++) {
            retval[i] = interpolate(hsvmin[i], hsvmax[i], frac);
        }
        return Color.HSVToColor(retval);
    }

    private static float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }

}
