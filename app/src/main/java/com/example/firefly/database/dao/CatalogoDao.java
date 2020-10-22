package com.example.firefly.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.firefly.database.entity.CatalogoEntity;
import com.example.firefly.database.relations.CatalogoCompleto;

import java.util.List;

@Dao
public interface CatalogoDao {

    /** Este metodo es para insertar una luminaria en la tabla de catalogo
     *
     * @param luminariasCatalogo
     */
    @Insert
    void insertLuminariasCatalogo(CatalogoEntity luminariasCatalogo);


    /** Este metodo es para obtener la lista de luminarios con los datos de todas las tablas relacionadas
     *
     * @return una lista de luminarias del catalogo
     */
    @Transaction
    @Query("select * from catalogo_luminarias ")
    LiveData<List<CatalogoCompleto>> getAllCatalogo();


    /** Este metodo es para obtener los datos de una luminaria en especifico del catalogo
     *
     * @param id se obtiene del catalogo la luminaria seleccionada para obetener sus datos
     * @return los datos de una luminaria
     */
    @Transaction
    @Query("select * from catalogo_luminarias where id_catalogo=:id")
    LiveData<CatalogoCompleto> getLuminariaDetallada(int id);


    @Transaction
    @Query("select * from catalogo_luminarias")
    List<CatalogoCompleto> getCatalogoPrueba();

}
