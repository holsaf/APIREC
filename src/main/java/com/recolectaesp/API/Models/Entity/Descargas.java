package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * ENTITY para la tabla de descargas.
 */
@Entity
@Table(name="REC_DESCARGAS", indexes = {@Index(name="REC_IDX_FINDDESCARGAS", columnList = "id_vehiculo, fecha_descarga")})
@Data
public class Descargas implements Serializable {

    /**
     * columa para el ID, llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descarga")
    private Long idDescarga;

    /**
     * columna para el id_vehiculos, relacion con la tabla vehiculos.
     */
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculos vehiculo;

    /**
     * columa para el id_tipo_residuo, relacion con la tabla de tipoResiduos.
     */
    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo", referencedColumnName = "id_tipo_residuo")
    private TipoResiduos TipoResiduo;

    /**
     * columna para el id_celda, para la relacion con la tabla celdas.
     */
    @ManyToOne
    @JoinColumn(name = "id_celda", referencedColumnName = "id_celda")
    private Celdas Celda;

    /**
     * columa del peso de descarga.
     */

    @Column(name = "peso_descarga")
    @NotNull
    private Long pesoDescarga;

    /**
     * columna para la fecha de realizacion de la descarga.
     */
    @Column(name = "fecha_descarga")
    @Temporal(TemporalType.DATE)
    private Date fechaDescarga;

    //funcion para guardar la fecha de forma automatica.
    @PrePersist
    public void prePersist(){
        fechaDescarga = new Date();
    }

    /**
     * columna para las multas que tenga el vehiculo.
     */
    @NotNull
    private Long multa;


}
