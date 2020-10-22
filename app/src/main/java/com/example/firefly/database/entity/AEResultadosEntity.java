package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (tableName = "analisis_electrico_resultados",foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoAEresult", onDelete = CASCADE))
public class AEResultadosEntity {

    @PrimaryKey (autoGenerate = true)
    public long idResult;

    public long id_proyectoAEresult;

    public double potenciaIdeal;

    public double potenciaReal;

    public AEResultadosEntity(long id_proyectoAEresult, double potenciaIdeal, double potenciaReal) {
        this.id_proyectoAEresult = id_proyectoAEresult;
        this.potenciaIdeal = potenciaIdeal;
        this.potenciaReal = potenciaReal;
    }

    public double getPotenciaIdeal() {
        return potenciaIdeal;
    }

    public double getPotenciaReal() {
        return potenciaReal;
    }
}
