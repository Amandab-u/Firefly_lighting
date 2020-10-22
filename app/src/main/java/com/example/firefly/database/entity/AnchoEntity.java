package com.example.firefly.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ancho_luminaria")
public class AnchoEntity {

    @PrimaryKey(autoGenerate = true)
    public long id_ancho;

    public double ancho;

    public long idLuminariaA;

    public AnchoEntity(){}

    public AnchoEntity(long id, double ancho, long idLum){
        this.id_ancho=id;
        this.ancho=ancho;
        this.idLuminariaA=idLum;
    }
    public AnchoEntity(double ancho, long idLum){
        this.ancho=ancho;
        this.idLuminariaA=idLum;
    }

    public double getAncho() {
        return this.ancho;
    }
}
