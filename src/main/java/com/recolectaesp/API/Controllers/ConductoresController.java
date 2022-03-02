package com.recolectaesp.API.Controllers;


import com.recolectaesp.API.Models.Dto.CeldaAsignadaDto;
import com.recolectaesp.API.Models.Dto.SolicitudCeldaDto;
import com.recolectaesp.API.Models.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins= {"*"})

/**
 * Controlador para las solicitudes de los conductores y sus funcionalidades en el sistema de RECOELCTA ESP.
 * @version: 0.1
 * @author: holman sanchez
 *
 */
@RestController
@RequestMapping("/conductores")
public class ConductoresController {

    @Autowired
    private IVehiculosService vehiculoService;

    @Autowired
    private IConductoresService conductorService;

    @Autowired
    private ICeldasService celdaService;

    @Autowired
    private IDepositosService depositoService;

    @Autowired
    private ITipoResiduosService  tipoResiduoService;


    /**
     * METODO PARA ENVIAR EL LISTADO ACTUALIZADO DISPONIBLE EN LOS CENTROS DE DEPOSITO
     * @return Map que contiene la respuesta del metodo, el map tiene un mensaje que
     * indica la descripcion de la operacion y un objeto que es resultado, en este caso se responde
     * un arreglo de String con los diferentes tipos de depositos.
     */
    @GetMapping("/tipoResiduos")
    public ResponseEntity<?> solicitarTipoResiduos() {

        Map<String,Object> response = new HashMap<>();
        List<String> respuesta;

        try{
            respuesta = tipoResiduoService.consultarTiposDeResiduos();

        } catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos!");
            response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Tipos de Residuos enviados con EXITO");
        response.put("tipoResiduos", respuesta );
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    /**
     * METODO PARA SELECCIONAR Y ENVIAR LA MEJOR CELDA DISPONIBLE AL CONDUCTOR Y QUE ESTE PUEDA PROCEDER CON
     * LA DESCARGA, TAMBIEN ACTUALIZA LA CELDA ELEGEGIDA CON EL PESO DE LA DESCARGA Y ASI MISMO GUARDA LA DESCARGA
     * EN LA BASE DE DATOS.
     * @param solicitudCeldaDto Objeto con los parmetros de placa, cedula, peso, tipoResiduo y nombreDeposito.
     * @return el Map contiene un objeto que contiene un string con el nombre de la celda asignada y el vehiculo
     * asignado a la descarga.
     *
     */
    @PostMapping("/celdaDescarga")
    public ResponseEntity<?> solicitarCelda(@Valid @RequestBody SolicitudCeldaDto solicitudCeldaDto,  BindingResult result)  {
        Map<String,Object> response = new HashMap<>();
        CeldaAsignadaDto respuesta ;

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().
                    map(error -> "El campo '".concat(error.getField().concat(error.getDefaultMessage())))
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

       try{
           if (!vehiculoService.existeVehiculo(solicitudCeldaDto.getPlaca())){
               response.put("mensaje", "Vehiculo de placa: ".concat(solicitudCeldaDto.getPlaca().concat( " no encontrado!")));
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
           }
           if(!conductorService.existeConductor(solicitudCeldaDto.getCedula())){
               response.put("mensaje", "Conductor de cedula: ".concat(solicitudCeldaDto.getCedula().concat( " no encontrado!")));
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
           }

           if(!depositoService.existeDeposito(solicitudCeldaDto.getDeposito())){
               response.put("mensaje", "El deposito: ".concat(solicitudCeldaDto.getDeposito().concat( " no encontrado!")));
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
           }

           respuesta = celdaService.calcularCelda(solicitudCeldaDto);

        } catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos!");
            response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

       if (respuesta != null){
           response.put("mensaje","Celda asignada con exito!");
           response.put("celdaAsignada", respuesta);
           return new ResponseEntity<>(response,HttpStatus.OK);
       }

       response.put("mensaje", "No hay celdas disponibles para la carga solicitada!");
       return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);



    }




}
