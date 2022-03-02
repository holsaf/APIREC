package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Vehiculos;

import java.util.List;

public interface IEmpresasService {

    boolean existeEmpresa( String nitEmpresa);

    boolean existenVehiculos( String nitEmpresa);

    List<Vehiculos> listarVehiculos(String nitEmpresa);

    Empresas consultarEmpresa(String nitEmpresa);
}
