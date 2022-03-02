package com.recolectaesp.API.Models.Entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ENTITY para la tabla de conductores
 */
@Entity
@Table(name="REC_CONDUCTORES")
@Data
public class Conductores implements Serializable {

    /**
     * columna del ID de conductores, llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Integer idConductor;

    /**
     * columna para el id_empresa para la relacion con la tabla de empresas.
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    private Empresas Empresa;

    /**
     * cedula del conductor
     */
    @NotNull
    @Size(min=6, max=12)
    @Column(unique = true)
    private String cedula;

    /**
     * nombre del conductor
     */
    @Column(name = "nombre_conductor")
    private String nombreConductor;


}



