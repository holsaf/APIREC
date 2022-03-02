package com.recolectaesp.API.Models.Services;
import com.recolectaesp.API.Models.Dto.FechasLiquidarDto;
import com.recolectaesp.API.Models.Dto.LiquidacionesEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqManualDto;
import com.recolectaesp.API.Models.Entity.Descargas;
import com.recolectaesp.API.Models.Entity.Liquidaciones;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import java.util.List;

public interface ILiquidacionesService {

    LiquidacionesEmpresaDto liquidacionEmpresa (SolicitudLiqEmpresaDto solicitudLiqEmpresaDto);

    Liquidaciones consultarLiquidacionVehiculo(Vehiculos vehiculo,  Integer mesLiquidar, Integer anoLiquidar) ;

    //Liquidaciones  guardarLiquidacion(Liquidaciones liquidacion);

    //Liquidaciones calcularValorAPagarYMultas(List<Descargas> listaDescargas, Vehiculos vehiculo, Liquidaciones nuevaLiquidacion);

    String llamarLiquidacionMensualVehiculos(SolicitudLiqManualDto solicitudLiqManualDto);

    FechasLiquidarDto solicitarMesesYAnosLiquidaciones();

    List<String> solicitarMesesLiqManual(Integer anoLiqManual);
}
