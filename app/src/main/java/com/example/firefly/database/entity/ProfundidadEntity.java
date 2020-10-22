package com.example.firefly.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "profundidad_luminarias")
public class ProfundidadEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_profundidad")
    public long idProfundidad;

    public double profundidad;

    public long idLuminariaP;

    public ProfundidadEntity(long id, double prof, long idLum) {
        this.idProfundidad = id;
        this.profundidad= prof;
        this.idLuminariaP = idLum;
    }
    public ProfundidadEntity(double prof, long idLum) {
        this.profundidad= prof;
        this.idLuminariaP = idLum;
    }

    public ProfundidadEntity(){}

    public double getProfundidad() {
        return this.profundidad;
    }

    public long getIdLuminaria() {
        return this.idLuminariaP;
    }

    public long getIdProfundidad() {
        return this.idProfundidad;
    }
}


