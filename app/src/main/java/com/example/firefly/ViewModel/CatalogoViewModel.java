package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.CatalogoRepository;
import com.example.firefly.database.relations.CatalogoCompleto;

import java.util.List;

public class CatalogoViewModel extends AndroidViewModel {

    private final LiveData<List<CatalogoCompleto>> mObservableLuminaria;





    public CatalogoViewModel(@NonNull Application application, CatalogoRepository repository) {
        super(application);


        mObservableLuminaria = repository.getCatalogoCompleto();

    }




    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */



    public  LiveData<List<CatalogoCompleto>> getCatalogoCompleto() {
        return mObservableLuminaria;
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

        private final CatalogoRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getCatalogoRepository();
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CatalogoViewModel(mApplication, mRepository);
        }
    }

}
