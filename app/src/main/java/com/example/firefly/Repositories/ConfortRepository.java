package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.EncuestaResultsEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class ConfortRepository {

    private static ConfortRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;

    private MediatorLiveData<List<ProyectoCompleto>> mObservableProyectos;



    private ConfortRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
    }

    public static ConfortRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (ConfortRepository.class) {
                if (sInstance == null) {
                    sInstance = new ConfortRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public LiveData<ProyectoCompleto> getProyectoDatos(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }

    public void insertResultEncuesta(EncuestaResultsEntity results){
        mExecutors.diskIO().execute(() -> mDatabase.encuestaResultsDao().insertResultEncuesta(results));
    }


}
