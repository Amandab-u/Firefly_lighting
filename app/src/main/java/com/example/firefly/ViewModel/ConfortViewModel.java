package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.AElectricoRepository;
import com.example.firefly.Repositories.ConfortRepository;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.EncuestaResultsEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

public class ConfortViewModel extends AndroidViewModel {

    ConfortRepository mRepository;


    public ConfortViewModel(@NonNull Application application, ConfortRepository repository) {
        super(application);
        mRepository = repository;


    }




    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mRepository.getProyectoDatos(id);
    }

    public void insertResultEncuesta(EncuestaResultsEntity results){
        mRepository.insertResultEncuesta(results);
    }


    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final ConfortRepository mRepository;




        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getConfortRepository();

        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ConfortViewModel(mApplication, mRepository);
        }
    }
}