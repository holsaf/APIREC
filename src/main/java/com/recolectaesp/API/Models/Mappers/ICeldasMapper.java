package com.recolectaesp.API.Models.Mappers;


import com.recolectaesp.API.Models.Dto.CeldaAsignadaDto;
import com.recolectaesp.API.Models.Entity.Descargas;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

/**
 * MAPPER para transformaciones de datos de entiry celdas a celdasAginadaDto
 */
@Component
@Mapper(componentModel = "spring")
public interface ICeldasMapper {

    /**
     * configuracion de parametros iniciales que no se mapean, directamente.
     * @param descarga: descarga que se asigno a la descarga.
     * @param celdaAsignadaDto: parametro para enviar al usuario.
     */
    @BeforeMapping
    default void setCeldaYPlacaAsignada(Descargas descarga, @MappingTarget CeldaAsignadaDto celdaAsignadaDto){
        celdaAsignadaDto.setCeldaAsignada(descarga.getCelda().getNombreCelda());
        celdaAsignadaDto.setPlacaAsignada(descarga.getVehiculo().getPlaca());
    }

    @Mappings({
            @Mapping(target = "celdaAsignada" , ignore = true)
    })
    CeldaAsignadaDto descargaToCeldaAsignadaDto (Descargas descarga);
}
