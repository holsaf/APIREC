package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * DTO para recibir los datos requeridos para la signacion de celda.
 */
@Data
public class SolicitudCeldaDto {

    /**
     * placa del vehiculo
     */
    @Size(min=5, max=8, message = "La placa debe tener minimo 5 y maximo 8 caracteres")
    //@JsonProperty("placa")
    private String placa;

    /**
     * cedula del conductor
     */
    @Size(min=6, max=12, message = "La cedula debe tener minimo 6 y maximo 12 caracteres")
    //@JsonProperty("cedula")
    private String cedula;

    /**
     * peso del vehiculo en la bascula
     */
    @Min(value= 10000,message="El valor minimo para el peso es 1000")
    @Max(value=30000, message="El valor maximo para el peso es 30000")
    //@JsonProperty("peso")
    private Long peso;

    /**
     * tipo de residuo de la descarga.
     */
    @Pattern(regexp = "[A-Z]+", message="El tipo de residuo solo puede tener letras mayuscula")
    //@JsonProperty("tipoResiduo")
    private String tipoResiduo;

    /**
     * nombre del deposito
     */
    @Pattern(regexp = "[A-Z0-9]+", message=" El deposito solo puede tener letras mayúsculas o números")
    //@JsonProperty("deposito")
    private String deposito ;
}
