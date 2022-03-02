package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO para la informacion de liquidacion de un vehiculo.
 */
@Data
public class LiquidacionDto implements Serializable {

    /**
     * placa del vehiculo
     */
    @JsonProperty("placaVehiculo")
    private String placaVehiculo;

    /**
     * numero de descarga asignadas al vehiculo
     */
    @JsonProperty("numeroDescargas")
    private Integer numeroDescargas;

    /**
     * total de descuentos asignados al vehiculo
     */
    @NotNull
    @JsonProperty("descuentos")
    private Long totalMultas;

    /**
     * total del valor a pagar asignado al vehiculo.
     */
    @NotNull
    @JsonProperty("valorAPagar")
    private Long totalPagar;

}
