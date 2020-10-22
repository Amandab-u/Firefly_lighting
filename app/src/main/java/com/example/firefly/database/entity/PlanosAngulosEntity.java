package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planos_angulo")
public class PlanosAngulosEntity {

    @PrimaryKey
    public int id_planos_angulos;

    public String plano;

    public PlanosAngulosEntity(){}

    public PlanosAngulosEntity(int id_planos, String plano) {
        this.id_planos_angulos = id_planos;
        this.plano = plano;
    }

    public int getId_planos_angulos() {
        return id_planos_angulos;
    }

    public String getPlano() {
        return plano;
    }

}
