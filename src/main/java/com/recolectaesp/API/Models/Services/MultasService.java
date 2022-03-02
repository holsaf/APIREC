package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.stereotype.Service;

/**
 * Servicio para las funciones de Multas en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class MultasService implements IMultasService{

    /**
     * METODO PARA CALCULAR LA MULTA DE LA EMPRESA
     * @param pesoMulta
     * @param empresa
     * @return retorna el valor de la multa
     */
    @Override
    public long multarEmpresa(Long pesoMulta, Empresas empresa){
        return pesoMulta*empresa.getRangoMulta();
    }


    /**
     * METODO PARA CALCULAR LA MULTA DEL VEHICULO
     * @param vehiculo
     * @param pesoCarga
     * @param tipoResiduo
     * @return
     */
    @Override
    public long calcularMulta (Vehiculos vehiculo, Long pesoCarga, String tipoResiduo){
        long pesoMulta;

        switch (tipoResiduo){
            case "COMERCIAL":
                pesoMulta = pesoCarga - vehiculo.getCapacidadCom();
                break;

            case "DOMICILIARIO":
                pesoMulta = pesoCarga - vehiculo.getCapacidadDom();
                break;

            case "INDUSTRIAL":
                pesoMulta = pesoCarga - vehiculo.getCapacidadInd();
                break;

            case "AGRICOLA":
                pesoMulta = pesoCarga - vehiculo.getCapacidadAgr();
                break;

            case "BIOMEDICO":
                pesoMulta = pesoCarga - vehiculo.getCapacidadBio();
                break;

            case "CONSTRUCCION":
                pesoMulta = pesoCarga - vehiculo.getCapacidadCos();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipoResiduo);
        }
        if(pesoMulta > 0){
            return multarEmpresa(pesoMulta, vehiculo.getEmpresa());

        }
        return 0;
    }
}
