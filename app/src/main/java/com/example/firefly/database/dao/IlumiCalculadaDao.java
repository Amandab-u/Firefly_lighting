package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.IlumiCalculadaEntity;

import java.util.List;

@Dao
public interface IlumiCalculadaDao {

    @Insert
    void insertIluminanciaCalculadas(List<IlumiCalculadaEntity> iCalculadas);
}
