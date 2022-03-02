package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Conductores;
import com.recolectaesp.API.Models.Repository.IConductoresRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para todas las funcionalidades de los conductores en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class ConductoresService implements IConductoresService {

    @Autowired
    private IConductoresRepository conductorRepository;


    /**
     *METODO PARA CONSULTAR LA EXISTENCIA DE UN CONDUCTOR
     * @param cedula
     * @return retorna True si el vehiculo existe.
     */
    @Override
    @Transactional(readOnly = true)
    public Boolean existeConductor(String cedula) {
        return conductorRepository.existsByCedula(cedula);
    }
}