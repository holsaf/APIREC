package com.recolectaesp.API.Controllers;

import com.recolectaesp.API.Models.Dto.FechasLiquidarDto;
import com.recolectaesp.API.Models.Dto.LiquidacionesEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqEmpresaDto;
import com.recolectaesp.API.Models.Dto.SolicitudLiqManualDto;
import com.recolectaesp.API.Models.Services.IEmpresasService;
import com.recolectaesp.API.Models.Services.ILiquidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins= {"*"}) //permite a un app front conectarse a esta api y autoriza los metodos permitidos por defecto todos estan autorizados.
/**
 * Controlador para las solicitudes de las liquidaciones y sus funcionalidades en el sistema de RECOELCTA ESP.
 * @version: 1.0
 * @author: holman.sanchez@segurosbolivar.com
 *
 */
@RestController
@RequestMapping("/liquidaciones")
public class LiquidacionesController {


    @Autowired
    private IEmpresasService empresaService;

    @Autowired
    private ILiquidacionesService liquidacionService;

    /**
     * METODO PARA ENVIAR LOS MESES Y AÑOS DISPONIBLES AL USUARIO PARA QUE PUEDA LIQUIDAR EN LA FECHA DESEADA.
     * @return Map que contiene la respuesta del metodo, el map tiene un mensaje que
     * indica la descripcion de la operacion y un objeto que es resultado.
     * En este caso: Respuesta HTTP 200 (OK), se retorna un objeto
     * con 2 arreglos, el primero de string con los meses y el segundo de int con los años.
     *
     *
     */
    @GetMapping("/fechasLiquidar")
    public ResponseEntity<?> solicitarMesesYAnosLiquidaciones() {

        Map<String,Object> response = new HashMap<>();
        FechasLiquidarDto respuesta ;

        try{
            respuesta = liquidacionService.solicitarMesesYAnosLiquidaciones();
        } catch (DataAccessException e) {
        response.put("mensaje","Error al realizar la consulta en la base de datos!");
        response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
        response.put("mensaje","Fechas enviadas con exito");
        response.put("fechasLiquidar", respuesta );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * METODO PARA ENVIAR LOS MESES QUE NO ESTAN LIQUIDADOS EN LA BASE DE DATOS DE ACUERDO CON EL AÑO, CON EL
     * ANIMO DE EVITAR ERRORES AL USUARIO.
     * @param anoLiqManual contiene el año requerido para la consulta.
     * @return Respuesta HTTP 200 (OK), se retorna un arreglo de string con los meses disponibles para liquidar.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @GetMapping("/mesesLiqManual")
    public ResponseEntity<?> solicitarMesesLiqManual(@RequestParam (name="anoLiqManual") Integer anoLiqManual) {

        Map<String,Object> response = new HashMap<>();
        List<String> respuesta ;
        int anoActual = Year.now().getValue();
        System.out.println(anoActual);
        try{
            if (anoLiqManual<2020||anoLiqManual>anoActual) {
                response.put("mensaje", "Porfavor ingrese un año valido a partir de 2020!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            respuesta = liquidacionService.solicitarMesesLiqManual(anoLiqManual);
        } catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos!");
            response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Meses LiqManual enviados con exito");
        response.put("mesesLiqManual", respuesta );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /**
     * METODO PARA ENVIAR LA LIQUIDACION DE UN EMPRESA, QUE ES UNA CONSULTA DEL LISTADO DE LAS LIQUIDACIONES
     * MENSUALES DE LOS VEHICULOS ASIGNADO A ESA EMPRESA
     * @param nitEmpresa NIT de la empresa a consultar.
     * @param mesLiquidar mes de la liquidacion que se desea consultar.
     * @param anoLiquidar año de la liquidacion que se desea consultar.
     * @return Respuesta HTTP 200 (OK), se retorna un objeto con el nombre de la empresa, la fecha de liquidacion,
     * y el listado de liquidaciones de vehiculos asignados.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @GetMapping("/liquidarEmpresa")
    public ResponseEntity<?> solicitarLiquidacionEmpresa(@RequestParam(value = "nit") String nitEmpresa,
                                                         @RequestParam (name="mesLiquidar") Integer mesLiquidar,
                                                         @RequestParam (name="anoLiquidar") Integer anoLiquidar) {

        Map<String,Object> response = new HashMap<>();
        LiquidacionesEmpresaDto respuesta;
        int anoActual = Year.now().getValue();


        try{
            if (!empresaService.existeEmpresa(nitEmpresa)) {
                response.put("mensaje", "Empresa de NIT: ".concat(nitEmpresa.toString().concat(" no encontrado!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            if (!empresaService.existenVehiculos(nitEmpresa)) {
                response.put("mensaje", "Empresa de NIT: ".concat(nitEmpresa.toString().concat(" no tiene vehiculos registrados!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (mesLiquidar<1||mesLiquidar>12) {
                response.put("mensaje", "Porfavor ingrese un numero valido para el mes, entre 1 y 12");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (anoLiquidar<2020||anoLiquidar>anoActual) {
                response.put("mensaje", "Porfavor ingrese un numero valido para el año, a partir del 2020 y maximo el actual");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            SolicitudLiqEmpresaDto solicitudLiqEmpresaDto = new SolicitudLiqEmpresaDto();
            solicitudLiqEmpresaDto.setAnoLiquidar(anoLiquidar);
            solicitudLiqEmpresaDto.setMesLiquidar(mesLiquidar);
            solicitudLiqEmpresaDto.setNit(nitEmpresa);
            respuesta = liquidacionService.liquidacionEmpresa(solicitudLiqEmpresaDto);

        }catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos!");
            response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (respuesta != null){
            response.put("liquidacionEmpresa", respuesta);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        response.put("mensaje", "Los vehiculos de la empresa con NIT :".concat(nitEmpresa.concat(" no estan liquidados en el mes solicitado.!")));
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    /**
     * METODO PARA SOLICITAR LA LIQUIDACION MANUAL DE LO VEHICULOS EN EL SISTEMA,
     * @param solicitudLiqManualDto Objeto requerido para la liquidacion, contiene el mes y el año a liquidar en int.
     * @param result variable para la validacion de parametros.
     * @return Respuesta HTTP 201 (CREATED), se retorna un mensaje con la confirmacion exitosa de la carga de datos masiva.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @PostMapping("/liquidacionManual")
    public ResponseEntity<?> liquidacionManual(@Valid @RequestBody SolicitudLiqManualDto solicitudLiqManualDto,  BindingResult result)
                                               throws SQLException, ClassNotFoundException {
        Map<String,Object> response = new HashMap<>();
        String respuesta;

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().
                    map(error -> "El campo '".concat(error.getField().concat(Objects.requireNonNull(error.getDefaultMessage()))))
                    .collect(Collectors.toList());
            response.put("error", errors);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            respuesta = liquidacionService.llamarLiquidacionMensualVehiculos(solicitudLiqManualDto);
        } catch (DataAccessException e) {
            response.put("mensaje","Error al realizar la consulta en la base de datos!");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje",respuesta);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
}
