package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.VerificacionEntity;

import java.util.List;

@Dao
public interface VerificacionDao {

    @Insert
    void insertIluminMedida(List<VerificacionEntity> ilumMedidas);
}
