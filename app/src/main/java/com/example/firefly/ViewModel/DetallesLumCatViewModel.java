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

public class DetallesLumCatViewModel extends AndroidViewModel {

    private final LiveData<CatalogoCompleto> mObservableLuminaria;


    CatalogoRepository mRepository;


    public DetallesLumCatViewModel(@NonNull Application mApplication, CatalogoRepository repository, int luminId) {
        super(mApplication);

        mRepository = repository;
        mObservableLuminaria = mRepository.getLuminariaDetallada(luminId);

    }




    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */



    public  LiveData<CatalogoCompleto> getLuminariaDetallada() {
        return mObservableLuminaria;
    }


    public void updateLuminariaSelec (int idLumin,long idProye, String name,double potencia){
        mRepository.updatLuminSeleccionada(idLumin, idProye,name, potencia);
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class DetallesViewModelFactory  implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;
        private final CatalogoRepository mRepository;

        private final int idLuminaria;

        public DetallesViewModelFactory(@NonNull Application application, int id) {
            mApplication = application;
            mRepository = ((BasicApp) application).getCatalogoRepository();
            idLuminaria = id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

                return (T) new DetallesLumCatViewModel(mApplication, mRepository, idLuminaria);

        }
    }

}
