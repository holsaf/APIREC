package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * ENTITY para la tabla de Celdas
 */
@Entity
@Table(name="REC_CELDAS", indexes = {@Index(name="REC_IDX_FINDCELDA", columnList = "id_tipo_residuo,id_deposito")})
@Data
public class Celdas implements Serializable {

    /**
     * columna del ID de celdas, llave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_celda")
    private Integer idCelda;

    /**
     * columna del id_tipoResiduo para la relacion con la tabla de tipoResiduos.
     */
    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo", referencedColumnName = "id_tipo_residuo")
    private TipoResiduos tipoResiduo;

    /**
     * columna del id_deposito para la relacion con la tabla de depositos.
     */
    @ManyToOne
    @JoinColumn(name = "id_deposito", referencedColumnName = "id_deposito")
    private Depositos deposito;

    /**
     * columna de nombre de la celda
     */
    @NotNull
    @Column(name="nombre_celda")
    private String nombreCelda;

    /**
     * columna de la capacidad total de almacenamiento de la celda
     */
    @Column(name = "capacidad_total")
    @NotNull
    private Long capacidadTotal;

    /**
     * columna de la capacidad disponible de la celda
     */
    @NotNull
    @Column(name = "capacidad_disponible")
    private Long capacidadDisponible;

}
