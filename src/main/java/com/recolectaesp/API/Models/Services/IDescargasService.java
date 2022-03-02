package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Descargas;
import com.recolectaesp.API.Models.Entity.Vehiculos;

import java.util.Date;
import java.util.List;

public interface IDescargasService {

    Descargas guardarDescarga(Descargas descarga, Long pesoCarga, Vehiculos vehiculo, String tipoResiduo);

    List<Descargas> listarDescargasPorVehiculoYPorFecha(Date fechaInicio, Date fechaFin , Vehiculos vehiculo);
}
