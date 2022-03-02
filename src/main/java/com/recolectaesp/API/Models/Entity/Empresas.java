package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * ENTITY para la tabla de empresas.
 */
@Entity
@Table(name = "REC_EMPRESAS")
@Data
public class Empresas implements Serializable {

    /**
     * columna para el ID, llave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    /**
     * relacion con la tabla vehiculos.
     */
    @OneToMany(mappedBy = "empresa")
    private List<Vehiculos> vehiculosEmpresa;

    /**
     * columna para el NIT de la empresa.
     */
    @Size(min=9, max=10)
    @NotNull
    @Column(name = "nit_empresa", unique = true)
    private String nitEmpresa;

    /**
     * columna para el nombre de la empresa
     */
    @NotNull
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    /**
     * columna para el rango del calculo de multas
     */
    @NotNull
    @Column(name = "rango_multa")
    private Integer rangoMulta;

    /**
     * columna para el rango del calculo del valor a pagar
     */
    @NotNull
    @Column(name = "rango_kilo")
    private Integer rangoKilo;








}

