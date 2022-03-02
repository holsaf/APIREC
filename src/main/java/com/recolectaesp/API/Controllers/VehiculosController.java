package com.recolectaesp.API.Controllers;

import com.recolectaesp.API.Models.Dto.VehiculoDto;
import com.recolectaesp.API.Models.Mappers.IVehiculosMapper;
import com.recolectaesp.API.Models.Services.IVehiculosService;
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
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins= {"*"}) //permite a un app front conectarse a esta api y autoriza los metodos permitidos por defecto todos estan autorizados.

/**
 * CONTROLADOR PARA LA ADMINISTRACION DE LOS VEHICULOS
 */
@RestController
@RequestMapping("/vehiculos")
public class VehiculosController {

    @Autowired
    IVehiculosService vehiculoService;

    @Autowired
    private IVehiculosMapper vehiculosMapper;

    /**
     * METODO PARA CONSULTAR LOS VEHICULOS POR PLACA
     * @param placa
     * @return
     */
    @GetMapping("/vehiculo/{placa}")
    public ResponseEntity<?> solicitarVehiculo(@PathVariable String placa) {

        VehiculoDto vehiculoDto ;
        Map<String, Object> response = new HashMap<>();

        try {
            if(!vehiculoService.existeVehiculo (placa)){
                response.put("mensaje", "El vehiculo de placa : ".concat(placa.concat( " no encontrado!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            vehiculoDto = vehiculosMapper.vehiculoToVehiculoDto(vehiculoService.consultarVehiculoPorPlaca(placa));
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(vehiculoDto, HttpStatus.OK);
    }


    /**
     * METODO PARA CREAR UN VEHICULO NUEVO
     * @param vehiculoDto
     * @param result
     * @return
     */
    @PostMapping("/crearVehiculo")
    public ResponseEntity<?> crearVehiculo(@Valid @RequestBody VehiculoDto vehiculoDto, BindingResult result) {

        VehiculoDto newVehiculoDto ;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            if(vehiculoService.existeVehiculo(vehiculoDto.getPlaca())) {
                response.put("mensaje", "El vehiculo de placa : ".concat(vehiculoDto.getPlaca().concat(" ya esta registrado!")));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            newVehiculoDto = vehiculoService.guardarVehiculo(vehiculoDto);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El vehiculo ha sido creado con éxito!");
        response.put("vehiculo", newVehiculoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * METODO PARA ACTUALZIAR UN VEHICULO EXISTENTE EN EL SISTEMA
     *
     * @param vehiculoDto
     * @param result
     * @return
     */
    @PutMapping("/actualizarVehiculo")
    public ResponseEntity<?> actualizarVehiculo(@Valid @RequestBody VehiculoDto vehiculoDto, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        VehiculoDto vehiculoActualizado ;

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            if(!vehiculoService.existeVehiculo (vehiculoDto.getPlaca())){
                response.put("mensaje", "El vehiculo de placa : ".concat(vehiculoDto.getPlaca().concat( " no encontrado!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            vehiculoActualizado = vehiculoService.actualizarVehiculo(vehiculoDto);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El vehiculo ha sido actualizado con éxito!");
        response.put("vehiculo", vehiculoActualizado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * METODO PARA BORRAR UN VEHICULO DEL SISTEMA
     * @param placa
     * @return
     */
    @DeleteMapping("/borrarVehiculo/{placa}")
    public ResponseEntity<?> borrarVehiuclo(@PathVariable String placa) {

        Map<String, Object> response = new HashMap<>();

        try {
            if(!vehiculoService.existeVehiculo (placa)){
                response.put("mensaje", "El vehiculo de placa : ".concat(placa.concat( " no encontrado!")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            vehiculoService.eliminarvehiculo(placa);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el vehiculo de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            response.put("mensaje", "El vehiculo fue eliminado con éxito!");
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}