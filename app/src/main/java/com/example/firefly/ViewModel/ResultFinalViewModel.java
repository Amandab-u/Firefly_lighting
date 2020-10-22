package com.example.firefly.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.firefly.BasicApp;
import com.example.firefly.Repositories.ResultFinalRepository;
import com.example.firefly.database.relations.ProyectoCompleto;

public class ResultFinalViewModel extends AndroidViewModel {

    ResultFinalRepository mRepository;


    public ResultFinalViewModel(@NonNull Application application, ResultFinalRepository repository) {
        super(application);
        mRepository = repository;


    }

    public LiveData<ProyectoCompleto> getEverything(long id){
        return mRepository.getEverything(id);
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final ResultFinalRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getResultFinalRepository();

        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ResultFinalViewModel(mApplication, mRepository);
        }
    }
}
