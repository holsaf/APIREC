package com.recolectaesp.API.Models.Dto;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * DTO para recibir los datos para la liquidacion de empresa.
 */
@Data
public class SolicitudLiqEmpresaDto {


    /**
     * mes  a liquidar
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

    /**
     * NIT de la empresa
     */
    @Size(min=9, max=10 , message="El numero de digitos del NIT debe ser entre 9 y 10")
    //@JsonProperty("nit")
    private String nit;

}
