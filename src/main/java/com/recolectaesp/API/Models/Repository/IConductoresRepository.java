package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Conductores;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REPOSITORY para la tabla de conductores.
 */
public interface IConductoresRepository extends JpaRepository< Conductores, Long> {

    //consulta si existe un conductor
    Boolean existsByCedula(String cedula);





}
