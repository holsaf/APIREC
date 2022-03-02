package com.recolectaesp.API.Models.Repository;

import com.recolectaesp.API.Models.Entity.Liquidaciones;
import com.recolectaesp.API.Models.Entity.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * REPOSITORY para la tabla liquidaciones.
 */
public interface ILiquidacionesRepository extends JpaRepository<Liquidaciones, Long> {

    //query para guardar la liquidacion en la base de datos
    Liquidaciones save (Liquidaciones liquidacion);

    // consulta para saber la liquidacion del vehiculo, dpendiendo del me sy año.
    Optional<Liquidaciones> findLiquidacionByVehiculoAndAnoLiquidadoAndMesLiquidado(Vehiculos vehiculo,
                                                                                    Integer anoLiquidado,
                                                                                    Integer mesLiquidado) ;

    //consulta de los diferentes meses en liquidacion por año.
    @Query(value ="SELECT DISTINCT mesLiquidado FROM Liquidaciones WHERE anoLiquidado = :anoLiquidado ORDER BY mesLiquidado")
    List<Integer> findMesesLiquidadoByAnoLiquidado(@Param("anoLiquidado") Integer anoLiquidado);

    //consulta de los diferentes meses disponibles en la tabla liquidaciones.
    @Query("SELECT DISTINCT anoLiquidado FROM Liquidaciones ORDER BY anoLiquidado")
    List<Integer> findAllDistinctAnoLiquidado();

    //consulta de los diferentes años disponibles en la tabla lliquidaciones
    @Query("SELECT DISTINCT mesLiquidado FROM Liquidaciones ORDER BY mesLiquidado")
    List<Integer> findAllDistinctMesLiquidado();




}
