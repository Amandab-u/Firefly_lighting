package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.AEResultadosEntity;

@Dao
public interface AEResultadosDao {

    @Insert
    void insertResults(AEResultadosEntity result);
}
