package com.example.firefly.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "datos_local",
        foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
                parentColumns = "id_proyecto",childColumns = "id_proyectoL", onDelete = CASCADE))
public class DatosLocalEntity {

    @PrimaryKey (autoGenerate = true)
    public long id_local;

    public long id_proyectoL;

    public long iluminanciaNorma;

    public double largoLocal;

    public double altoLocal;

    public double anchoLocal;

    public double altoTrabajo;

    public long id_factor_mantenimiento;

    public long id_rPared;

    public long id_rTecho;

    public long id_rPiso;

    public long id_rTrabajo;

    public double superficiePared;

    public double superficieTecho;

    public double superficiePiso;

    public double superficieTrabajo;

    @ColumnInfo(name = "numero_luminarias")
    public int numeroLuminarias;





    public DatosLocalEntity(long id_local, long id_proyectoL, long iluminanciaNorma,
                            double largoLocal, double altoLocal, double anchoLocal,
                            double altoTrabajo, long id_factor_mantenimiento, long id_rPared,
                            long id_rTecho, long id_rPiso, long id_rTrabajo, double superficiePared,
                            double superficieTecho, double superficiePiso, double superficieTrabajo,
                            int numeroLuminarias) {
        this.id_local = id_local;
        this.id_proyectoL = id_proyectoL;
        this.iluminanciaNorma = iluminanciaNorma;
        this.largoLocal = largoLocal;
        this.altoLocal = altoLocal;
        this.anchoLocal = anchoLocal;
        this.altoTrabajo = altoTrabajo;
        this.id_factor_mantenimiento = id_factor_mantenimiento;
        this.id_rPared = id_rPared;
        this.id_rTecho = id_rTecho;
        this.id_rPiso = id_rPiso;
        this.id_rTrabajo = id_rTrabajo;
        this.superficiePared = superficiePared;
        this.superficieTecho = superficieTecho;
        this.superficiePiso = superficiePiso;
        this.superficieTrabajo = superficieTrabajo;
        this.numeroLuminarias = numeroLuminarias;
    }

    public long getId_proyectoL() {
        return id_proyectoL;
    }

    public long getIluminanciaNorma() {
        return iluminanciaNorma;
    }

    public double getLargoLocal() {
        return largoLocal;
    }

    public double getAltoLocal() {
        return altoLocal;
    }

    public double getAnchoLocal() {
        return anchoLocal;
    }

    public double getAltoTrabajo() {
        return altoTrabajo;
    }

    public long getId_factor_mantenimiento() {
        return id_factor_mantenimiento;
    }

    public long getId_rPared() {
        return id_rPared;
    }

    public long getId_rTecho() {
        return id_rTecho;
    }

    public long getId_rPiso() {
        return id_rPiso;
    }

    public long getId_rTrabajo() {
        return id_rTrabajo;
    }

    public double getSuperficiePared() {
        return superficiePared;
    }

    public double getSuperficieTecho() {
        return superficieTecho;
    }

    public double getSuperficiePiso() {
        return superficiePiso;
    }

    public double getSuperficieTrabajo() {
        return superficieTrabajo;
    }

    public int getNumeroLuminarias() {
        return numeroLuminarias;
    }

}
