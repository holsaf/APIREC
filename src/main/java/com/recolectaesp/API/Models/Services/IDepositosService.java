package com.recolectaesp.API.Models.Services;

import com.recolectaesp.API.Models.Entity.Depositos;

public interface IDepositosService {

    Depositos consultarDepositoPorNombre(String nombreDeposito);

    Boolean existeDeposito(String nombreDeposito);
}
