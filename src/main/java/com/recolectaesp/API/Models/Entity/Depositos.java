package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ENTITY para la tabal de depositos.
 */
@Entity
@Table(name="REC_DEPOSITOS")
@Data
public class Depositos implements Serializable {

    /**
     * columa del ID, llave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposito")
    private Integer idDeposito;

    /**
     * columa para el nombre del posito.
     */
    @NotEmpty
    @NotNull
    @Column(name = "nombre_deposito", unique = true)
    private String nombreDeposito;

    /**
     * columa para el municipio del deposito
     */
    private String municipio;

    /**
     * columa para el departamento
     */
    private String departamento;


}
