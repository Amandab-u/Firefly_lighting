package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.ResultadosBasicosEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.ArrayList;
import java.util.List;

public class ResultadosRepository {

    private static ResultadosRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;

    private MediatorLiveData<List<ProyectoCompleto>> mObservableProyectos;

    private List<DatosLuminariaEntity> mLuminaria;


    private ResultadosRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
//        mObservableProyectos = new MediatorLiveData<>();
//
//        mObservableProyectos.addSource(mDatabase.proyectosDao().getProyectosCompleto(),
//                proyectoEntities -> {
//                    if (mDatabase.getDatabaseCreated().getValue() != null) {
//                        mObservableProyectos.postValue(proyectoEntities);
//                    }
//                });
    }

    public static ResultadosRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (ProyectoRepository.class) {
                if (sInstance == null) {
                    sInstance = new ResultadosRepository(database,exe);
                }
            }
        }
        return sInstance;
    }


    public List<DatosLuminariaEntity> getDatosLuminarias(long idProyecto){
        mExecutors.diskIO().execute(() -> mLuminaria = mDatabase.datosLuminariaDao().getLuminarias(idProyecto));
        return mLuminaria;
    }

    public LiveData<List<FotometriaEntity>> getFotometria(long id, int idplano){
        return mDatabase.fotometriaDao().getFotometria(id,idplano);
    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }

    public LiveData<CatalogoCompleto> getCatalogoCompleto(int id){
        return mDatabase.catalogoDao().getLuminariaDetallada(id);
    }

    public void insertPuntosAnalisis(ArrayList<PuntosAnalisisEntity> puntos){
        mExecutors.diskIO().execute(() -> mDatabase.puntosAnalisisDao().insertPuntosAnalisis(puntos));
    }

    public void insertResultadosBasicos(ResultadosBasicosEntity result){
        mExecutors.diskIO().execute(() -> mDatabase.resultadosBasicosDao().insertResultadosBasicos(result));
    }

    public void insertIlumiCalculadas(List<IlumiCalculadaEntity> calculadas){
        mExecutors.diskIO().execute(() -> mDatabase.ilumiCalculadaDao().insertIluminanciaCalculadas(calculadas));
    }
}
