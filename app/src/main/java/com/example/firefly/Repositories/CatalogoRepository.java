package com.example.firefly.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.firefly.FireflyExecutors;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.ui.Catalogo;

import java.util.List;

public class CatalogoRepository {

    private static CatalogoRepository sInstance;

    private FireflyDatabase mDatabase;

    private FireflyExecutors mExecutors;

    private MediatorLiveData<List<CatalogoCompleto>> mObservableLuminarias;


    private CatalogoRepository(final FireflyDatabase database,FireflyExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
        mObservableLuminarias = new MediatorLiveData<>();


        mObservableLuminarias.addSource(mDatabase.catalogoDao().getAllCatalogo(),
                catalogoEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableLuminarias.postValue(catalogoEntities);
                    }
                });
    }

    public static CatalogoRepository getInstance(final FireflyDatabase database, FireflyExecutors exe) {
        if (sInstance == null) {
            synchronized (CatalogoRepository.class) {
                if (sInstance == null) {
                    sInstance = new CatalogoRepository(database,exe);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of luminarias from CatalogoEntity and get notified when the data changes.
     */
    public LiveData<List<CatalogoCompleto>> getCatalogoCompleto() {
        return mObservableLuminarias;
    }


    public LiveData<CatalogoCompleto> getLuminariaDetallada(int id){
       return mDatabase.catalogoDao().getLuminariaDetallada(id);
    }

    public void updatLuminSeleccionada(int idlumin, long idProy, String name, double potencia){
        mExecutors.diskIO().execute(() -> mDatabase.datosLuminariaDao().updateLuminSelec(idlumin,idProy,name,potencia));
    }


}
