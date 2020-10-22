package com.example.firefly.database;

import com.example.firefly.R;
import com.example.firefly.database.dao.AltoDao;
import com.example.firefly.database.dao.AnchoDao;
import com.example.firefly.database.dao.BalastoDao;
import com.example.firefly.database.dao.CatalogoDao;
import com.example.firefly.database.dao.DiametroDao;
import com.example.firefly.database.dao.FotometriaDao;
import com.example.firefly.database.dao.LargoDao;
import com.example.firefly.database.dao.ProfundidadLumDao;
import com.example.firefly.database.entity.AltoEntity;
import com.example.firefly.database.entity.AnchoEntity;
import com.example.firefly.database.entity.BalastoEntity;
import com.example.firefly.database.entity.CatalogoEntity;
import com.example.firefly.database.entity.DiametroEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.LargoEntity;
import com.example.firefly.database.entity.ProfundidadEntity;

import java.util.ArrayList;
import java.util.List;

public class PrepopulateDatabase {

    public PrepopulateDatabase() {

    }

    /**
     * Este metodo es para prepopulated la tabla de Profundidad
     *
     * @param pDao es un objeto de tipo ProfundidadLumDao
     */

    public static void crearProfundidadTable(ProfundidadLumDao pDao) {
        ProfundidadEntity profundidadUno = new ProfundidadEntity(1,0.04, 1);
        ProfundidadEntity profundidadDos = new ProfundidadEntity(2,0.05, 2);
        ProfundidadEntity profundidadTres = new ProfundidadEntity(3,0.08, 4);
        ProfundidadEntity profundidadCuatro = new ProfundidadEntity(4,0.05, 6);

        List<ProfundidadEntity> profundidades = new ArrayList<>();
        profundidades.add(profundidadUno);
        profundidades.add(profundidadDos);
        profundidades.add(profundidadTres);
        profundidades.add(profundidadCuatro);


        pDao.insertAllProfundidad(profundidades);
    }


    /**
     * Este metodo es para prepopulate la tabla principal del Catalogo de luminarias
     *
     * @param cDao es un objeto de tipo Catalogo DAO
     */

    public static void crearCatalogoTable(CatalogoDao cDao) {



        CatalogoEntity luminaria1 = new CatalogoEntity(
                1, "RC127V",
                "W60L60 1x LED36S/840 OC",
                3600, 1.0,
                3600, 36,
                80, 4000,
                "OC",R.mipmap.luminaria_rcv127v,R.mipmap.fotometria_rcv127v);

        CatalogoEntity luminaria2 = new CatalogoEntity(
                2, "TBS165",
                "G 2XTL5-28W HFS C6 840",
                5250, 0.7,
                3675, 61,
                80, 4000,
                "C6",R.mipmap.luminaria_tbs165g,R.mipmap.fotometria_tbs165g);

        CatalogoEntity luminaria3 = new CatalogoEntity(
                3, "SM134V",
                "PSD W20L120 1xLED37S/840 NOC",
                3700, 1,
                3700, 36,
                80, 4000,
                "NOC",R.mipmap.luminaria_sm134v,R.mipmap.fotometria_sm134v);

        CatalogoEntity luminaria4 = new CatalogoEntity(
                4, "TCS165",
                "4XTL5-14W HFP C3 840",
                4800, 0.66,
                3168, 61,
                80, 4000,
                "C3",R.mipmap.luminaria_tcs165,R.mipmap.fotometria_tcs165);

        CatalogoEntity luminaria5 = new CatalogoEntity(
                5, "PT570P",
                "1xLED25S/840 WB",
                2942, 1,
                2942, 36,
                80, 4000,
                "WB",R.mipmap.luminaria_pt570p,R.mipmap.fotometria_pt570p);

        CatalogoEntity luminaria6 = new CatalogoEntity(
                6, "TPS760",
                "2xTL5-28W HFP AC-MLO 840",
                5250, 0.62,
                3255, 61,
                80, 4000,
                "AC-MLO",R.mipmap.luminaria_tps760,R.mipmap.fotometria_tps760);

        cDao.insertLuminariasCatalogo(luminaria1);
        cDao.insertLuminariasCatalogo(luminaria2);
        cDao.insertLuminariasCatalogo(luminaria3);
        cDao.insertLuminariasCatalogo(luminaria4);
        cDao.insertLuminariasCatalogo(luminaria5);
        cDao.insertLuminariasCatalogo(luminaria6);

    }

    /**
     * Este metodo es para prepopulate la tabla
     * de largo de luminarias del catalogo
     *
     * @param lDao es un objeto de tipo LargoDao
     */

    public static void crearLargoTable(LargoDao lDao) {

        LargoEntity largoUno = new LargoEntity(1,0.6, 1);
        LargoEntity largoDos = new LargoEntity(2,1.2, 2);
        LargoEntity largoTres = new LargoEntity(3,1.17, 3);
        LargoEntity largoCuatro = new LargoEntity(4,0.58, 4);
        LargoEntity largoCinco = new LargoEntity(5, 1.26, 6);

        List<LargoEntity> largos = new ArrayList<>();

        largos.add(largoUno);
        largos.add(largoDos);
        largos.add(largoTres);
        largos.add(largoCuatro);
        largos.add(largoCinco);

        lDao.insertAllLargo(largos);
    }
    /**
     * Este metodo es para prepopulate la tabla
     * de anchos de luminarias del catalogo
     *
     * @param ADao es un objeto de tipo AnchoDao
     */
    public static void crearAnchoTable(AnchoDao ADao) {

        AnchoEntity anchoUno = new AnchoEntity(1,0.6,1);
        AnchoEntity anchoDos = new AnchoEntity(2,0.3,2);
        AnchoEntity anchoTres = new AnchoEntity(3,0.2,3);
        AnchoEntity anchoCuatro = new AnchoEntity(4,0.58,4);
        AnchoEntity anchoCinco = new AnchoEntity(5,0.27,6);

        List<AnchoEntity> anchos = new ArrayList<>();
        anchos.add(anchoUno);
        anchos.add(anchoDos);
        anchos.add(anchoTres);
        anchos.add(anchoCuatro);
        anchos.add(anchoCinco);

        ADao.insertAllAncho(anchos);
    }

    /**
     * Este metodo es para prepopulate la tabla
     * de altos de luminarias del catalogo
     *
     * @param AlDao es un objeto de tipo AnchoDao
     */
    public static void crearAltoTable(AltoDao AlDao) {

        AltoEntity altoUno = new AltoEntity(1,0.05,3);
        AltoEntity altoDos = new AltoEntity(2,0.4,5);

        List<AltoEntity> altos = new ArrayList<>();
        altos.add(altoUno);
        altos.add(altoDos);

        AlDao.insertAllAltos(altos);
    }

    /**
     * Este metodo es para prepopulate la tabla
     * de diametros de luminarias del catalogo
     *
     * @param dDao es un objeto de tipo AnchoDao
     */
    public static void crearDiametroTable(DiametroDao dDao) {

        DiametroEntity diametroUno = new DiametroEntity(1,0.25,5);

        dDao.insertDiametro(diametroUno);
    }

    /**
     * Este metodo es para prepopulate la tabla
     * de balasto de luminarias del catalogo
     *
     * @param bDao es un objeto de tipo BalastoDao
     */
    public static void crearBalastoTable(BalastoDao bDao) {

        BalastoEntity balastoUno = new BalastoEntity(1,"HF Estandar",2);
        BalastoEntity balastoDos = new BalastoEntity(2,"HF Performer",4);
        BalastoEntity balastoTres = new BalastoEntity(3,"HF Performer",6);

        List<BalastoEntity> balastos = new ArrayList<>();
        balastos.add(balastoUno);
        balastos.add(balastoDos);
        balastos.add(balastoTres);

        bDao.insertAllBalasto(balastos);
    }




    /**
     * Este metodo es para prepopulate la tabla
     * de fotometrias de luminarias del catalogo
     *
     * @param fDao es un objeto de tipo BalastoDao
     */

    public static void crearFotometria (FotometriaDao fDao){

        List<FotometriaEntity> i= new ArrayList<>();

        // Esta es la tabla de intensidades de la luminaria RC127V para el plano 90-270
        FotometriaEntity intensidad1 = new FotometriaEntity(1,0.200835,423.237,1);
        FotometriaEntity intensidad2 = new FotometriaEntity(1,10.2426,418.566,1);
        FotometriaEntity intensidad3 = new FotometriaEntity(1,20.2843,399.103,1);
        FotometriaEntity intensidad4 = new FotometriaEntity(1,30.326,357.841,1);
        FotometriaEntity intensidad5 = new FotometriaEntity(1,31.6954,349.278,1);
        FotometriaEntity intensidad6 = new FotometriaEntity(1,38.0856,299.452,1);
        FotometriaEntity intensidad7 = new FotometriaEntity(1,40.3678,279.21,1);
        FotometriaEntity intensidad8 = new FotometriaEntity(1,43.715,248.848,1);
        FotometriaEntity intensidad9 = new FotometriaEntity(1,49.1923,199.801,1);
        FotometriaEntity intensidad10 = new FotometriaEntity(1,50.4095,188.123,1);
        FotometriaEntity intensidad11 = new FotometriaEntity(1,55.5825,149.197,1);
        FotometriaEntity intensidad12 = new FotometriaEntity(1,60.4512,114.163,1);
        FotometriaEntity intensidad13 = new FotometriaEntity(1,62.7334,98.5924,1);
        FotometriaEntity intensidad14 = new FotometriaEntity(1,69.7322,54.995,1);

        // Esta es la tabla de intensidades de la luminaria RC127V para el plano 0-180
        FotometriaEntity intensidad15 = new FotometriaEntity(2,0.200835,424.794,1);
        FotometriaEntity intensidad16 = new FotometriaEntity(2,10.2426,423.237,1);
        FotometriaEntity intensidad17 = new FotometriaEntity(2,20.2843,414.674,1);
        FotometriaEntity intensidad18 = new FotometriaEntity(2,26.3702,399.882,1);
        FotometriaEntity intensidad19 = new FotometriaEntity(2,30.326,385.09,1);
        FotometriaEntity intensidad20 = new FotometriaEntity(2,35.6512,349.278,1);
        FotometriaEntity intensidad21 = new FotometriaEntity(2,40.3678,307.237,1);
        FotometriaEntity intensidad22 = new FotometriaEntity(2,41.2806,298.673,1);
        FotometriaEntity intensidad23 = new FotometriaEntity(2,45.8451,248.069,1);
        FotometriaEntity intensidad24 = new FotometriaEntity(2,50.2573,204.472,1);
        FotometriaEntity intensidad25 = new FotometriaEntity(2,50.8659,199.022,1);
        FotometriaEntity intensidad26 = new FotometriaEntity(2,56.6475,149.197,1);
        FotometriaEntity intensidad27 = new FotometriaEntity(2,60.4512,119.613,1);
        FotometriaEntity intensidad28 = new FotometriaEntity(2,63.6463,98.5924,1);
        FotometriaEntity intensidad29 = new FotometriaEntity(2,70.0365,58.8876,1);

        // Esta es la tabla de intensidades de la luminaria TBS165 para el plano 90-270
        FotometriaEntity intensidad30 = new FotometriaEntity(1,0.109034,274.405,2);
        FotometriaEntity intensidad31 = new FotometriaEntity(1,10.1038,269.94,2);
        FotometriaEntity intensidad32 = new FotometriaEntity(1,20.0987,253.571,2);
        FotometriaEntity intensidad33 = new FotometriaEntity(1,26.459,238.69,2);
        FotometriaEntity intensidad34 = new FotometriaEntity(1,30.0935,227.53,2);
        FotometriaEntity intensidad35 = new FotometriaEntity(1,38.4528,198.512,2);
        FotometriaEntity intensidad36 = new FotometriaEntity(1,40.0883,191.815,2);
        FotometriaEntity intensidad37 = new FotometriaEntity(1,46.812,159.077,2);
        FotometriaEntity intensidad38 = new FotometriaEntity(1,50.2648,136.756,2);
        FotometriaEntity intensidad39 = new FotometriaEntity(1,52.9907,118.899,2);
        FotometriaEntity intensidad40 = new FotometriaEntity(1,57.5337,79.4643,2);
        FotometriaEntity intensidad41 = new FotometriaEntity(1,60.0779,52.6786,2);
        FotometriaEntity intensidad42 = new FotometriaEntity(1,61.7134,39.2857,2);
        FotometriaEntity intensidad43 = new FotometriaEntity(1,64.9844,11.756,2);
        FotometriaEntity intensidad44 = new FotometriaEntity(1,69.5275,1.33929,2);

        // Esta es la taba de intensidades de la luminaria TBS165 para el plano 0-180
        FotometriaEntity intensidad45 = new FotometriaEntity(2,0.109034,274.405,2);
        FotometriaEntity intensidad46 = new FotometriaEntity(2,5.1973,278.869,2);
        FotometriaEntity intensidad47 = new FotometriaEntity(2,10.1038,297.47,2);
        FotometriaEntity intensidad48 = new FotometriaEntity(2,14.8287,318.304,2);
        FotometriaEntity intensidad49 = new FotometriaEntity(2,20.0987,335.417,2);
        FotometriaEntity intensidad50 = new FotometriaEntity(2,25.1869,354.762,2);
        FotometriaEntity intensidad51 = new FotometriaEntity(2,30.0935,355.506,2);
        FotometriaEntity intensidad52 = new FotometriaEntity(2,35,341.369,2);
        FotometriaEntity intensidad53 = new FotometriaEntity(2,37.5441,318.304,2);
        FotometriaEntity intensidad54 = new FotometriaEntity(2,40.0883,295.238,2);
        FotometriaEntity intensidad55 = new FotometriaEntity(2,41.3603,278.125,2);
        FotometriaEntity intensidad56 = new FotometriaEntity(2,43.3593,237.946,2);
        FotometriaEntity intensidad57 = new FotometriaEntity(2,45.3583,199.256,2);
        FotometriaEntity intensidad58 = new FotometriaEntity(2,46.812,159.077,2);
        FotometriaEntity intensidad59 = new FotometriaEntity(2,48.4476,118.155,2);
        FotometriaEntity intensidad60 = new FotometriaEntity(2,49.9013,79.4643,2);
        FotometriaEntity intensidad61 = new FotometriaEntity(2,50.2648,67.5595,2);
        FotometriaEntity intensidad62 = new FotometriaEntity(2,53.3541,38.5417,2);
        FotometriaEntity intensidad63 = new FotometriaEntity(2,55.1713,22.9167,2);
        FotometriaEntity intensidad64 = new FotometriaEntity(2,59.8962,2.08333,2);
        FotometriaEntity intensidad65 = new FotometriaEntity(2,63.5306,0.595238,2);
        FotometriaEntity intensidad66 = new FotometriaEntity(2,69.7092,0.595238,2);

        // Esta es la tabla de intensidades de la luminaria SM134V para el plano 90-270
        FotometriaEntity intensidad67 = new FotometriaEntity(1,0.362963,434.524,3);
        FotometriaEntity intensidad68 = new FotometriaEntity(1,10.2472,425.43,3);
        FotometriaEntity intensidad69 = new FotometriaEntity(1,20.2935,400.628,3);
        FotometriaEntity intensidad70 = new FotometriaEntity(1,30.1778,366.733,3);
        FotometriaEntity intensidad71 = new FotometriaEntity(1,33.2565,349.372,3);
        FotometriaEntity intensidad72 = new FotometriaEntity(1,40.5481,309.689,3);
        FotometriaEntity intensidad73 = new FotometriaEntity(1,41.8444,298.115,3);
        FotometriaEntity intensidad74 = new FotometriaEntity(1,48.0019,249.339,3);
        FotometriaEntity intensidad75 = new FotometriaEntity(1,50.4324,224.537,3);
        FotometriaEntity intensidad76 = new FotometriaEntity( 1,52.5389,200.562,3);
        FotometriaEntity intensidad77 = new FotometriaEntity(1,56.5898,150.132,3);
        FotometriaEntity intensidad78 = new FotometriaEntity(1,60.4787,114.583,3);
        FotometriaEntity intensidad79 = new FotometriaEntity(1,62.4231,98.8757,3);
        FotometriaEntity intensidad80 = new FotometriaEntity(1,69.8769,63.3267,3);

        // Esta es la tabla de intensidades de la luminaria SM134V para el plano 0-180
        FotometriaEntity intensidad81 = new FotometriaEntity(2,0.200926,436.177,3);
        FotometriaEntity intensidad82 = new FotometriaEntity(2,10.4093,429.563,3);
        FotometriaEntity intensidad83 = new FotometriaEntity(2,20.2935,410.549,3);
        FotometriaEntity intensidad84 = new FotometriaEntity(2,30.3398,378.307,3);
        FotometriaEntity intensidad85 = new FotometriaEntity(2,36.1731,349.372,3);
        FotometriaEntity intensidad86 = new FotometriaEntity(2,40.5481,314.649,3);
        FotometriaEntity intensidad87 = new FotometriaEntity(2,41.1963,301.422,3);
        FotometriaEntity intensidad88 = new FotometriaEntity(2,44.5991,249.339,3);
        FotometriaEntity intensidad89 = new FotometriaEntity(2,48.0019,200.562,3);
        FotometriaEntity intensidad90 = new FotometriaEntity(2,50.4324,168.32,3);
        FotometriaEntity intensidad91 = new FotometriaEntity(2,52.2148,150.132,3);
        FotometriaEntity intensidad92 = new FotometriaEntity(2,58.0481,100.529,3);
        FotometriaEntity intensidad93 = new FotometriaEntity(2,60.3167,86.4749,3);
        FotometriaEntity intensidad94 = new FotometriaEntity(2,66.9602,50.0992,3);
        FotometriaEntity intensidad95 = new FotometriaEntity(2,69.8769,38.5251,3);

        // Esta es la tabla de intensidades de la luminaria TCS165 para el plano 90-270
        FotometriaEntity intensidad96 = new FotometriaEntity(1,-0.133472,332.09,4);
        FotometriaEntity intensidad97 = new FotometriaEntity(1,8.96689,319.662,4);
        FotometriaEntity intensidad98 = new FotometriaEntity(1,10.0286,315.312,4);
        FotometriaEntity intensidad99 = new FotometriaEntity(1,19.8873,287.348,4);
        FotometriaEntity intensidad100 = new FotometriaEntity(1,21.8591,279.891,4);
        FotometriaEntity intensidad101 = new FotometriaEntity(1,29.8977,247.576,4);
        FotometriaEntity intensidad102 = new FotometriaEntity(1,31.5661,239.498,4);
        FotometriaEntity intensidad103 = new FotometriaEntity(1,39.9081,199.727,4);
        FotometriaEntity intensidad104 = new FotometriaEntity(1,43.5483,181.705,4);
        FotometriaEntity intensidad105 = new FotometriaEntity(1,47.3401,160.577,4);
        FotometriaEntity intensidad106 = new FotometriaEntity(1,50.0702,145.662,4);
        FotometriaEntity intensidad107 = new FotometriaEntity(1,54.9237,120.184,4);
        FotometriaEntity intensidad108 = new FotometriaEntity(1,59.9289,92.8412,4);
        FotometriaEntity intensidad109 = new FotometriaEntity(1,62.8107,80.4126,4);
        FotometriaEntity intensidad110 = new FotometriaEntity(1,69.4843,51.2056,4);

        // Esta es la tabla de intensidades de la luminaria TCS165 para el plano 0-180
        FotometriaEntity intensidad111 = new FotometriaEntity(2,0.0182007,332.09,4);
        FotometriaEntity intensidad112 = new FotometriaEntity(2,3.96169,319.662,4);
        FotometriaEntity intensidad113 = new FotometriaEntity(2,10.0286,292.941,4);
        FotometriaEntity intensidad114 = new FotometriaEntity(2,16.8539,279.891,4);
        FotometriaEntity intensidad115 = new FotometriaEntity(2,19.8873,274.919,4);
        FotometriaEntity intensidad116 = new FotometriaEntity(2,25.9542,263.734,4);
        FotometriaEntity intensidad117 = new FotometriaEntity(2,29.8977,256.276,4);
        FotometriaEntity intensidad118 = new FotometriaEntity(2,34.5996,240.741,4);
        FotometriaEntity intensidad119 = new FotometriaEntity(2,40.2115,207.184,4);
        FotometriaEntity intensidad120 = new FotometriaEntity(2,43.6999,181.084,4);
        FotometriaEntity intensidad121 = new FotometriaEntity(2,46.2784,159.955,4);
        FotometriaEntity intensidad122 = new FotometriaEntity(2,49.9185,135.72,4);
        FotometriaEntity intensidad123 = new FotometriaEntity(2,51.5869,119.563,4);
        FotometriaEntity intensidad124 = new FotometriaEntity(2,55.5304,80.4126,4);
        FotometriaEntity intensidad125 = new FotometriaEntity(2,58.7155,40.6413,4);
        FotometriaEntity intensidad126 = new FotometriaEntity(2,59.9289,25.7271,4);
        FotometriaEntity intensidad127 = new FotometriaEntity(2,64.7825,2.11285,4);

        // Esta es la tabla de intensidades de la luminaria PT570P para el plano 0-180 y 90-270
        FotometriaEntity intensidad128 = new FotometriaEntity(1,0.0370178,1144.18,5);
        FotometriaEntity intensidad129 = new FotometriaEntity(1,2.24547,1197.82,5);
        FotometriaEntity intensidad130 = new FotometriaEntity(1,2.87645,1215.7,5);
        FotometriaEntity intensidad131 = new FotometriaEntity(1,6.24171,1235.21,5);
        FotometriaEntity intensidad132 = new FotometriaEntity(1,8.97598,1267.72,5);
        FotometriaEntity intensidad133 = new FotometriaEntity(1,10.0276,1266.09,5);
        FotometriaEntity intensidad134 = new FotometriaEntity(1,12.1309,1259.59,5);
        FotometriaEntity intensidad135 = new FotometriaEntity(1,12.8671,1235.21,5);
        FotometriaEntity intensidad136 = new FotometriaEntity(1,13.8135,1196.19,5);
        FotometriaEntity intensidad137 = new FotometriaEntity(1,14.9703,1145.8,5);
        FotometriaEntity intensidad138 = new FotometriaEntity(1,17.3891,1045.02,5);
        FotometriaEntity intensidad139 = new FotometriaEntity(1,20.0182,992.998,5);
        FotometriaEntity intensidad140 = new FotometriaEntity(1,20.9647,960.487,5);
        FotometriaEntity intensidad141 = new FotometriaEntity(1,22.6473,898.715,5);
        FotometriaEntity intensidad142 = new FotometriaEntity(1,25.6971,745.911,5);
        FotometriaEntity intensidad143 = new FotometriaEntity(1,28.4314,594.733,5);
        FotometriaEntity intensidad144 = new FotometriaEntity(1,30.0088,495.573,5);
        FotometriaEntity intensidad145 = new FotometriaEntity(1,30.745,443.555,5);
        FotometriaEntity intensidad146 = new FotometriaEntity(1,33.2689,294.002,5);
        FotometriaEntity intensidad147 = new FotometriaEntity(1,37.5806,142.824,5);
        FotometriaEntity intensidad148 = new FotometriaEntity(1,40.1046,89.1802,5);
        FotometriaEntity intensidad149 = new FotometriaEntity(1,49.99,14.4039,5);
        FotometriaEntity intensidad150 = new FotometriaEntity(1,59.9806,1.39929,5);
        FotometriaEntity intensidad151 = new FotometriaEntity(1,67,0,5);

        FotometriaEntity intensidad208 = new FotometriaEntity(2,0.0370178,1144.18,5);
        FotometriaEntity intensidad209 = new FotometriaEntity(2,2.24547,1197.82,5);
        FotometriaEntity intensidad210 = new FotometriaEntity(2,2.87645,1215.7,5);
        FotometriaEntity intensidad211 = new FotometriaEntity(2,6.24171,1235.21,5);
        FotometriaEntity intensidad212 = new FotometriaEntity(2,8.97598,1267.72,5);
        FotometriaEntity intensidad213 = new FotometriaEntity(2,10.0276,1266.09,5);
        FotometriaEntity intensidad214 = new FotometriaEntity(2,12.1309,1259.59,5);
        FotometriaEntity intensidad215 = new FotometriaEntity(2,12.8671,1235.21,5);
        FotometriaEntity intensidad216 = new FotometriaEntity(2,13.8135,1196.19,5);
        FotometriaEntity intensidad217 = new FotometriaEntity(2,14.9703,1145.8,5);
        FotometriaEntity intensidad218 = new FotometriaEntity(2,17.3891,1045.02,5);
        FotometriaEntity intensidad219 = new FotometriaEntity(2,20.0182,992.998,5);
        FotometriaEntity intensidad220 = new FotometriaEntity(2,20.9647,960.487,5);
        FotometriaEntity intensidad221 = new FotometriaEntity(2,22.6473,898.715,5);
        FotometriaEntity intensidad222 = new FotometriaEntity(2,25.6971,745.911,5);
        FotometriaEntity intensidad223 = new FotometriaEntity(2,28.4314,594.733,5);
        FotometriaEntity intensidad224 = new FotometriaEntity(2,30.0088,495.573,5);
        FotometriaEntity intensidad225 = new FotometriaEntity(2,30.745,443.555,5);
        FotometriaEntity intensidad226 = new FotometriaEntity(2,33.2689,294.002,5);
        FotometriaEntity intensidad227 = new FotometriaEntity(2,37.5806,142.824,5);
        FotometriaEntity intensidad228 = new FotometriaEntity(2,40.1046,89.1802,5);
        FotometriaEntity intensidad229 = new FotometriaEntity(2,49.99,14.4039,5);
        FotometriaEntity intensidad230 = new FotometriaEntity(2,59.9806,1.39929,5);
        FotometriaEntity intensidad231 = new FotometriaEntity(2,67,0,5);

        // Esta es la tabla de intensidades de la luminaria TPS760 para el plano 90-270
        FotometriaEntity intensidad152 = new FotometriaEntity(1,0.309896,330.537,6);
        FotometriaEntity intensidad153 = new FotometriaEntity(1,6.23437,328.664,6);
        FotometriaEntity intensidad154 = new FotometriaEntity(1,10.3359,324.295,6);
        FotometriaEntity intensidad155 = new FotometriaEntity(1,14.2856,318.677,6);
        FotometriaEntity intensidad156 = new FotometriaEntity(1,20.362,302.447,6);
        FotometriaEntity intensidad157 = new FotometriaEntity(1,28.8689,278.727,6);
        FotometriaEntity intensidad158 = new FotometriaEntity(1,30.388,270.612,6);
        FotometriaEntity intensidad159 = new FotometriaEntity(1,35.401,250.012,6);
        FotometriaEntity intensidad160 = new FotometriaEntity(1,37.224,239.401,6);
        FotometriaEntity intensidad161 = new FotometriaEntity(1,40.566,218.801,6);
        FotometriaEntity intensidad162 = new FotometriaEntity(1,41.6293,204.444,6);
        FotometriaEntity intensidad163 = new FotometriaEntity(1,42.9965,189.463,6);
        FotometriaEntity intensidad164 = new FotometriaEntity(1,44.2118,176.979,6);
        FotometriaEntity intensidad165 = new FotometriaEntity(1,45.4271,163.246,6);
        FotometriaEntity intensidad166 = new FotometriaEntity(1,46.1866,152.634,6);
        FotometriaEntity intensidad167 = new FotometriaEntity(1,47.25,139.526,6);
        FotometriaEntity intensidad168 = new FotometriaEntity(1,48.4653,118.926,6);
        FotometriaEntity intensidad169 = new FotometriaEntity(1,50.4401,85.8427,6);
        FotometriaEntity intensidad170 = new FotometriaEntity(1,53.3264,64.6192,6);
        FotometriaEntity intensidad171 = new FotometriaEntity(1,55.605,47.1411,6);
        FotometriaEntity intensidad172 = new FotometriaEntity(1,58.3394,35.2809,6);
        FotometriaEntity intensidad173 = new FotometriaEntity(1,60.6181,25.2934,6);
        FotometriaEntity intensidad174 = new FotometriaEntity(1,63.3524,19.0512,6);
        FotometriaEntity intensidad175 = new FotometriaEntity(1,65.783,14.0574,6);
        FotometriaEntity intensidad176 = new FotometriaEntity(1,68.9731,11.5605,6);
        FotometriaEntity intensidad177 = new FotometriaEntity(1,70.1884,12.1848,6);


        FotometriaEntity intensidad178 = new FotometriaEntity(2,0.309896,329.913,6);
        FotometriaEntity intensidad179 = new FotometriaEntity(2,6.23438,329.288,6);
        FotometriaEntity intensidad180 = new FotometriaEntity(2,10.4878,326.167,6);
        FotometriaEntity intensidad181 = new FotometriaEntity(2,14.1337,319.925,6);
        FotometriaEntity intensidad182 = new FotometriaEntity(2,20.362,304.32,6);
        FotometriaEntity intensidad183 = new FotometriaEntity(2,28.717,279.351,6);
        FotometriaEntity intensidad184 = new FotometriaEntity(2,30.5399,271.236,6);
        FotometriaEntity intensidad185 = new FotometriaEntity(2,35.553,250.637,6);
        FotometriaEntity intensidad186 = new FotometriaEntity(2,37.3759,240.649,6);
        FotometriaEntity intensidad187 = new FotometriaEntity(2,40.566,221.923,6);
        FotometriaEntity intensidad188 = new FotometriaEntity(2,42.237,205.069,6);
        FotometriaEntity intensidad189 = new FotometriaEntity(2,43.4523,194.457,6);
        FotometriaEntity intensidad190 = new FotometriaEntity(2,45.1233,178.851,6);
        FotometriaEntity intensidad191 = new FotometriaEntity(2,46.1866,166.991,6);
        FotometriaEntity intensidad192 = new FotometriaEntity(2,46.7943,154.507,6);
        FotometriaEntity intensidad193 = new FotometriaEntity(2,47.5538,139.526,6);
        FotometriaEntity intensidad194 = new FotometriaEntity(2,48.3134,124.544,6);
        FotometriaEntity intensidad195 = new FotometriaEntity(2,49.2248,110.811,6);
        FotometriaEntity intensidad196 = new FotometriaEntity(2,49.8325,98.9513,6);
        FotometriaEntity intensidad197 = new FotometriaEntity(2,50.7439,85.8427,6);
        FotometriaEntity intensidad198 = new FotometriaEntity(2,52.1111,75.231,6);
        FotometriaEntity intensidad199 = new FotometriaEntity(2,53.7821,63.3708,6);
        FotometriaEntity intensidad200 = new FotometriaEntity(2,54.9974,53.3833,6);
        FotometriaEntity intensidad201 = new FotometriaEntity(2,56.9722,41.5231,6);
        FotometriaEntity intensidad202 = new FotometriaEntity(2,59.2509,33.4082,6);
        FotometriaEntity intensidad203 = new FotometriaEntity(2,60.9219,25.9176,6);
        FotometriaEntity intensidad204 = new FotometriaEntity(2,63.2005,19.6754,6);
        FotometriaEntity intensidad205 = new FotometriaEntity(2,65.783,15.3059,6);
        FotometriaEntity intensidad206 = new FotometriaEntity(2,68.9731,12.809,6);
        FotometriaEntity intensidad207 = new FotometriaEntity(2,70.3403,12.809,6);


        i.add(intensidad1); i.add(intensidad2); i.add(intensidad3); i.add(intensidad4); i.add(intensidad5);
        i.add(intensidad6); i.add(intensidad7); i.add(intensidad8); i.add(intensidad9); i.add(intensidad10);
        i.add(intensidad11); i.add(intensidad12); i.add(intensidad13); i.add(intensidad14); i.add(intensidad15);
        i.add(intensidad16); i.add(intensidad17); i.add(intensidad18); i.add(intensidad19); i.add(intensidad20);
        i.add(intensidad21); i.add(intensidad22); i.add(intensidad23); i.add(intensidad24); i.add(intensidad25);
        i.add(intensidad26); i.add(intensidad27); i.add(intensidad28); i.add(intensidad29); i.add(intensidad30);
        i.add(intensidad31); i.add(intensidad32); i.add(intensidad33); i.add(intensidad34); i.add(intensidad35);
        i.add(intensidad36); i.add(intensidad37); i.add(intensidad38); i.add(intensidad39); i.add(intensidad40);
        i.add(intensidad41); i.add(intensidad42); i.add(intensidad43); i.add(intensidad44); i.add(intensidad45);
        i.add(intensidad46); i.add(intensidad47); i.add(intensidad48); i.add(intensidad49); i.add(intensidad50);
        i.add(intensidad51); i.add(intensidad52); i.add(intensidad53); i.add(intensidad54); i.add(intensidad55);
        i.add(intensidad56); i.add(intensidad57); i.add(intensidad58); i.add(intensidad59); i.add(intensidad60);
        i.add(intensidad61); i.add(intensidad62); i.add(intensidad63); i.add(intensidad64); i.add(intensidad65);
        i.add(intensidad66); i.add(intensidad67); i.add(intensidad68); i.add(intensidad69); i.add(intensidad70);
        i.add(intensidad71); i.add(intensidad72); i.add(intensidad73); i.add(intensidad74); i.add(intensidad75);
        i.add(intensidad76); i.add(intensidad77); i.add(intensidad78); i.add(intensidad79); i.add(intensidad80);
        i.add(intensidad81); i.add(intensidad82); i.add(intensidad83); i.add(intensidad84); i.add(intensidad85);
        i.add(intensidad86); i.add(intensidad87); i.add(intensidad88); i.add(intensidad89); i.add(intensidad90);
        i.add(intensidad91); i.add(intensidad92); i.add(intensidad93); i.add(intensidad94); i.add(intensidad95);
        i.add(intensidad96); i.add(intensidad97); i.add(intensidad98); i.add(intensidad99); i.add(intensidad100);
        i.add(intensidad101); i.add(intensidad102); i.add(intensidad103); i.add(intensidad104); i.add(intensidad105);
        i.add(intensidad106); i.add(intensidad107); i.add(intensidad108); i.add(intensidad109); i.add(intensidad110);
        i.add(intensidad111); i.add(intensidad112); i.add(intensidad113); i.add(intensidad114); i.add(intensidad115);
        i.add(intensidad116); i.add(intensidad117); i.add(intensidad118); i.add(intensidad119); i.add(intensidad120);
        i.add(intensidad121); i.add(intensidad122); i.add(intensidad123); i.add(intensidad124); i.add(intensidad125);
        i.add(intensidad126); i.add(intensidad127); i.add(intensidad128); i.add(intensidad129); i.add(intensidad130);
        i.add(intensidad131); i.add(intensidad132); i.add(intensidad133); i.add(intensidad134); i.add(intensidad135);
        i.add(intensidad136); i.add(intensidad137); i.add(intensidad138); i.add(intensidad139); i.add(intensidad140);
        i.add(intensidad141); i.add(intensidad142); i.add(intensidad143); i.add(intensidad144); i.add(intensidad145);
        i.add(intensidad146); i.add(intensidad147); i.add(intensidad148); i.add(intensidad149); i.add(intensidad150);
        i.add(intensidad151); i.add(intensidad152); i.add(intensidad153); i.add(intensidad154); i.add(intensidad155);
        i.add(intensidad156); i.add(intensidad157); i.add(intensidad158); i.add(intensidad159); i.add(intensidad160);
        i.add(intensidad161); i.add(intensidad162); i.add(intensidad163); i.add(intensidad164); i.add(intensidad165);
        i.add(intensidad166); i.add(intensidad167); i.add(intensidad168); i.add(intensidad169); i.add(intensidad170);
        i.add(intensidad171); i.add(intensidad172); i.add(intensidad173); i.add(intensidad174); i.add(intensidad175);
        i.add(intensidad176); i.add(intensidad177); i.add(intensidad178); i.add(intensidad179); i.add(intensidad180);
        i.add(intensidad181); i.add(intensidad182); i.add(intensidad183); i.add(intensidad184); i.add(intensidad185);
        i.add(intensidad186); i.add(intensidad187); i.add(intensidad188); i.add(intensidad189); i.add(intensidad190);
        i.add(intensidad191); i.add(intensidad192); i.add(intensidad193); i.add(intensidad194); i.add(intensidad195);
        i.add(intensidad196); i.add(intensidad197); i.add(intensidad198); i.add(intensidad199); i.add(intensidad200);
        i.add(intensidad201); i.add(intensidad202); i.add(intensidad203); i.add(intensidad204); i.add(intensidad205);
        i.add(intensidad206); i.add(intensidad207);i.add(intensidad208); i.add(intensidad209); i.add(intensidad210);
        i.add(intensidad211); i.add(intensidad212); i.add(intensidad213); i.add(intensidad214); i.add(intensidad215);
        i.add(intensidad216); i.add(intensidad217); i.add(intensidad218); i.add(intensidad219); i.add(intensidad220);
        i.add(intensidad221); i.add(intensidad222); i.add(intensidad223); i.add(intensidad224); i.add(intensidad225);
        i.add(intensidad226); i.add(intensidad227); i.add(intensidad228); i.add(intensidad229); i.add(intensidad230);
        i.add(intensidad231);

        fDao.insertIntensidad(i);

    }


}
