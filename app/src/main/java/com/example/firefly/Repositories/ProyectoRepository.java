package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.ProyectosEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class ProyectoRepository {

    private static ProyectoRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;

    private MediatorLiveData<List<ProyectoCompleto>> mObservableProyectos;



    private long idProyecto;

    private long[] IDs;

    private ProyectoRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
        mObservableProyectos = new MediatorLiveData<>();

        mObservableProyectos.addSource(mDatabase.proyectosDao().getProyectosCompleto(),
                proyectoEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProyectos.postValue(proyectoEntities);
                    }
                });
    }

    public static ProyectoRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (ProyectoRepository.class) {
                if (sInstance == null) {
                    sInstance = new ProyectoRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<ProyectoCompleto>> getAllProyectos(){
        return mObservableProyectos;
    }

    public void insertProyecto(ProyectosEntity nuevo){
        mExecutors.diskIO().execute(() -> mDatabase.proyectosDao().insertProyecto(nuevo));
    }

    public void deleteProyecto(){
        mExecutors.diskIO().execute(() -> mDatabase.proyectosDao().deleteProyectos());
    }



}
