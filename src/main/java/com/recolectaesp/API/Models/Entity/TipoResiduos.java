package com.recolectaesp.API.Models.Entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ENTITY pora la tabla de tipoResiduos.
 */
@Entity
@Table(name="REC_TIPO_RESIDUOS")
@Data
public class TipoResiduos implements Serializable {

    /**
     * columna para el ID, llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_residuo")
    private Integer idTipoResiduo;

    /**
     * columna para nombre del tipo de residuo.
     */
    @NotNull
    @Column(name = "nombre_residuo", unique = true)
    private String nombreResiduo;

    /**
     * columna para el precio asignado al tipo de residuo
     */
    @NotNull
    @Column(name = "precio_kilo")
    private Long precioKilo;

}
