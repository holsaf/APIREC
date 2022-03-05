package com.recolectaesp.API.Models.Dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * DAO para la implementacion de JDBC en las funcionalidades de liquidaciones.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@Repository
public class LiquidacionesDao implements ILiquidacionesDao{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    /**
     * METODO PARA LLAMAR EL PROCEDIMIENTO RECOELCTAESP.P_LIQUIDACIONES GUARDADO EN EL ESQUEMA RECOLECTAESP
     * DE LA BASE DE DATOS.
     * @param mesLiquidado mes solicitado para la liquidacion manual.
     * @param anoLiquidado año solicitado para la liquidacion manual.
     * @return Mensaje de confirmacion del inicio de liquidaciones mensuales.
     */
    public String ProcedimientoMasivoLiquidacionVehiculos(Integer mesLiquidado, Integer anoLiquidado){

        String respuesta = "PROCESO INICIADO";
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("HOLMAN").
                withProcedureName("p_liquidacion");

        LocalDateTime fechaEntrada = LocalDateTime.of(anoLiquidado, mesLiquidado, 1, 0,0);
        Date fechaInicio = java.sql.Timestamp.valueOf(fechaEntrada);
        Date fechaFin = java.sql.Timestamp.valueOf(fechaEntrada.withDayOfMonth(fechaEntrada.toLocalDate().
                lengthOfMonth()));
        System.out.println(fechaFin);
        System.out.println(fechaInicio);
        try{
            simpleJdbcCall.execute(new MapSqlParameterSource("p_fecha_inicio", fechaInicio).
                    addValue("p_fecha_final", fechaFin));
        }catch(Exception e){
            return e.getMessage();
        }
        return respuesta;

    }



}
