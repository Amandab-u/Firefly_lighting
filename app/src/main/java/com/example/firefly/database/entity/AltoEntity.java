package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alto_luminaria")
public class AltoEntity {

    @PrimaryKey(autoGenerate = true)
    public long id_alto;

    public double alto;

    public long idLuminariaAl;

    public AltoEntity(){}

    public AltoEntity(long idA,double alto,long idL){
        this.id_alto = idA;
        this.alto = alto;
        this.idLuminariaAl = idL;
    }
    public AltoEntity(double alto,long idL){
        this.alto = alto;
        this.idLuminariaAl = idL;
    }

    public double getAlto() {
        return this.alto;
    }
}
