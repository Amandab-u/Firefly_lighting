package com.example.firefly.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.BalastoEntity;

import java.util.List;

@Dao
public interface BalastoDao {

    @Insert
    void inserBalasto (BalastoEntity balasto);

    @Insert
    void insertAllBalasto(List<BalastoEntity> balastos);
}
