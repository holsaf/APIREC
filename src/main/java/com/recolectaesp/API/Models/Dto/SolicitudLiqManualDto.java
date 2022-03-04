package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * DTO para recibir los datos de la liquidacion manual
 */
@Data
public class SolicitudLiqManualDto  {


    /**
     * mes a liquidar
     */
    //@JsonProperty("mesLiquidar")
    @Min(value= 1,message="El valor minimo para el mes es 1")
    @Max(value=12, message="El valor maximo para el mes es 12")
    private Integer mesLiquidar;

    /**
     * año a liquidar
     */
    @Min(value=2020, message="El valor minimo para el año es 2020") //El sistema empezo a funcionar en el año 2020
    //@JsonProperty("anoLiquidar")
    private Integer anoLiquidar;

}
