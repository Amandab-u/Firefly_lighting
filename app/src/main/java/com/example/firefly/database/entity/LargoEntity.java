package com.example.firefly.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "largo_luminaria")
public class LargoEntity {


    @PrimaryKey(autoGenerate = true)
    public long id_largo;

    @ColumnInfo(name = "largo_lum")
    public double largo;

    public long idLuminaria;

    public LargoEntity (long id, double largo, long idLum){
        this.id_largo = id;
        this.largo = largo;
        this.idLuminaria = idLum;

    }
    public LargoEntity (double largo, long idLum){
        this.largo = largo;
        this.idLuminaria = idLum;

    }
    public LargoEntity (){}

    public double getLargo() {
        return this.largo;
    }
}
