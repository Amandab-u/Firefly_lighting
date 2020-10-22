package com.example.firefly.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "proyectos")
public class ProyectosEntity {

    @PrimaryKey
    public int id_proyecto;

    @ColumnInfo(name = "nombre_proyectos")
    public String nombreProyecto;


    public Boolean consumoElectrico;

    public Boolean encuesta;

    public Boolean verificacion;

    public ProyectosEntity( int id, String nombre, Boolean consumo, Boolean encu, Boolean verif){
        this.id_proyecto = id;
        this.nombreProyecto=nombre;
        this.consumoElectrico=consumo;
        this.encuesta=encu;
        this.verificacion=verif;
    }

    public ProyectosEntity (){}

    public long getId_proyecto() {
        return id_proyecto;
    }

    public String getNombreProyecto() {
        return this.nombreProyecto;
    }

    public Boolean getConsumoElectrico() {
        return this.consumoElectrico;
    }

    public Boolean getEncuesta() {
        return this.encuesta;
    }

    public Boolean getVerificacion() {
        return this.verificacion;
    }
}
