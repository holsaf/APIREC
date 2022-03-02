package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Vehiculos;

public interface IMultasService {

    long multarEmpresa(Long pesoMulta, Empresas empresa);

    long calcularMulta (Vehiculos vehiculo, Long pesoCarga, String tipoResiduo);
}
