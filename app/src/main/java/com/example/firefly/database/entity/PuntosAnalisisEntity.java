package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "puntos_analisis",foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoPA", onDelete = CASCADE))
public class PuntosAnalisisEntity {

    @PrimaryKey (autoGenerate = true)
    public long idPuntosAnalisis;

    public long id_proyectoPA;

    public double coordenadaX;

    public double coordenadaY;

    public PuntosAnalisisEntity(long id_proyectoPA, double coordenadaX, double coordenadaY) {
        this.id_proyectoPA = id_proyectoPA;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }
}
