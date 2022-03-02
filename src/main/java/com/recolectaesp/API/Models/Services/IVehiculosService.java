package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Dto.VehiculoDto;
import com.recolectaesp.API.Models.Entity.Vehiculos;



public interface IVehiculosService {

    Vehiculos consultarVehiculoPorPlaca(String placa);

    Boolean existeVehiculo (String placa);

    VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto);

    Integer eliminarvehiculo(String placa);

    VehiculoDto actualizarVehiculo(VehiculoDto vehiculoDto);


}
