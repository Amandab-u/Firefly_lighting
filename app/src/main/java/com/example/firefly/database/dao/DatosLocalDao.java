package com.example.firefly.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

@Dao
public interface DatosLocalDao {

    @Query("select * from datos_local where id_proyectoL=:id")
    LiveData<DatosLocalEntity> getDatosLocales(int id);

    @Query("select count(id_local) from datos_local")
    LiveData<Integer> getSizeDatosLocal();

    @Insert
    void insertDatosLocal(DatosLocalEntity local);
}
