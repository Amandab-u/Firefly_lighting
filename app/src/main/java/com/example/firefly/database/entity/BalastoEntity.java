package com.example.firefly.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Balasto")
public class BalastoEntity {

    @PrimaryKey(autoGenerate = true)
    public long id_balasto;

    public String balasto;

    public long idLuminariaB;

    public BalastoEntity (long id, String bal, long idL){
        this.id_balasto = id;
        this.balasto = bal;
        this.idLuminariaB = idL;
    }

    public BalastoEntity ( String bal, long idL){
        this.balasto = bal;
        this.idLuminariaB = idL;
    }
    public BalastoEntity(){}

    public String getBalasto() {
        return this.balasto;
    }
}
