package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.TipoResiduos;

import java.util.List;

public interface ITipoResiduosService {

    TipoResiduos consultarTipoResiduosPorNombre(String nombreResiduo);

    List<String> consultarTiposDeResiduos();
}
