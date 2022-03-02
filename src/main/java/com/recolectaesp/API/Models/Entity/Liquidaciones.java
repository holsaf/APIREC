package com.recolectaesp.API.Models.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ENTITY para la tabla de liquidaciones.
 */
@Entity
@Table(name="REC_LIQUIDACIONES", uniqueConstraints={@UniqueConstraint(columnNames ={"id_vehiculo",
                                                     "ano_liquidado","mes_liquidado"})})
@Data
public class Liquidaciones implements Serializable {

    /**
     * columna para el ID, llave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liquidacion")
    private Long idLiquidacion;

    /**
     * columna para el id_vehiculo, para la relacion con la tabla vehiculos.
     */
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculos vehiculo;

    /**
     * columna para el año de liquidacion.
     */
    @NotNull
    @Column(name = "ano_liquidado")
    private Integer anoLiquidado;

    /**
     * columna para el mes de liquidacion.
     */
    @NotNull
    @Column(name = "mes_liquidado")
    private Integer mesLiquidado;

    /**
     * columa para el total de las multas de la liquidacion
     */
    @NotNull
    @Column(name = "total_multas")
    private Long totalMultas;

    /**
     * columa para el total a pagar de la liquidacion, sin tener en cuenta la multa.
     */
    @NotNull
    @Column(name = "total_pagar")
    private Long totalPagar;

    /**
     * columna para el numero total de desargas de la liquidacion.
     */
    @NotNull
    @Column(name = "numeroDescargas")
    private Integer numeroDescargas;





}
