package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.TipoResiduos;
import com.recolectaesp.API.Models.Repository.ITipoResiduosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para todas las funcionalidades de los tipos de residuos en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class TipoResiduosService implements ITipoResiduosService{

    @Autowired
    private ITipoResiduosRepository tipoResiduoRepository;

    /**
     * METODO PARA CONSULTAR EL TIPO DE RESIDUO POR SU NOMBRE.
     * @param nombreResiduo
     * @return retorna el tipoResiduo.
     */
    @Override
    @Transactional(readOnly = true)
    public TipoResiduos consultarTipoResiduosPorNombre(String nombreResiduo) {
        return tipoResiduoRepository.findTipoResiduoByNombreResiduo(nombreResiduo);
    }


    /**
     * METODO PARA CONSULTAR LOS NOMBRES DE LOS TIPOS DE RESIDUOS EN EL SISTEMA.
     * @return retorna una arreglo con los nombres de los tipos de residuos.
     */
    @Override
    @Transactional
    public List<String> consultarTiposDeResiduos() {
        return tipoResiduoRepository.findNombreResiduos();
    }
}
