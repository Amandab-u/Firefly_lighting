package com.example.firefly.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.firefly.database.entity.ProyectosEntity;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

@Dao
public interface ProyectosDao {

    @Query("SELECT * FROM PROYECTOS")
    LiveData<List<ProyectosEntity>> getAllProjects();

    @Query("select * from proyectos where id_proyecto =:id")
    LiveData<ProyectosEntity> getProyecto(int id);

    @Transaction
    @Query("select * from proyectos where id_proyecto =:id")
    LiveData<ProyectoCompleto> getProyectoCompleto(long id);

    @Insert
    void insertProyecto(ProyectosEntity proyecto);

    @Query("delete from proyectos; ")
    void deleteProyectos();




    @Transaction
    @Query("select * from proyectos")
    LiveData<List<ProyectoCompleto>> getProyectosCompleto();
}
