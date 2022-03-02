package com.recolectaesp.API.Models.Mappers;

import com.recolectaesp.API.Models.Dto.LiquidacionDto;
import com.recolectaesp.API.Models.Entity.Liquidaciones;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MAPPER para conversion de entity Liquidaciones a LiquidacionesDto
 */
@Component
@Mapper(componentModel = "spring")
public interface ILiquidacionMapper {


    /**
     * configuracion inicial de parametros que no se mapean.
     * @param liquidacion: liquidacion que se va a convertir.
     * @param liquidacionDto: parametro par enviar al usuario.
     */
    @BeforeMapping
    default void setPlacaVehiculo(Liquidaciones liquidacion , @MappingTarget LiquidacionDto liquidacionDto){
        liquidacionDto.setPlacaVehiculo(liquidacion.getVehiculo().getPlaca());
    }
    LiquidacionDto liquidacionToLiquidacionDto (Liquidaciones liquidacion);
    List<LiquidacionDto> liquidacionListToLiquidacionDtoList (List<Liquidaciones> liquidacionList);

    //Mapping para configurar los atributos a mapear.
    //@Mappings({@Mapping(target = "placaVehiculo" , ignore = true)})



//    @Mappings({
//            @Mapping(target = "placaVehiculo", ignore = true)
//    })




}
