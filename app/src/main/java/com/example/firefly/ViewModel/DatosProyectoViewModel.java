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

public class DatosProyectoViewModel extends AndroidViewModel{

    private ProyectoRepository mRepository;

    private long idProyecto;

    public DatosProyectoViewModel(@NonNull Application application, ProyectoRepository repository) {
        super(application);
        mRepository = repository;

    }

//    public LiveData<ProyectosEntity> getmProyectoObservable() {
//        return mProyectoObservable;
//    }

    public void insertProyecto(ProyectosEntity proyecto){
        mRepository.insertProyecto(proyecto);

    }



    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class DatosProyectoViewModelFactory  implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;
        private final ProyectoRepository mRepository;

        public DatosProyectoViewModelFactory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getProyectoRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

            return (T) new DatosProyectoViewModel(mApplication, mRepository);

        }
    }
}
