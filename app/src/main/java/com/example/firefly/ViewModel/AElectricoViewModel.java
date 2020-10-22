package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.AElectricoRepository;
import com.example.firefly.database.entity.AEResultadosEntity;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class AElectricoViewModel extends AndroidViewModel {

    AElectricoRepository mRepository;


    public AElectricoViewModel(@NonNull Application application, AElectricoRepository repository) {
        super(application);
        mRepository = repository;


    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mRepository.getProyectoCompleto(id);
    }

    public void insertConsumoElectrico(ConsumoElectricoEntity analisisElectricoDatos){
        mRepository.insertConsumoElectrico(analisisElectricoDatos);
    }

    public void insertResults(AEResultadosEntity results){
        mRepository.insertResultadosAE(results);
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final AElectricoRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getAElectricoRepository();

        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AElectricoViewModel(mApplication, mRepository);
        }
    }
}
