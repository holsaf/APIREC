package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class VehiculoDto implements Serializable {

    @Size(min=9, max=10)
    @JsonProperty("nitEmpresa")
    private String nitEmpresa;

    /**
     * columna para la placa del vehiculo.
     */
    @NotNull(message = "no puede estar vacio")
    @Size(min = 5, max = 8, message = "el tamaño tiene que estar entre 5 y 8")
    @JsonProperty("placaVehiculo")
    private String placa;

    /**
     * columna para el peso del vehiculo
     */
    @NotNull(message = "no puede estar vacio")
    //@JsonProperty("pesoVehiculo")
    private Integer pesoVehiculo;

    /**
     * columna para la capacidad comercial.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capComercial")
    private Integer capacidadCom;

    /**
     * columna para la capacidad domiciliaria.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capDomiciliaria")
    private Integer capacidadDom;

    /**
     * columna para la capacidad agricola.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capAgricola")
    private Integer capacidadAgr;

    /**
     * columna para la capacidad biomedica.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capBiomedico")
    private Integer capacidadBio;

    /**
     * columna para la capacidad industrial.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capIndustrial")
    private Integer capacidadInd;

    /**
     * columna para la capacidad construccion.
     */
    @NotNull(message = "no puede estar vacio")
    @JsonProperty("capConstruccion")
    private Integer capacidadCos;

}
