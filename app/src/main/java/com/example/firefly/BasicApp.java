package com.example.firefly;

import android.app.Application;

import com.example.firefly.Repositories.AElectricoRepository;
import com.example.firefly.Repositories.CatalogoRepository;
import com.example.firefly.Repositories.ConfortRepository;
import com.example.firefly.Repositories.DatosLocalRepository;
import com.example.firefly.Repositories.ProyectoRepository;
import com.example.firefly.Repositories.ResultFinalRepository;
import com.example.firefly.Repositories.ResultadosRepository;
import com.example.firefly.Repositories.VerificacionRepository;
import com.example.firefly.database.FireflyDatabase;
import com.example.firefly.ui.DatosLocal;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {

    private FireflyExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new FireflyExecutors();
    }

    public FireflyDatabase getDatabase() {
        return FireflyDatabase.getInstance(this, mAppExecutors);
    }

    public CatalogoRepository getCatalogoRepository() {
        return CatalogoRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public ProyectoRepository getProyectoRepository(){
        return ProyectoRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public DatosLocalRepository getDatosLocalRepository(){
        return DatosLocalRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public ResultadosRepository getResultadosRepository(){
        return ResultadosRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public AElectricoRepository getAElectricoRepository(){
        return AElectricoRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public VerificacionRepository getVerificacionRepository(){
        return VerificacionRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public ConfortRepository getConfortRepository(){
        return ConfortRepository.getInstance(getDatabase(),mAppExecutors);
    }

    public ResultFinalRepository getResultFinalRepository(){
        return ResultFinalRepository.getInstance(getDatabase(),mAppExecutors);
    }
}
