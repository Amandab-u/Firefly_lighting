package com.example.firefly.database.dao;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.firefly.database.entity.DatosLuminariaEntity;

import java.util.List;

@Dao
public interface DatosLuminariaDao {

    @Insert
    void insertLuminariasProyecto(List<DatosLuminariaEntity> luminarias);

    @Query("update datos_luminaria set idLuminariaSelect =:id,nombreLuminariaSelec =:name, potencia =:pot where id_proyecto =:idp")
    void updateLuminSelec(int id,long idp, String name, double pot);

    @Query("select * from datos_luminaria where id_proyecto =:id")
    List<DatosLuminariaEntity> getLuminarias(long id);

}
