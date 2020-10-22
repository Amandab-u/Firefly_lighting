package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class DatosLocalRepository {
    private static DatosLocalRepository sInstance;

    private FireflyExecutors mExecutors;

    private FireflyDatabase mDatabase;
    LiveData<Integer> datosLocalSize;

    private MediatorLiveData<DatosLocalEntity> mObservableDatosLocal;




    private DatosLocalRepository(final FireflyDatabase database, FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
//        mObservableDatosLocal = new MediatorLiveData<>();
//
//        mObservableDatosLocal.addSource(mDatabase.datosLocalDao().getNumLuminarias(),
//                proyectoEntities -> {
//                    if (mDatabase.getDatabaseCreated().getValue() != null) {
//                        mObservableDatosLocal.postValue(proyectoEntities);
//                    }
//                });
    }

    public static DatosLocalRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (ProyectoRepository.class) {
                if (sInstance == null) {
                    sInstance = new DatosLocalRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    public void insertDatosLocal (DatosLocalEntity datosLocal){
        mExecutors.diskIO().execute(() ->  mDatabase.datosLocalDao().insertDatosLocal(datosLocal));
    }

    public LiveData<ProyectoCompleto> getNumLuminarias(long id){
        return mDatabase.proyectosDao().getProyectoCompleto(id);
    }

    public LiveData<Integer> getDatosLocalSize(){
        return mDatabase.datosLocalDao().getSizeDatosLocal();
    }
    public void insertDatosLumin(List<DatosLuminariaEntity> luminarias){
        mExecutors.diskIO().execute(() -> mDatabase.datosLuminariaDao().insertLuminariasProyecto(luminarias));
    }
}
