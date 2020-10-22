package com.example.firefly.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.firefly.database.entity.FotometriaEntity;

import java.util.List;

@Dao
public interface FotometriaDao {

    @Insert
    void insertIntensidad(List<FotometriaEntity> intensidad);

    @Query("select * from fotometria where id_luminaria =:id and id_plano_angulo=:idplano")
    LiveData<List<FotometriaEntity>> getFotometria(long id, int idplano);
}
