package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.ResultadosBasicosEntity;

@Dao
public interface ResultadosBasicosDao {

    @Insert
    void insertResultadosBasicos(ResultadosBasicosEntity result);
}
