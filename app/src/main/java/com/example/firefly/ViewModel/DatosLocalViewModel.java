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

public class DatosLocalViewModel extends AndroidViewModel {



    private DatosLocalRepository mRepository;





    public DatosLocalViewModel(@NonNull Application application, DatosLocalRepository repository) {
        super(application);

        mRepository=repository;


    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */

    public void insertDatosLocal (DatosLocalEntity datosLocal){
        mRepository.insertDatosLocal(datosLocal);
    }

    public LiveData<Integer> getDatosLocalSize(){
       return mRepository.getDatosLocalSize();
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

        private final DatosLocalRepository mRepository;


        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getDatosLocalRepository();
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DatosLocalViewModel(mApplication, mRepository);
        }
    }

}
