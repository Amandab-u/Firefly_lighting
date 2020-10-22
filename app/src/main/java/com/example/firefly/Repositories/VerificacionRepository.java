package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.VerificacionEntity;
import com.example.firefly.database.relations.ProyectoCompleto;
import com.example.firefly.ui.Verificacion;

import java.util.ArrayList;
import java.util.List;

public class VerificacionRepository {

    private static VerificacionRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;


    private VerificacionRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
    }

    public static VerificacionRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (VerificacionRepository.class) {
                if (sInstance == null) {
                    sInstance = new VerificacionRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }

    public void insertIluminaMedida(List<VerificacionEntity> medidas){
        mExecutors.diskIO().execute(() -> mDatabase.verificacionDao().insertIluminMedida(medidas));
    }


}
