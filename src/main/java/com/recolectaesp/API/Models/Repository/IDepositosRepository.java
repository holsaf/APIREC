package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Depositos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REPOSITORY para la tabla de depositos.
 */
public interface IDepositosRepository extends JpaRepository<Depositos, Long> {

    //consulta para encontrar el deposito por su nombre.
    Depositos findDepositoByNombreDeposito(String nombreDeposito);

    //consulta si exite el deposito por su nombre
    Boolean existsByNombreDeposito(String nombreDeposito);

}
