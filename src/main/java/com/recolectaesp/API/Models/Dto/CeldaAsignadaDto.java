package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * DTO para enviar al usario la celda asignada.
 */
@Data
public class CeldaAsignadaDto implements Serializable {

    /**
     * placa asignada a la descarga
     */
    @JsonProperty("placaAsignada")
    private String placaAsignada;

    /**
     * celda asignada a la descarga
     */
    @JsonProperty("celdaAsignada")
    public String celdaAsignada ;
}


