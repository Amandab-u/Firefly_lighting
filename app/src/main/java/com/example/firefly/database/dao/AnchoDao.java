package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.AnchoEntity;
import com.example.firefly.database.entity.LargoEntity;

import java.util.List;

@Dao
public interface AnchoDao {
    @Insert
    void insertAncho (AnchoEntity ancho);

    @Insert
    void insertAllAncho(List<AnchoEntity> anchos);

}
