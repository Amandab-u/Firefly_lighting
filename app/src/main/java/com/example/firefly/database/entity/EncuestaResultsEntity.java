package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "encuesta_resultados", foreignKeys = @ForeignKey(entity = ProyectosEntity.class,
        parentColumns = "id_proyecto",childColumns = "id_proyectoE", onDelete = CASCADE))
public class EncuestaResultsEntity {

    @PrimaryKey(autoGenerate = true)
    public long encuestaId;

    public long id_proyectoE;

    public double percentPatologiaSi = 0;
    public double percentLeer = 0;
    public double percentManiObjSim = 0;
    public double percentManiObjPeq = 0;
    public double percentManiMaq = 0;
    public double percentUsarOrd = 0;
    public double percentDificultadMuy = 0;
    public double percentDificultadDif = 0;
    public double percentDificultadMedio = 0;
    public double percentDificultadFacil = 0;
    public double percentDificultadMuyF = 0;
    public double percentPosturaSi = 0;
    public double percentAnomaliaArdor = 0;
    public double percentAnomaliaDolor = 0;
    public double percentAnomaliaDisting = 0;
    public double percentAnomaliaNuca = 0;
    public double percentAnomaliaNone = 0;
    public double percentCambiarPSi = 0;
    public double percentPercibeMuyA = 0;
    public double percentPercibeAmplio = 0;
    public double percentPercibeAcorde = 0;
    public double percentPercibeEstrecho = 0;
    public double percentPercibeMuyE = 0;
    public double promedioEdad;

    public EncuestaResultsEntity(long id_proyectoE, double percentPatologiaSi, double percentLeer, double percentManiObjSim, double percentManiObjPeq,
                                 double percentManiMaq, double percentUsarOrd, double percentDificultadMuy, double percentDificultadDif,
                                 double percentDificultadMedio, double percentDificultadFacil, double percentDificultadMuyF, double percentPosturaSi,
                                 double percentAnomaliaArdor, double percentAnomaliaDolor, double percentAnomaliaDisting, double percentAnomaliaNuca,
                                 double percentAnomaliaNone, double percentCambiarPSi, double percentPercibeMuyA, double percentPercibeAmplio,
                                 double percentPercibeAcorde, double percentPercibeEstrecho, double percentPercibeMuyE, double promedioEdad) {
        this.id_proyectoE = id_proyectoE;
        this.percentPatologiaSi = percentPatologiaSi;
        this.percentLeer = percentLeer;
        this.percentManiObjSim = percentManiObjSim;
        this.percentManiObjPeq = percentManiObjPeq;
        this.percentManiMaq = percentManiMaq;
        this.percentUsarOrd = percentUsarOrd;
        this.percentDificultadMuy = percentDificultadMuy;
        this.percentDificultadDif = percentDificultadDif;
        this.percentDificultadMedio = percentDificultadMedio;
        this.percentDificultadFacil = percentDificultadFacil;
        this.percentDificultadMuyF = percentDificultadMuyF;
        this.percentPosturaSi = percentPosturaSi;
        this.percentAnomaliaArdor = percentAnomaliaArdor;
        this.percentAnomaliaDolor = percentAnomaliaDolor;
        this.percentAnomaliaDisting = percentAnomaliaDisting;
        this.percentAnomaliaNuca = percentAnomaliaNuca;
        this.percentAnomaliaNone = percentAnomaliaNone;
        this.percentCambiarPSi = percentCambiarPSi;
        this.percentPercibeMuyA = percentPercibeMuyA;
        this.percentPercibeAmplio = percentPercibeAmplio;
        this.percentPercibeAcorde = percentPercibeAcorde;
        this.percentPercibeEstrecho = percentPercibeEstrecho;
        this.percentPercibeMuyE = percentPercibeMuyE;
        this.promedioEdad = promedioEdad;
    }

    public double getPercentPatologiaSi() {
        return percentPatologiaSi;
    }

    public double getPercentLeer() {
        return percentLeer;
    }

    public double getPercentManiObjSim() {
        return percentManiObjSim;
    }

    public double getPercentManiObjPeq() {
        return percentManiObjPeq;
    }

    public double getPercentManiMaq() {
        return percentManiMaq;
    }

    public double getPercentUsarOrd() {
        return percentUsarOrd;
    }

    public double getPercentDificultadMuy() {
        return percentDificultadMuy;
    }

    public double getPercentDificultadDif() {
        return percentDificultadDif;
    }

    public double getPercentDificultadMedio() {
        return percentDificultadMedio;
    }

    public double getPercentDificultadFacil() {
        return percentDificultadFacil;
    }

    public double getPercentDificultadMuyF() {
        return percentDificultadMuyF;
    }

    public double getPercentPosturaSi() {
        return percentPosturaSi;
    }

    public double getPercentAnomaliaArdor() {
        return percentAnomaliaArdor;
    }

    public double getPercentAnomaliaDolor() {
        return percentAnomaliaDolor;
    }

    public double getPercentAnomaliaDisting() {
        return percentAnomaliaDisting;
    }

    public double getPercentAnomaliaNuca() {
        return percentAnomaliaNuca;
    }

    public double getPercentAnomaliaNone() {
        return percentAnomaliaNone;
    }

    public double getPercentCambiarPSi() {
        return percentCambiarPSi;
    }

    public double getPercentPercibeMuyA() {
        return percentPercibeMuyA;
    }

    public double getPercentPercibeAmplio() {
        return percentPercibeAmplio;
    }

    public double getPercentPercibeAcorde() {
        return percentPercibeAcorde;
    }

    public double getPercentPercibeEstrecho() {
        return percentPercibeEstrecho;
    }

    public double getPercentPercibeMuyE() {
        return percentPercibeMuyE;
    }

    public double getPromedioEdad() {
        return promedioEdad;
    }
}
