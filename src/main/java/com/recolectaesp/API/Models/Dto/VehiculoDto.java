package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class VehiculoDto  {

    @NotNull(message = "Se requiere el nit de la empresa")
    @Size(min=9, max=10, message="El numero de digitos del NIT debe ser entre 9 y 10")
    //@JsonProperty("nitEmpresa")
    private String nitEmpresa;

    /**
     * columna para la placa del vehiculo.
     */
    @NotNull(message = "Se requiere la placa del vehiculo")
    @Size(min = 5, max = 8, message = "El numero de caracteres debe estar entre 5 y 8")
    @JsonProperty("placaVehiculo")
    private String placa;

    /**
     * columna para el peso del vehiculo
     */
    @NotNull(message = "Se requiere el peso del vehiculo")
    //@JsonProperty("pesoVehiculo")
    @Min(value= 10000,message="El valor minimo para el peso es 10000")
    @Max(value=30000, message="El valor maximo para el peso es 30000")
    private Integer pesoVehiculo;

    /**
     * columna para la capacidad comercial.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Comercial")
    @JsonProperty("capComercial")
    private Integer capacidadCom;

    /**
     * columna para la capacidad domiciliaria.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Domiciliaria")
    @JsonProperty("capDomiciliaria")
    private Integer capacidadDom;

    /**
     * columna para la capacidad agricola.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Agricola")
    @JsonProperty("capAgricola")
    private Integer capacidadAgr;

    /**
     * columna para la capacidad biomedica.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Biomedico")
    @JsonProperty("capBiomedico")
    private Integer capacidadBio;

    /**
     * columna para la capacidad industrial.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Industrial")
    @JsonProperty("capIndustrial")
    private Integer capacidadInd;

    /**
     * columna para la capacidad construccion.
     */
    @NotNull(message = "Se requiere la capacidad de carda de residuos Construccion")
    @JsonProperty("capConstruccion")
    private Integer capacidadCos;

}
