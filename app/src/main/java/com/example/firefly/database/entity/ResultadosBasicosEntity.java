package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "resultados_basicos", foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoRB", onDelete = CASCADE))
public class ResultadosBasicosEntity {

    @PrimaryKey (autoGenerate = true)
    public long idResultadosB;

    public long id_proyectoRB;

    public double iluminanciamedia;

    public double iluminanciaMax;

    public double iluminanciaMin;

    public double uniformidad;

    public ResultadosBasicosEntity(long id_proyectoRB, double iluminanciamedia, double iluminanciaMax, double iluminanciaMin, double uniformidad) {
        this.id_proyectoRB = id_proyectoRB;
        this.iluminanciamedia = iluminanciamedia;
        this.iluminanciaMax = iluminanciaMax;
        this.iluminanciaMin = iluminanciaMin;
        this.uniformidad = uniformidad;
    }

    public double getIluminanciamedia() {
        return iluminanciamedia;
    }

    public double getIluminanciaMax() {
        return iluminanciaMax;
    }

    public double getIluminanciaMin() {
        return iluminanciaMin;
    }

    public double getUniformidad() {
        return uniformidad;
    }
}
