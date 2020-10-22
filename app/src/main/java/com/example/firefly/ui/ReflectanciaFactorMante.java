package com.example.firefly.ui;

import java.security.PublicKey;
import java.util.ArrayList;

public abstract class ReflectanciaFactorMante {


    //Valores de factor de mantenimiento
    public static final double MUY_LIMPIO = 0.8;
    public static final double LIMPIO = 0.67;
    public static final double NORMAL = 0.57;
    public static final double SUCIO = 0.5;

    //Valores de reflectancia piso
    public static final double PISO_CLARO = 0.3;
    public static final double PISO_OSCURO = 0.1;

    //Valores de reflectancia de paredes
    public static final double PARED_CLARO = 0.5;
    public static final double PARED_MEDIO = 0.3;
    public static final double PARED_OSCURO = 0.1;

    //Valores de reflectancia techo
    public static final double TECHO_BLANCO = 0.7;
    public static final double TECHO_CLARO = 0.5;
    public static final double TECHO_MEDIO = 0.3;

    //Valores de reflectancia para superf de trabajo
    public static final double BLANCO = 0.77;
    public static final double GRIS_CLARO = 0.575;
    public static final double GRIS_OSCURO = 0.15;
    public static final double NEGRO = 0.05;
    public static final double CREMA = 0.625;
    public static final double MARRON_CLARO = 0.35;
    public static final double MARRON_OSCURO = 0.15;
    public static final double ROSA = 0.5;
    public static final double ROJO_CLARO = 0.4;
    public static final double ROJO_OSCURO = 0.15;
    public static final double VERDE_CLARO = 0.5;
    public static final double VERDE_OSCURO = 0.15;
    public static final double AZUL_CLARO = 0.475;
    public static final double AZUL_OSCURO = 0.1;

    //Valores de iluminancia segun norma
    public static final double[] salonConferencia = new double[]{100, 1500, 2000};
    public static final double[] pantallas = new double[]{50,75,100};
    public static final double[] lapizPizarron = new double[]{500,750,1000};
    public static final double[] lapizCuatro = new double[]{1000,1500,2000};
    public static final double[] oficina = new double[]{200,300,500};


    public static double getfactorMantenimiento (int id){
        double factorMantenimiento = 0;
        switch (id){
            case 1:
                factorMantenimiento = MUY_LIMPIO;
                return factorMantenimiento;
            case 2:
                factorMantenimiento = LIMPIO;
                return factorMantenimiento;
            case 3:
                factorMantenimiento = NORMAL;
                return factorMantenimiento;
            case 4:
                factorMantenimiento = SUCIO;
                return factorMantenimiento;
            default:
                return factorMantenimiento;
        }
    }

    public static double getReflectanciaPiso(int id){
        double rPiso = 0;
        switch (id){
            case 1:
                rPiso = PISO_CLARO;
                return rPiso;
            case 2:
                rPiso = PISO_OSCURO;
                return rPiso;
            default:
                return rPiso;
        }
    }

    public static double getReflectanciaPared(int id){
        double rPared = 0;
        switch (id){
            case 1:
                return PARED_CLARO;
            case 2:
                return PARED_MEDIO;
            case 3:
                return PARED_OSCURO;
            default:
                return rPared;
        }
    }

    public static double getReflectanciaTecho(int id){
        double rTecho=0;
        switch (id){
            case 1:
                return TECHO_BLANCO;
            case 2:
                return TECHO_CLARO;
            case 3:
                return TECHO_MEDIO;
            default:
                return rTecho;
        }
    }

    public static double getReflectanciaTrabajo(int id){
        double rTrabajo=0;
        switch (id){
            case 1:
                return BLANCO;
            case 2:
                return GRIS_CLARO;
            case 3:
                return GRIS_OSCURO;
            case 4:
                return NEGRO;
            case 5:
                return CREMA;
            case 6:
                return MARRON_CLARO;
            case 7:
                return MARRON_OSCURO;
            case 8:
                return ROSA;
            case 9:
                return ROJO_CLARO;
            case 10:
                return ROJO_OSCURO;
            case 11:
                return VERDE_CLARO;
            case 12:
                return VERDE_OSCURO;
            case 13:
                return AZUL_CLARO;
            case 14:
                return AZUL_OSCURO;
            default:
                return rTrabajo;
        }
    }

    public static double[] getNorma(int id){
        switch (id){
            case 1:
                return salonConferencia;
            case 2:
                return pantallas;
            case 3:
                return lapizPizarron;
            case 4:
                return lapizCuatro;
            default:
                return oficina;
        }
    }
}
