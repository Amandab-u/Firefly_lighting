package com.example.firefly.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "catalogo_luminarias")
public class CatalogoEntity  {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_catalogo")
    public long idCatalogo;

    @ColumnInfo(name = "nombre_luminaria")
    public String nombreLuminaria;

    @ColumnInfo(name = "descripciton_luminaria")
    public String descripcionLuminaria;

    @ColumnInfo(name = "flujo_luminoso_total")
    public int flujoLumTotal;

    @ColumnInfo(name = "light_output_rate")
    public double lightOutputRate;

    @ColumnInfo(name = "flujo_luminoso")
    public int flujoLuminoso;

    @ColumnInfo(name = "potencia_electrica")
    public int potenciaElectrica;

    @ColumnInfo(name = "indice_rc")
    public int indiceRc;

    @ColumnInfo(name = "temperatura_color")
    public int temperaturaColor;

    public int imagenLumReal;

    public int imagenFotometria;

    public String difusor;

    public CatalogoEntity(long id, String name, String descripcion,
                          int flujoTotal, double ratio, int flujoLuminoso,
                          int potencia, int indice, int temperatura, String dif, int imagenReal, int imagenFoto){
        this.idCatalogo=id;
        this.nombreLuminaria=name;
        this.descripcionLuminaria=descripcion;
        this.flujoLumTotal=flujoTotal;
        this.lightOutputRate=ratio;
        this.flujoLuminoso=flujoLuminoso;
        this.potenciaElectrica=potencia;
        this.indiceRc=indice;
        this.temperaturaColor=temperatura;
        this.difusor=dif;
        this.imagenLumReal=imagenReal;
        this.imagenFotometria = imagenFoto;
    }


    public CatalogoEntity(){}

    public String getNombre(){

        return this.nombreLuminaria;
    }

    public String getDescripcion(){

        return this.descripcionLuminaria;
    }

    public long getId(){

        return this.idCatalogo;
    }

    public int getFlujoLumTotal() {
        return this.flujoLumTotal;
    }

    public int getPotenciaElectrica() {
        return this.potenciaElectrica;
    }

    public double getLightOutputRate() {
        return this.lightOutputRate;
    }

    public int getFlujoLuminoso() {
        return this.flujoLuminoso;
    }

    public int getIndiceRc() {
        return this.indiceRc;
    }

    public int getTemperaturaColor() {
        return this.temperaturaColor;
    }

    public String getDifusor() {
        return this.difusor;
    }

    public int getImagenLumReal() {
        return this.imagenLumReal;
    }

    public int getImagenFotometria() {
        return this.imagenFotometria;
    }
}
