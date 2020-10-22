package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "datos_luminaria", foreignKeys = @ForeignKey(
        entity = ProyectosEntity.class,parentColumns = "id_proyecto",
        childColumns = "id_proyecto", onDelete = CASCADE))
public class DatosLuminariaEntity {

    @PrimaryKey(autoGenerate = true)
    public long id_luminaria;

    public long id_proyecto;

    public long idLuminariaSelect;

    public String nombreLuminariaSelec;

    public double potencia;

    public double coordenadaX;

    public double coordenadaY;

    public double coordenadaZ;

    public DatosLuminariaEntity(){}

    public DatosLuminariaEntity(  long id_proyecto, long idLuminariaSelect, double coordenadaX,
                                  double coordenadaY, double coordenadaZ, String nombre, double potenc) {

        this.id_proyecto = id_proyecto;
        this.idLuminariaSelect = idLuminariaSelect;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.coordenadaZ = coordenadaZ;
        this.nombreLuminariaSelec = nombre;
        this.potencia = potenc;
    }

//    public DatosLuminariaEntity(long id_luminaria, long id_proyecto) {
//        this.id_luminaria = id_luminaria;
//        this.id_proyecto = id_proyecto;
//    }

    public long getId_luminaria() {
        return this.id_luminaria;
    }



    public long getIdLuminariaSelect() {
        return this.idLuminariaSelect;
    }

    public double getCoordenadaX() {
        return this.coordenadaX;
    }

    public double getCoordenadaY() {
        return this.coordenadaY;
    }

    public double getCoordenadaZ() {
        return this.coordenadaZ;
    }

    public String getNombreLuminariaSelec() {
        return nombreLuminariaSelec;
    }

    public double getPotencia() {
        return potencia;
    }
}
