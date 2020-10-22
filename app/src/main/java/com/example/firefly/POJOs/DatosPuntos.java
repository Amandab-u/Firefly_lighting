package com.example.firefly.POJOs;

public class DatosPuntos {

    int idPunto;
    double intensidadX;
    double intensidadY;
    double anguloInter;

    public DatosPuntos(double intensidadX, double intensidadY, double anguloInter) {

        this.intensidadX = intensidadX;
        this.intensidadY = intensidadY;
        this.anguloInter = anguloInter;
    }


    public double getIntensidadX() {
        return intensidadX;
    }


    public double getIntensidadY() {
        return intensidadY;
    }



    public double getAnguloInter() {
        return anguloInter;
    }


}
