package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.firefly.POJOs.Coordenadas;
import com.example.firefly.database.entity.PuntosAnalisisEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PuntosAnalisisDao {

    @Insert
    void insertPuntosAnalisis (ArrayList<PuntosAnalisisEntity> puntos);


}
