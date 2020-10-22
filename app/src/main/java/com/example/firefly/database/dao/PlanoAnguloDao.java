package com.example.firefly.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.firefly.database.entity.PlanosAngulosEntity;

@Dao
public interface PlanoAnguloDao {


    @Insert
    void insertPlanoAngulo(PlanosAngulosEntity planoAndulo);

//    @Transaction
//    @Query("select * from fotometria")
//    List<FotometriaCompleta> getAllIntensidades();
}
