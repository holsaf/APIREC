package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Dao.ILiquidacionesDao;
import com.recolectaesp.API.Models.Dto.FechasLiquidarDto;
import com.recolectaesp.API.Models.Dto.LiquidacionesEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqManualDto;
import com.recolectaesp.API.Models.Entity.Liquidaciones;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import com.recolectaesp.API.Models.Mappers.ILiquidacionMapper;
import com.recolectaesp.API.Models.Repository.ILiquidacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

/**
 * Servicio para todas las funcionalidades de las liquidaciones en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class LiquidacionesService implements ILiquidacionesService {

    @Autowired
    private IEmpresasService empresasService;

    @Autowired
    private IDescargasService descargaService;

    @Autowired
    private ILiquidacionesRepository liquidacionRepository;

    @Autowired
    private ILiquidacionMapper liquidacionMapper;

    @Autowired(required=true)
    private ILiquidacionesDao liquidacionesDao;

    /**
     * METODO PARA GENERAR LA LIQUIDACION DE UNA EMPRESA.
     * @param solicitudLiqEmpresaDto objeto con los parametros que vienen del front, nit,mes y año a liquidar.
     * @return retorna un objeto con la fecha de liquidacion, nombre de empresa y arrglo de liquidacion de vehiculos.
     */
    @Override
    @Transactional
    public LiquidacionesEmpresaDto liquidacionEmpresa(SolicitudLiqEmpresaDto solicitudLiqEmpresaDto){
        List<Liquidaciones> listadoLiquidaciones = new ArrayList<>();
        List<Vehiculos> listaVehiculos = empresasService.listarVehiculos(solicitudLiqEmpresaDto.getNit());
        Liquidaciones liquidacionPrueba;
        for (Vehiculos vehiculo : listaVehiculos){
                liquidacionPrueba = consultarLiquidacionVehiculo(vehiculo, solicitudLiqEmpresaDto.getMesLiquidar(), solicitudLiqEmpresaDto.getAnoLiquidar());
                if(liquidacionPrueba!=null) {
                    listadoLiquidaciones.add(liquidacionPrueba);
                }
        }
        if(listadoLiquidaciones.isEmpty()){
            return null;
        }
        LiquidacionesEmpresaDto liquidacionesEmpresaDto = new LiquidacionesEmpresaDto(listadoLiquidaciones);
        liquidacionesEmpresaDto.setListaLiquidaciones(liquidacionMapper.liquidacionListToLiquidacionDtoList(listadoLiquidaciones));
        return liquidacionesEmpresaDto;
    }

    /**
     * METODO PARA CONSULTAR LA LIQUIDACION DE UN VEHICULO.
     * @param vehiculo
     * @param mesLiquidar
     * @param anoLiquidar
     * @return retorna la liquidacion de un vehiculo.
     */
    @Override
    @Transactional
    public Liquidaciones consultarLiquidacionVehiculo (Vehiculos vehiculo,Integer mesLiquidar, Integer anoLiquidar)  {
        Optional<Liquidaciones> liquidacionConsultadaOpt = liquidacionRepository.findLiquidacionByVehiculoAndAnoLiquidadoAndMesLiquidado(vehiculo, anoLiquidar, mesLiquidar);
        if(liquidacionConsultadaOpt.isEmpty()){
            return null;
        }
        return liquidacionConsultadaOpt.get();
    }

    /**
     * METODO PARA LLAMAR EL PROCEDIMIENTO DE LIQUIDACION MENSUAL DE TODOS LOS VEHICULOS DEL SISTEMA.
     * @param solicitudLiqManualDto objeto con los datos requeridos para la liquidacion.
     * @return retorna un string con el mensaje del inicio exitoso del procedimiento.
     */
    @Override
    @Transactional
    public String llamarLiquidacionMensualVehiculos(SolicitudLiqManualDto solicitudLiqManualDto){

        return liquidacionesDao.ProcedimientoMasivoLiquidacionVehiculos( solicitudLiqManualDto.getMesLiquidar(), solicitudLiqManualDto.getAnoLiquidar() );
    }

    /**
     * METODO PARA LISTAR LOS MESES Y AÑOS DISPONIBLES DISPONIBLES PARA LIQUIDAR.
     * @return
     */
    @Override
    @Transactional
    public FechasLiquidarDto solicitarMesesYAnosLiquidaciones() {
        FechasLiquidarDto fechasLiquidarDto = new FechasLiquidarDto();
        fechasLiquidarDto.setAnosLiquidar(liquidacionRepository.findAllDistinctAnoLiquidado());
        List<Integer> mesesInt = liquidacionRepository.findAllDistinctMesLiquidado();
        List<String> mesesString = new ArrayList<String>();
        for(Integer meses: mesesInt){
            mesesString.add(Month.of(meses).getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase(Locale.ROOT));
        }
        fechasLiquidarDto.setMesesLiquidar(mesesString);
        return fechasLiquidarDto;
    }

    /**
     * METODO PARA LISTAR LOS MESES DISPONIBLES PARA GENERAR LA LIQUIDACION MENUAL PARA EVITAR ERRORES.
     * @param anoLiquidado
     * @return retorna un arreglo de meses disponibles para poder liquidar de forma manual.
     */
    @Override
    @Transactional
    public List<String> solicitarMesesLiqManual(Integer anoLiquidado) {
        System.out.println("solicitud de meses liq manual en proceso");
        List<Integer> mesesInt = liquidacionRepository.findMesesLiquidadoByAnoLiquidado(anoLiquidado);
        List<String> mesesString = new ArrayList<String>();
        for(int i=1 ; i <= 12 ; ++i){
            if(!mesesInt.contains(i)){
                mesesString.add(Month.of(i).getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase(Locale.ROOT));
            }
        }
        return mesesString;
    }
}
