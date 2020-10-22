package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fotometria")
public class FotometriaEntity {

    @PrimaryKey(autoGenerate = true)
    public int id_fotometria;

    public int id_plano_angulo;

    public double angulo;

    public double intensidad;

    public int id_luminaria;

    public FotometriaEntity( int id_plano_angulo, double angulo, double intensidad, int id_luminaria) {
        this.id_plano_angulo = id_plano_angulo;
        this.angulo = angulo;
        this.intensidad = intensidad;
        this.id_luminaria = id_luminaria;
    }


    public int getId_fotometria() {
        return id_fotometria;
    }

    public int getId_plano_angulo() {
        return id_plano_angulo;
    }

    public double getAngulo() {
        return angulo;
    }

    public double getIntensidad() {
        return intensidad;
    }

    public int getId_luminaria() {
        return id_luminaria;
    }
}
