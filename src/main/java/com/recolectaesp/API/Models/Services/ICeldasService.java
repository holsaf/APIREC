package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Dto.CeldaAsignadaDto;
import com.recolectaesp.API.Models.Dto.SolicitudCeldaDto;
import com.recolectaesp.API.Models.Entity.Celdas;
import com.recolectaesp.API.Models.Entity.Depositos;
import com.recolectaesp.API.Models.Entity.TipoResiduos;


public interface ICeldasService {

    CeldaAsignadaDto calcularCelda(SolicitudCeldaDto solicitudCeldaDto);

    Celdas celdaMayorCapacidadDisponible( TipoResiduos residuo, Depositos deposito, Long pesoCarga );

    void actualizarCapacidadDisponibleCelda(Long pesoCarga, Celdas celda);



}
