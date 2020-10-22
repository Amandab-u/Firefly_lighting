package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.firefly.database.entity.LargoEntity;

import java.util.List;

@Dao
public interface LargoDao {

    @Insert
    void insertLargo (LargoEntity largo);

    @Insert
    void insertAllLargo(List<LargoEntity> largos);

}
