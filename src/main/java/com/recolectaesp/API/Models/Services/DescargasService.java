package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Descargas;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import com.recolectaesp.API.Models.Repository.IDescargasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Servicio para todas las funcionalidades de las descargas en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class DescargasService implements IDescargasService{

    @Autowired
    IMultasService multaService;

    @Autowired
    IDescargasRepository descargaRepository;

    /**
     * METODO PARA GUARDAR LA DESCARGA, Y CONFIGURA EL PESOCARGA, LA PLACA Y EL TIPO DE RESIDUO.
     * @param descarga
     * @param pesoCarga
     * @param vehiculo
     * @param tipoResiduo
     * @return retorna la descarga que se guardo en la base de datos.
     */
    @Override
    @Transactional
    public Descargas guardarDescarga(Descargas descarga, Long pesoCarga, Vehiculos vehiculo, String tipoResiduo) {
        descarga.setPesoDescarga(pesoCarga);
        descarga.setMulta(multaService.calcularMulta(vehiculo, pesoCarga, tipoResiduo));
        descarga.setVehiculo(vehiculo);
        return descargaRepository.save(descarga) ;
    }

    /**
     * METODO PARA LISTAR LAS DESCARGAS POR VEHICULO Y FECHA DE INICIO Y FIN.
     * @param fechaInicio
     * @param fechaFin
     * @param vehiculo
     * @return retorna un arreglo con las descargas que el vehiculo tiene en las fechas consultadas.
     */
    @Override
    @Transactional (readOnly = true)
    public List<Descargas> listarDescargasPorVehiculoYPorFecha(Date fechaInicio, Date fechaFin, Vehiculos vehiculo) {
        return descargaRepository.findByVehiculoAndFecha(fechaInicio, fechaFin, vehiculo);
    }
}
