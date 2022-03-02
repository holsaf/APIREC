package com.recolectaesp.API.Models.EntityJdbc;

import lombok.Data;
import javax.persistence.Id;

/**
 * ENTITY para el proceso en JDBC.
 */
@Data
public class LiquidacionesJdbc {

        @Id
        private Long id_liquidacion;

        private Long id_vehiculo;

        private Long ano_liquidado;

        private Long mes_liquidado;

        private Long total_multas;

        private Long total_pagar;

        private Integer numeroDescargas;

    }
