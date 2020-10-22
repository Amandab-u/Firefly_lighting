package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.DatosLocalRepository;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;
import java.util.Observable;

public class DatosLuminViewModel extends AndroidViewModel {

    private final LiveData<ProyectoCompleto> observableNumLumin;

    DatosLocalRepository mRepository;

    public DatosLuminViewModel(@NonNull Application application, DatosLocalRepository repository, long id) {
        super(application);
        mRepository = repository;
        observableNumLumin = mRepository.getNumLuminarias(id);

    }

    public LiveData<ProyectoCompleto> getNumLuminarias(){
        return observableNumLumin;
    }

    public void insertDatosLuminarias(List<DatosLuminariaEntity> luminarias){
        mRepository.insertDatosLumin(luminarias);
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final DatosLocalRepository mRepository;

        private final long idProyecto;


        public Factory(@NonNull Application application, long id) {
            mApplication = application;
            mRepository = ((BasicApp) application).getDatosLocalRepository();
            idProyecto = id;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DatosLuminViewModel(mApplication, mRepository,idProyecto);
        }
    }
}
