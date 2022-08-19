package com.damoralesr97.vacunas.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damoralesr97.vacunas.dto.Busqueda;
import com.damoralesr97.vacunas.entity.Empleado;
import com.damoralesr97.vacunas.response.ResponseHandler;
import com.damoralesr97.vacunas.service.EmpleadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/empleados")
@SecurityRequirement(name = "basicAuth")
@Api(value = "EmpleadoController", description = "REST APIs relacionada a la Entidad Empleado")
public class EmpleadoController {

	@Autowired
	EmpleadoService empleadoService;

	@ApiOperation(value = "Obtener todos los empleados registrados", response = Iterable.class)
	@GetMapping(value = { "" })
	public ResponseEntity<Object> findAll() {
		return ResponseHandler.generateResponse("¡Datos recuperados con éxito!", HttpStatus.OK,
				empleadoService.findAll());
	}

	@ApiOperation(value = "Registrar un empleado", response = Empleado.class)
	@PostMapping(value = { "" })
	public ResponseEntity<Object> save(@RequestBody Empleado empleado) {
		try {
			Empleado emp = empleadoService.save(empleado);
			return ResponseHandler.generateResponse("¡Datos añadidos con éxito!", HttpStatus.OK, emp);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

	@ApiOperation(value = "Actualizar datos de un empleado", response = Empleado.class)
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Object> adminActualizarEmpleado(@PathVariable(value = "id") Integer empleadoId,
			@RequestBody Empleado empleadoDetalle) throws InvocationTargetException, IllegalAccessException {
		try {
			Empleado empleado = empleadoService.adminActualizarEmpleado(empleadoId, empleadoDetalle);
			return ResponseHandler.generateResponse("¡Empleado actualizado!", HttpStatus.OK, empleado);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

	@ApiOperation(value = "Eliminar registro de un empleado", response = Empleado.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarEmpleado(@PathVariable(value = "id") Integer empleadoId) {
		try {
			Empleado empleado = empleadoService.eliminarEmpleado(empleadoId);
			return ResponseHandler.generateResponse("¡Empleado eliminado!", HttpStatus.OK, empleado);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

	@ApiOperation(value = "Obtener informacion de un empleado", response = Empleado.class)
	@GetMapping(value = { "/me/{id}" })
	public ResponseEntity<Object> me(@PathVariable(value = "id") Integer empleadoId) {
		try {
			return ResponseHandler.generateResponse("¡Datos recuperados con éxito!", HttpStatus.OK,
					empleadoService.me(empleadoId));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

	@ApiOperation(value = "Obtener todos los empleados registrados que cumplan con un criterio de busqueda", response = Iterable.class, notes = "Se puede filtrar por estadoVacuna, tipoVacuna, rango de fechas (fechaInicio, fechaFin)")
	@PostMapping("/busqueda")
	public ResponseEntity<Object> busqueda(@RequestBody Busqueda busqueda) {
		try {
			return ResponseHandler.generateResponse("¡Datos recuperados con éxito!", HttpStatus.OK,
					empleadoService.busquedaFiltros(busqueda));
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

}
