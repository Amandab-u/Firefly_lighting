package com.example.firefly.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.firefly.database.entity.AEResultadosEntity;
import com.example.firefly.database.entity.ConsumoElectricoEntity;
import com.example.firefly.database.entity.DatosLocalEntity;
import com.example.firefly.database.entity.DatosLuminariaEntity;
import com.example.firefly.database.entity.EncuestaResultsEntity;
import com.example.firefly.database.entity.IlumiCalculadaEntity;
import com.example.firefly.database.entity.ProyectosEntity;
import com.example.firefly.database.entity.PuntosAnalisisEntity;
import com.example.firefly.database.entity.ResultadosBasicosEntity;
import com.example.firefly.database.entity.VerificacionEntity;

import java.util.List;

public class ProyectoCompleto {

    @Embedded
    public ProyectosEntity proyecto;

    @Relation(parentColumn = "id_proyecto",
                entityColumn = "id_proyectoL",
                entity = DatosLocalEntity.class,
                projection = {"numero_luminarias"})
    public DatosLocalEntity datosLocal;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoL",
            entity = DatosLocalEntity.class)
    public DatosLocalEntity local;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyecto",
            entity = DatosLuminariaEntity.class)
    public List<DatosLuminariaEntity> luminariasProyecto;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyecto",
            entity = DatosLuminariaEntity.class)
    public DatosLuminariaEntity luminariaProyecto;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoPA",
            entity = PuntosAnalisisEntity.class)
    public List<PuntosAnalisisEntity> puntos;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoV",
            entity = VerificacionEntity.class)
    public List<VerificacionEntity> iluminanciasMedidas;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoE",
            entity = EncuestaResultsEntity.class)
    public EncuestaResultsEntity encuestaResults;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoC",
            entity = ConsumoElectricoEntity.class)
    public ConsumoElectricoEntity datosElectricos;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoAEresult",
            entity = AEResultadosEntity.class)
    public AEResultadosEntity resultElectricos;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoRB",
            entity = ResultadosBasicosEntity.class)
    public ResultadosBasicosEntity analisisBasico;

    @Relation(parentColumn = "id_proyecto",
            entityColumn = "id_proyectoIC",
            entity = IlumiCalculadaEntity.class)
    public List<IlumiCalculadaEntity> iluminanciasCalculadas;

}
