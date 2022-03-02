package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Dto.CeldaAsignadaDto;
import com.recolectaesp.API.Models.Dto.SolicitudCeldaDto;
import com.recolectaesp.API.Models.Entity.*;
import com.recolectaesp.API.Models.Mappers.ICeldasMapper;
import com.recolectaesp.API.Models.Repository.ICeldasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para todas las funcionalidades de las celdas en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class CeldasService implements ICeldasService{

    @Autowired
    private IVehiculosService vehiculoService;

    @Autowired
    private ITipoResiduosService tipoResiduoService;

    @Autowired
    private IDepositosService depositoService;

    @Autowired
    private IMultasService multaService;

    @Autowired
    private IDescargasService descargasService;

    @Autowired
    private ICeldasRepository celdaRepository;

    @Autowired
    private ICeldasMapper celdaMapper;

    /**
     * METODO PARA CALCULAR LA MEJOR CELDA DISPONIBLE.
     * @param solicitudCeldaDto Parametros de entrada en el objeto SolicitudCeldaDto.
     * @return retorna el objeto CeldaAsignadaDto
     */
    @Override
    @Transactional
    public CeldaAsignadaDto calcularCelda(SolicitudCeldaDto solicitudCeldaDto) {
        Descargas newDescarga = new Descargas();
        System.out.println("calculo de la celda");
        Vehiculos vehiculo = vehiculoService.consultarVehiculoPorPlaca(solicitudCeldaDto.getPlaca());
        TipoResiduos residuo = tipoResiduoService.consultarTipoResiduosPorNombre(solicitudCeldaDto.getTipoResiduo());
        Depositos deposito = depositoService.consultarDepositoPorNombre(solicitudCeldaDto.getDeposito());
        Long pesoCarga = solicitudCeldaDto.getPeso() - vehiculo.getPesoVehiculo();
        newDescarga.setTipoResiduo(residuo);
        newDescarga.setCelda(celdaMayorCapacidadDisponible(residuo,deposito, pesoCarga));
        if(newDescarga.getCelda() != null){
            actualizarCapacidadDisponibleCelda(pesoCarga, newDescarga.getCelda());
            System.out.println(newDescarga.getCelda().getNombreCelda());
            System.out.println(solicitudCeldaDto.getPeso());
            System.out.println(pesoCarga.toString());
            return celdaMapper.descargaToCeldaAsignadaDto(descargasService.guardarDescarga(newDescarga, pesoCarga, vehiculo, solicitudCeldaDto.getTipoResiduo()));
        }
        return null;
    }

    /**
     * METODO PARA CONSULTA LA CELDA DE MAYOR CAPACIDAD DISPONIBLE.
     * @param tipoResiduo tipo de residuo para la consulta
     * @param deposito deposito de la celda a calcular
     * @param pesoCarga peso de la carga que se va a descarga.
     * @return retorna la Celda con la mayor capacidad
     */
    @Transactional(readOnly = true)
    public Celdas celdaMayorCapacidadDisponible(TipoResiduos tipoResiduo, Depositos deposito, Long pesoCarga){ // busca las celdas del tipo de residuo en el deposito
        Celdas celdaDisponible = celdaRepository.findCeldaDisponibleByTipoResiduoAndDeposito( tipoResiduo.getIdTipoResiduo() , deposito.getIdDeposito());
        if(celdaDisponible.getCapacidadDisponible()>= pesoCarga) {
            return celdaDisponible;
        }
        return null;
    }

    /**
     * METODO PARA ACTUALIZAR LA CAPACIDAD DISPONIBLE DE LA CELDA ASGINADA.
     * @param pesoCarga peso de la descarga para actualizar
     * @param celda celda que se quiere actualizar
     */
    @Transactional
    public void actualizarCapacidadDisponibleCelda(Long pesoCarga, Celdas celda){
        celda.setCapacidadDisponible(celda.getCapacidadDisponible()- pesoCarga);
    }
}
