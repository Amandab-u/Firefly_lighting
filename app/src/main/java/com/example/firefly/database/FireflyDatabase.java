package com.example.firefly.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.dao.AEResultadosDao;
import com.example.firefly.database.dao.AltoDao;
import com.example.firefly.database.dao.AnchoDao;
import com.example.firefly.database.dao.BalastoDao;
import com.example.firefly.database.dao.CatalogoDao;
import com.example.firefly.database.dao.ConsumoElectricoDao;
import com.example.firefly.database.dao.DatosLocalDao;
import com.example.firefly.database.dao.DatosLuminariaDao;
import com.example.firefly.database.dao.DiametroDao;
import com.example.firefly.database.dao.EncuestaResultsDao;
import com.example.firefly.database.dao.FotometriaDao;
import com.example.firefly.database.dao.IlumiCalculadaDao;
import com.example.firefly.database.dao.LargoDao;
import com.example.firefly.database.dao.ProfundidadLumDao;
import com.example.firefly.database.dao.ProyectosDao;
import com.example.firefly.database.dao.PuntosAnalisisDao;
import com.example.firefly.database.dao.ResultadosBasicosDao;
import com.example.firefly.database.dao.VerificacionDao;
import com.example.firefly.database.entity.AEResultadosEntity;
import com.example.firefly.database.entity.AltoEntity;
import com.example.firefly.database.entity.AnchoEntity;
import com.example.firefly.database.entity.BalastoEntity;
import com.example.firefly.database.entity.CatalogoEntity;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.DiametroEntity;
import com.example.firefly.database.entity.EncuestaResultsEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.LargoEntity;
import com.example.firefly.database.entity.PlanosAngulosEntity;
import com.example.firefly.database.entity.ProfundidadEntity;
import com.example.firefly.database.entity.ProyectosEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.ResultadosBasicosEntity;
import com.example.firefly.database.entity.VerificacionEntity;


@Database(entities={
        CatalogoEntity.class,
        ProfundidadEntity.class,
        LargoEntity.class,
        AnchoEntity.class,
        AltoEntity.class,
        DiametroEntity.class,
        BalastoEntity.class,
        ProyectosEntity.class,
        DatosLocalEntity.class,
        DatosLuminariaEntity.class,
        PlanosAngulosEntity.class,
        FotometriaEntity.class,
        ConsumoElectricoEntity.class,
        PuntosAnalisisEntity.class,
        VerificacionEntity.class,
        ResultadosBasicosEntity.class,
        IlumiCalculadaEntity.class,
        EncuestaResultsEntity.class,
        AEResultadosEntity.class}, version = 1)
public abstract class FireflyDatabase extends RoomDatabase {

    private static FireflyDatabase INSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "firefly_db";

    public abstract CatalogoDao catalogoDao();
    public abstract ProfundidadLumDao profundidadDao();
    public abstract LargoDao largoDao();
    public abstract AnchoDao anchoDao();
    public abstract AltoDao altoDao();
    public abstract DiametroDao diametroDao();
    public abstract BalastoDao balastoDao();
    public abstract ProyectosDao proyectosDao();
    public abstract FotometriaDao fotometriaDao();
    public abstract DatosLocalDao datosLocalDao();
    public abstract DatosLuminariaDao datosLuminariaDao();
    public abstract ConsumoElectricoDao consumoElectricoDao();
    public abstract PuntosAnalisisDao puntosAnalisisDao();
    public abstract VerificacionDao verificacionDao();
    public abstract ResultadosBasicosDao resultadosBasicosDao();
    public abstract IlumiCalculadaDao ilumiCalculadaDao();
    public abstract EncuestaResultsDao encuestaResultsDao();
    public abstract AEResultadosDao aeResultadosDao();


    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static FireflyDatabase getInstance(final Context context, final FireflyExecutors executors) {
        if (INSTANCE == null) {
            synchronized (FireflyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context.getApplicationContext(), executors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static FireflyDatabase buildDatabase(final Context appContext,
                                             final FireflyExecutors executors) {

        return Room.databaseBuilder(appContext, FireflyDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {

                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            FireflyDatabase database = FireflyDatabase.getInstance(appContext,executors);

                            /** Esta seccion es para prepopulated la
                             * tabla principal del catalogo de luminarias
                             */

                            CatalogoDao cDao= INSTANCE.catalogoDao();
                            PrepopulateDatabase.crearCatalogoTable(cDao);

                            /** Esta seccion es para prepopulated la tabla de Profundidad*/

                            ProfundidadLumDao pDao= INSTANCE.profundidadDao();
                            PrepopulateDatabase.crearProfundidadTable(pDao);

                            /** Esta seccion es para prepopulated la tabla de Largo*/

                            LargoDao lDao = INSTANCE.largoDao();
                            PrepopulateDatabase.crearLargoTable(lDao);

                            /** Esta seccion es para prepopulated la tabla de Ancho*/

                            AnchoDao aDao = INSTANCE.anchoDao();
                            PrepopulateDatabase.crearAnchoTable(aDao);

                            /** Esta seccion es para prepopulated la tabla de Alto*/

                            AltoDao alDao = INSTANCE.altoDao();
                            PrepopulateDatabase.crearAltoTable(alDao);

                            /** Esta seccion es para prepopulated la tabla de Diametro*/

                            DiametroDao dDao = INSTANCE.diametroDao();
                            PrepopulateDatabase.crearDiametroTable(dDao);

                            /** Esta seccion es para prepopulated la tabla de Balasto*/

                            BalastoDao bDao = INSTANCE.balastoDao();
                            PrepopulateDatabase.crearBalastoTable(bDao);


                            /** Esta seccion es para prepopulated la tabla de intensidades para la fotometria */

                            FotometriaDao fDao = INSTANCE.fotometriaDao();
                            PrepopulateDatabase.crearFotometria(fDao);


                            database.setDatabaseCreated();
                        });
                    }


                })
                .build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }


    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
