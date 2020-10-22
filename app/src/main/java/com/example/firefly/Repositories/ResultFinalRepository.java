package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class ResultFinalRepository {

    private static ResultFinalRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;

    private MediatorLiveData<List<ProyectoCompleto>> mObservableProyectos;



    private ResultFinalRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
    }

    public static ResultFinalRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (ResultFinalRepository.class) {
                if (sInstance == null) {
                    sInstance = new ResultFinalRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public LiveData<ProyectoCompleto> getEverything(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }
}
