package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.AEResultadosEntity;
import com.example.firefly.database.entity.CatalogoEntity;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class AElectricoRepository {

    private static AElectricoRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;

    private MediatorLiveData<List<ProyectoCompleto>> mObservableProyectos;

    private CatalogoCompleto catalogo;

    private AElectricoRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
    }

    public static AElectricoRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (AElectricoRepository.class) {
                if (sInstance == null) {
                    sInstance = new AElectricoRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }

    public void insertConsumoElectrico(ConsumoElectricoEntity analisisElectricoDatos){
        mExecutors.diskIO().execute(() -> mDatabase.consumoElectricoDao().insertConsumoElectricoDatos(analisisElectricoDatos));
    }

    public void insertResultadosAE(AEResultadosEntity results){
        mExecutors.diskIO().execute(() -> mDatabase.aeResultadosDao().insertResults(results));
    }


}
