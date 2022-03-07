package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Celdas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * REPOSITORY para la tabla de celdas.
 */
public interface ICeldasRepository extends JpaRepository<Celdas , Long> {


    // consulta para saber la mejor celda disponible, dependiendo del tipo de residuo y deposito se escoge la de
    // mayor capacidad disponible.
    @Query(value = "SELECT * FROM REC_CELDAS c WHERE c.id_tipo_residuo = :tipoResiduo AND c.id_deposito = :deposito " +
            "ORDER BY c.capacidad_disponible DESC LIMIT 1", nativeQuery = true)
    Celdas findCeldaDisponibleByTipoResiduoAndDeposito(@Param("tipoResiduo") Integer id_tipo_residuo, @Param("deposito") Integer id_deposito);

}



