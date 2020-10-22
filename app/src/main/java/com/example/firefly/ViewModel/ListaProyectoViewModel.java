package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.ProyectoRepository;
import com.example.firefly.database.entity.ProyectosEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class ListaProyectoViewModel extends AndroidViewModel {

    private final LiveData<List<ProyectoCompleto>> mObservableProyecto;

    private ProyectoRepository mRepository;



    public ListaProyectoViewModel(@NonNull Application application, ProyectoRepository repository) {
        super(application);

        mRepository=repository;
        mObservableProyecto = mRepository.getAllProyectos();


    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public  LiveData<List<ProyectoCompleto>> getAllProyectos() {
        return mObservableProyecto;
    }


    public void deleteAllProyectos(){
        mRepository.deleteProyecto();
    }






    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final ProyectoRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getProyectoRepository();
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ListaProyectoViewModel(mApplication, mRepository);
        }
    }


}
