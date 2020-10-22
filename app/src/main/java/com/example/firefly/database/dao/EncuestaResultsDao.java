package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.EncuestaResultsEntity;

@Dao
public interface EncuestaResultsDao {

    @Insert
    void insertResultEncuesta(EncuestaResultsEntity results);
}
