package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.TipoResiduos;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * REPOSITORY de la tabla residuos.
 */
public interface ITipoResiduosRepository extends JpaRepository<TipoResiduos, String> {

    //consulta para saber el tipoResiduo por el nombre del residuo.
    TipoResiduos findTipoResiduoByNombreResiduo (String nombreResiduo);

    //consulta de los nombres de la tabla de tipo_residuo.
    @Query("select nombreResiduo from TipoResiduos")
    List<String> findNombreResiduos();
}


