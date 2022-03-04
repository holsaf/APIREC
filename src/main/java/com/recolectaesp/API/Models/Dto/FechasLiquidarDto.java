package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para enviar al usuario los meses y años disponibles para liquidar.
 */
@Data
public class FechasLiquidarDto  {

    /**
     * Lista de los meses disponibles.
     */
    //@JsonProperty("mesesLiquidar")
    private List<String> mesesLiquidar;

    /**
     * Lista de los años disponibles.
     */
    //@JsonProperty("anosLiquidar")
    private List<Integer> anosLiquidar;

}
