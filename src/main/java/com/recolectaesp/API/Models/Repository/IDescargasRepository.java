package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Descargas;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * REPOSITORY para la tabla de descargas.
 */
public interface IDescargasRepository extends JpaRepository<Descargas, Long> {

    // consulta para saber las descargas realizadas entre la fecha inicio y la fecha fin, por vehiculo.
    @Query ("select d from Descargas d where d.vehiculo = :vehiculo and d.fechaDescarga between :fechaInicio and :fechaFin")
    List<Descargas> findByVehiculoAndFecha(@Param("fechaInicio") Date fechaInicio,
                                           @Param("fechaFin") Date fechaFin,
                                           @Param("vehiculo") Vehiculos vehiculo);


    //query para guardar la descarga en la base de datos.
    Descargas save (Descargas descarga);

}
