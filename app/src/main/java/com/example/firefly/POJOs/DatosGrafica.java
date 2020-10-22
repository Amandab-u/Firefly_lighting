package com.example.firefly.POJOs;

public class DatosGrafica {

    private float coordenadaX;
    private float coordenadaY;
    private double iluminancia;

    public DatosGrafica(float coordenadaX, float coordenadaY, double iluminancia) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.iluminancia = iluminancia;
    }

    public float getCoordenadaX() {
        return coordenadaX;
    }

    public float getCoordenadaY() {
        return coordenadaY;
    }

    public double getIluminancia() {
        return iluminancia;
    }
}
