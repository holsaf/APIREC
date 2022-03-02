package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import com.recolectaesp.API.Models.Repository.IEmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para todas las funcionalidades de las empresas en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class EmpresasService implements IEmpresasService{

    @Autowired
    IEmpresasRepository empresaRepository;

    /**
     * METODO PARA SABER SI UNA EMPRESA EXISTE.
     * @param nitEmpresa nit de la empresa a consultar
     * @return retorna TRUE si la empresa existe.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeEmpresa(String nitEmpresa) {
        return empresaRepository.existsByNitEmpresa(nitEmpresa);
    }

    /**
     * METODO PARA CONSULTAR SI LA EMPRESA TIENE VEHICULOS.
     * @param nitEmpresa
     * @return retorna TRUE si la empresa tiene vehiculos asignados.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existenVehiculos(String nitEmpresa) {

        return (!empresaRepository.findAllVehiculosEmpresa(nitEmpresa).isEmpty());
    }

    /**
     * METODO PARA LISTAR LOS VEHICULOS QUE TIENE UNA EMPRESA ASIGNADOS.
     * @param nitEmpresa
     * @return retorna un arreglo de vehiculos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Vehiculos> listarVehiculos(String nitEmpresa) {
        return empresaRepository.findAllVehiculosEmpresa(nitEmpresa);
    }
    @Override
    @Transactional(readOnly = true)
    public Empresas consultarEmpresa(String nitEmpresa){
        return empresaRepository.findByNitEmpresa(nitEmpresa);
    }
}
