package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "verificacion",foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoV", onDelete = CASCADE))
public class VerificacionEntity {

    @PrimaryKey(autoGenerate = true)
    public long idVerificacion;

    public long id_proyectoV;

    public double iluminanciaMedida;

    public VerificacionEntity(long id_proyectoV, double iluminanciaMedida) {
        this.id_proyectoV = id_proyectoV;
        this.iluminanciaMedida = iluminanciaMedida;
    }

    public double getIluminanciaMedida() {
        return iluminanciaMedida;
    }
}
