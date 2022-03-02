package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * REPOSITORY de la tabla vehiculos.
 */
public interface IVehiculosRepository extends JpaRepository<Vehiculos, String> {

    //consulta para encontrar vehiculo de acuerdo a la placa.
    Vehiculos findVehiculoByPlaca(String placa);

    // consulta para saber si existe un vehiculo de acuerdo a la placa.
    Boolean existsByPlaca(String placa);

    @Modifying
    @Query(value="DELETE FROM Vehiculos v where v.placa = :placa")
    Integer deleteByPlaca(@Param("placa") String placa);




}
