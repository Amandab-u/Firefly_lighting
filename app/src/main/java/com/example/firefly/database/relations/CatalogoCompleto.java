package com.example.firefly.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.firefly.database.entity.AltoEntity;
import com.example.firefly.database.entity.AnchoEntity;
import com.example.firefly.database.entity.BalastoEntity;
import com.example.firefly.database.entity.CatalogoEntity;
import com.example.firefly.database.entity.DiametroEntity;
import com.example.firefly.database.entity.FotometriaEntity;
import com.example.firefly.database.entity.LargoEntity;
import com.example.firefly.database.entity.ProfundidadEntity;

import java.util.List;

public class CatalogoCompleto {

    @Embedded
    public CatalogoEntity catalogo;

    @Relation(parentColumn = "id_catalogo",
              entityColumn = "idLuminaria",
                entity = LargoEntity.class,
                projection = {"largo_lum"})
    public LargoEntity largos;


    @Relation(parentColumn = "id_catalogo",
                entityColumn = "idLuminariaP",
                entity = ProfundidadEntity.class,
                projection = {"profundidad"})
    public ProfundidadEntity profundidades;

    @Relation(parentColumn = "id_catalogo",
                entityColumn = "idLuminariaA",
                entity = AnchoEntity.class,
                projection = {"ancho"})
    public AnchoEntity anchos;

    @Relation(parentColumn = "id_catalogo",
            entityColumn = "idLuminariaAl",
            entity = AltoEntity.class,
            projection = {"alto"})
    public AltoEntity altos;

    @Relation(parentColumn = "id_catalogo",
            entityColumn = "idLuminariaD",
            entity = DiametroEntity.class,
            projection = {"diametro"})
    public DiametroEntity diametro;

    @Relation(parentColumn = "id_catalogo",
            entityColumn = "idLuminariaB",
            entity = BalastoEntity.class,
            projection = {"balasto"})
    public BalastoEntity balastos;

    @Relation(parentColumn = "id_catalogo",
            entityColumn = "id_luminaria",
            entity = FotometriaEntity.class)
    public List<FotometriaEntity> fotometria;


}
