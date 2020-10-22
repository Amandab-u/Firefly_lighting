package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "iluminancia_calculada", foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoIC", onDelete = CASCADE))
public class IlumiCalculadaEntity {

    @PrimaryKey (autoGenerate = true)
    public long idIluCalculada;

    public long id_proyectoIC;

    public double iluminanciaCalculada;

    public IlumiCalculadaEntity(long id_proyectoIC, double iluminanciaCalculada) {
        this.id_proyectoIC = id_proyectoIC;
        this.iluminanciaCalculada = iluminanciaCalculada;
    }

    public double getIluminanciaCalculada() {
        return iluminanciaCalculada;
    }
}
