package com.recolectaesp.API.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.recolectaesp.API.Models.Entity.Liquidaciones;
import lombok.Data;

import java.io.Serializable;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * DTO para almacenar las liquidaciones de los vehiculos asignados a la empresa.
 */
@Data
public class LiquidacionesEmpresaDto  {

    /**
     * Constructor para configurar la fecha de liquidacion y el nombre de la empresa, no se mapean.
     * @param liquidacionList se requiere la lista de liqudiciones de lo vehiculos para el proceso.
     */
    public LiquidacionesEmpresaDto(List<Liquidaciones> liquidacionList) {

        Month mes = Month.of(liquidacionList.get(0).getMesLiquidado());
        this.mesLiquidacion = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase(Locale.ROOT).concat("/" + liquidacionList.get(0).getAnoLiquidado().toString());
        this.nombreEmpresa = liquidacionList.get(0).getVehiculo().getEmpresa().getNombreEmpresa();
    }

    /**
     * lista de liquidaciones de los vehiculos de le empresa.
     */
    //@JsonProperty("listaLiquidaciones")
    private List<LiquidacionDto> listaLiquidaciones;

    /**
     * nombre de la empresa
     */
    //@JsonProperty("nombreEmpresa")
    private String nombreEmpresa;

    /**
     * fecha de las liquidaciones
     */
    //@JsonProperty("mesLiquidacion")
    private String mesLiquidacion;
}
