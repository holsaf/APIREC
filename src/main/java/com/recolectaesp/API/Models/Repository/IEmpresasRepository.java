package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Empresas;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * REPOSITORY para la tabla de empresas.
 */
public interface IEmpresasRepository extends JpaRepository<Empresas , Long> {

    //consulta para saber si exite un empresa
    Boolean existsByNitEmpresa(String nitEmpresa);

    //consulta para saber todos los vehiculos asignados a la empresa.
    @Query(value = "SELECT v FROM Empresas e JOIN e.vehiculosEmpresa v WHERE e.nitEmpresa = :nitEmpresa")
    List<Vehiculos> findAllVehiculosEmpresa(@Param("nitEmpresa") String nitEmpresa);

    @Query(value="SELECT e FROM Empresas e WHERE e.nitEmpresa = :nitEmpresa")
    Empresas findByNitEmpresa(@Param("nitEmpresa") String nitEmpresa);


}
