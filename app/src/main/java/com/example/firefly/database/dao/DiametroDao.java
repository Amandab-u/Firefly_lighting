package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.DiametroEntity;

@Dao
public interface DiametroDao {

    @Insert
    void insertDiametro (DiametroEntity diametro);

}
