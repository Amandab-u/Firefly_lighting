package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.VerificacionRepository;
import com.example.firefly.database.entity.VerificacionEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class VerificacionViewModel extends AndroidViewModel {
    VerificacionRepository mRepository;


    public VerificacionViewModel(@NonNull Application application, VerificacionRepository repository) {
        super(application);
        mRepository = repository;


    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mRepository.getProyectoCompleto(id);
    }

    public void insertIluminaMedida(List<VerificacionEntity> medida){
        mRepository.insertIluminaMedida(medida);
    }


    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final VerificacionRepository mRepository;




        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getVerificacionRepository();

        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new VerificacionViewModel(mApplication, mRepository);
        }
    }
}
