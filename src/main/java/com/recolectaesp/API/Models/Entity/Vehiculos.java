package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ENTITY para la tabla de vehiculos.
 */
@Entity
@Table(name = "REC_VEHICULOS")
@Data
public class Vehiculos implements Serializable {

    /**
     * columna para el ID, llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;


    /**
     * columna para el id_empresa, para la relacion con la tabla empresas.
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    private Empresas empresa;

    /**
     * columna para la placa del vehiculo.
     */
    @NotNull
    @Size(min=5, max=8)
    @Column(unique = true)
    private String placa;

    /**
     * columna para el peso del vehiculo
     */
    @NotNull
    @Column(name = "peso_vehiculo")
    private Integer pesoVehiculo;

    /**
     * columna para la capacidad comercial.
     */
    @NotNull
    @Column(name = "capacidad_com")
    private Integer capacidadCom;

    /**
     * columna para la capacidad domiciliaria.
     */
    @NotNull
    @Column(name = "capacidad_dom")
    private Integer capacidadDom;

    /**
     * columna para la capacidad agricola.
     */
    @NotNull
    @Column(name = "capacidad_agr")
    private Integer capacidadAgr;

    /**
     * columna para la capacidad biomedica.
     */
    @NotNull
    @Column(name = "capacidad_bio")
    private Integer capacidadBio;

    /**
     * columna para la capacidad industrial.
     */
    @NotNull
    @Column(name = "capacidad_ind")
    private Integer capacidadInd;

    /**
     * columna para la capacidad construccion.
     */
    @NotNull
    @Column( name = "capacidad_cos")
    private Integer capacidadCos;


}