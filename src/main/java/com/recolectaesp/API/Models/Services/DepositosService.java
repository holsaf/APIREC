package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Depositos;
import com.recolectaesp.API.Models.Repository.IDepositosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para todas las funcionalidades de los depositos en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class DepositosService implements IDepositosService{

    @Autowired
    private IDepositosRepository depositoRepository;

    /**
     * METODO PARA CONSULTAR DEPOSITO POR SU NOMBRE
     * @param nombreDeposito nombre del deposito
     * @return retorna el deposito
     */
    @Override
    @Transactional(readOnly = true)
    public Depositos consultarDepositoPorNombre(String nombreDeposito) {
        return depositoRepository.findDepositoByNombreDeposito(nombreDeposito) ;
    }

    /**
     * METODO PARA CONSULTAR SI UN DEPOSITO EXISTE.
     * @param nombreDeposito
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Boolean existeDeposito(String nombreDeposito) {
        return depositoRepository.existsByNombreDeposito(nombreDeposito);
    }


}
