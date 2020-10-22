package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.AltoEntity;

import java.util.List;

@Dao
public interface AltoDao {

    @Insert
    void insertAllAltos(List<AltoEntity> altos);
}
