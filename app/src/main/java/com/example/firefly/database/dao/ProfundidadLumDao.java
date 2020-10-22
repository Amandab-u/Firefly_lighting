package com.example.firefly.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.firefly.database.entity.ProfundidadEntity;

import java.util.List;

@Dao
public interface ProfundidadLumDao {




    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProfundidad (ProfundidadEntity profundidad);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProfundidad(List<ProfundidadEntity> profundidades);
}
