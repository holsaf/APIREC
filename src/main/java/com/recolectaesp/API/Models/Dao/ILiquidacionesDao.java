package com.recolectaesp.API.Models.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ILiquidacionesDao {

    String ProcedimientoMasivoLiquidacionVehiculos(Integer mesLiquidado, Integer anoLiquidado);

}
