package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;

import com.example.firefly.database.entity.ConsumoElectricoEntity;

@Dao
public interface ConsumoElectricoDao {

    @Insert
    void insertConsumoElectricoDatos(ConsumoElectricoEntity consumo);
}
