package com.recolectaesp.API.Models.Mappers;


import com.recolectaesp.API.Models.Dto.LiquidacionDto;
import com.recolectaesp.API.Models.Dto.VehiculoDto;
import com.recolectaesp.API.Models.Entity.Descargas;
import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Liquidaciones;
import com.recolectaesp.API.Models.Entity.Vehiculos;

import org.mapstruct.*;
import org.springframework.stereotype.Component;


/**
 * MAPPER para transformaciones de datos de vehiculo a vehiculosDto
 */
@Component
@Mapper(componentModel = "spring")
public interface IVehiculosMapper {

    @BeforeMapping
    default void setNitEmpresa(Vehiculos vehiculo , @MappingTarget VehiculoDto vehiculoDto){
        vehiculoDto.setNitEmpresa(vehiculo.getEmpresa().getNitEmpresa());
    }

    VehiculoDto vehiculoToVehiculoDto (Vehiculos vehiculo);
    Vehiculos vehiculoDtoToVehiculo (VehiculoDto vehiculoDto);


//    @Mappings({
//            @Mapping(target = "nitEmpresa" , ignore = true)
//   })



//    @Mappings({
//            @Mapping(target = "idVehiculo", ignore = true),
//            @Mapping(target = "empresa", ignore = true)
//   })


    }


