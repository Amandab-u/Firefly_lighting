package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "consumo_electrico",foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoC", onDelete = CASCADE))
public class ConsumoElectricoEntity {

    @PrimaryKey(autoGenerate = true)
    public long idConsumo;

    public long id_proyectoC;

    public double voltaje_alimentacion;

    public double corriente_alimenatcion;

    public double factor_potencia;

    public double horas_semanal;

    public ConsumoElectricoEntity(long id_proyectoC,
                                  double voltaje_alimentacion, double corriente_alimenatcion,
                                  double factor_potencia, double horas_semanal) {
        this.id_proyectoC = id_proyectoC;
        this.voltaje_alimentacion = voltaje_alimentacion;
        this.corriente_alimenatcion = corriente_alimenatcion;
        this.factor_potencia = factor_potencia;
        this.horas_semanal = horas_semanal;
    }

    public double getVoltaje_alimentacion() {
        return voltaje_alimentacion;
    }

    public double getCorriente_alimenatcion() {
        return corriente_alimenatcion;
    }

    public double getFactor_potencia() {
        return factor_potencia;
    }

    public double getHoras_semanal() {
        return horas_semanal;
    }
}
