package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diametro_luminaria")
public class DiametroEntity {

    @PrimaryKey(autoGenerate = true)
    public long id_diametro;

    public double diametro;

    public long idLuminariaD;

    public DiametroEntity (){}

    public DiametroEntity(long id,double diametro, long idLum){
        this.id_diametro = id;
        this.diametro = diametro;
        this.idLuminariaD = idLum;
    }
    public DiametroEntity(double diametro, long idLum){
        this.diametro = diametro;
        this.idLuminariaD = idLum;
    }

    public double getDiametro() {
        return this.diametro;
    }
}
