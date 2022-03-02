package com.recolectaesp.API.Models.Services;
import com.recolectaesp.API.Models.Dto.VehiculoDto;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import com.recolectaesp.API.Models.Mappers.ILiquidacionMapper;
import com.recolectaesp.API.Models.Mappers.IVehiculosMapper;
import com.recolectaesp.API.Models.Repository.IVehiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para todas las funcionalidades de los vehiculos en la API RECOLECTAESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Service
public class VehiculosService implements IVehiculosService{


    @Autowired
    private IVehiculosRepository vehiculoRepository;

    @Autowired
    private IVehiculosMapper vehiculosMapper;

    @Autowired
    private IEmpresasService empresasService;

    /**
     * METODO PARA CONSULTAR VEHICULO POR SU PLACA.
     * @param placa
     * @return retorna el vehiculo.
     */
    @Override
    @Transactional(readOnly = true)
    public Vehiculos consultarVehiculoPorPlaca(String placa) {
        return vehiculoRepository.findVehiculoByPlaca(placa);
    }

    /**
     * METODO PARA CONSULTAR SI EL VEHICULO EXISTE EN EL SISTEMA.
     * @param placa
     * @return retorna TRUE si el vehiculo existe.
     */
    @Override
    @Transactional(readOnly = true)
    public Boolean existeVehiculo(String placa) {
        /*if(vehiculoRepository.existsByPlaca(placa)){
            return true;
        }
        return false;*/
        return vehiculoRepository.existsByPlaca(placa);
    }

    @Override
    @Transactional
    public VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto) {
        Vehiculos newVehiculo = vehiculosMapper.vehiculoDtoToVehiculo(vehiculoDto);
        newVehiculo.setEmpresa(empresasService.consultarEmpresa(vehiculoDto.getNitEmpresa()));
        return vehiculosMapper.vehiculoToVehiculoDto(vehiculoRepository.
                save(newVehiculo));
    }

    @Override
    @Transactional
    public Integer eliminarvehiculo(String placa) {
        return vehiculoRepository.deleteByPlaca(placa);

    }

    @Override
    @Transactional
    public VehiculoDto actualizarVehiculo(VehiculoDto vehiculoDto) {

        Vehiculos vehiculoViejo = consultarVehiculoPorPlaca(vehiculoDto.getPlaca());
        vehiculoViejo.setPesoVehiculo(vehiculoDto.getPesoVehiculo());
        vehiculoViejo.setPlaca(vehiculoDto.getPlaca());
        vehiculoViejo.setEmpresa(empresasService.consultarEmpresa(vehiculoDto.getNitEmpresa()));
        vehiculoViejo.setCapacidadCom(vehiculoDto.getCapacidadCom());
        vehiculoViejo.setCapacidadAgr(vehiculoDto.getCapacidadAgr());
        vehiculoViejo.setCapacidadBio(vehiculoDto.getCapacidadBio());
        vehiculoViejo.setCapacidadDom(vehiculoDto.getCapacidadDom());
        vehiculoViejo.setCapacidadInd(vehiculoDto.getCapacidadInd());
        vehiculoViejo.setCapacidadCos(vehiculoDto.getCapacidadCos());
        return vehiculosMapper.vehiculoToVehiculoDto(vehiculoViejo);
    }


}
