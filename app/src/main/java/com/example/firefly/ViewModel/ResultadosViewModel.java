package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.ResultadosRepository;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.ResultadosBasicosEntity;
import com.example.firefly.database.relations.CatalogoCompleto;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.ArrayList;
import java.util.List;

public class ResultadosViewModel extends AndroidViewModel {



    ResultadosRepository  mRepository;


    public ResultadosViewModel(@NonNull Application application, ResultadosRepository repository) {
        super(application);
        mRepository = repository;


    }



    public LiveData<List<FotometriaEntity>> getFotometria(long id, int idplano){
        return mRepository.getFotometria(id,idplano);
    }

    public LiveData<ProyectoCompleto> getProyectoCompleto(long id){
        return mRepository.getProyectoCompleto(id);
    }

    public LiveData<CatalogoCompleto> getCatalogo(int id){
        return mRepository.getCatalogoCompleto(id);
    }

    public void insertPuntosAnalisis(ArrayList<PuntosAnalisisEntity> puntos){
        mRepository.insertPuntosAnalisis(puntos);
    }

    public void insertResultadosBasicos(ResultadosBasicosEntity result){
        mRepository.insertResultadosBasicos(result);
    }

    public void insertIlumiCalculadas(List<IlumiCalculadaEntity> calculadas){
        mRepository.insertIlumiCalculadas(calculadas);
    }


    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final ResultadosRepository mRepository;




        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getResultadosRepository();

        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ResultadosViewModel(mApplication, mRepository);
        }
    }
}
